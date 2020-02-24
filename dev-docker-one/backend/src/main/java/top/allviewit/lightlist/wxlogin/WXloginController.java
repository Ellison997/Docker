package top.allviewit.lightlist.wxlogin;

import java.io.IOException;
import java.security.MessageDigest;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.List;
import java.util.concurrent.TimeUnit;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.util.UriUtils;
import top.allviewit.lightlist.common.HttpRequest;

@Controller
@RequestMapping("/wxlogin")
public class WXloginController {

    @Value("${wx.domain}")
    private String domain;
    @Value("${wx.appid}")
    private String appid;
    @Value("${wx.secret}")
    private String secret;

    private final char[] HEX_DIGITS = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e',
            'f' };

    @Autowired
    private StringRedisTemplate redisClient;

    @RequestMapping("/i")
    public String index(@RequestParam(name = "plugin", required = false) String plugin,
            @RequestParam(name = "data", required = false) String data, HttpServletRequest request) {

        String redirectUrl = this.domain + "/weixin/login?plugin=" + plugin + "&data=" + data;
        String wxurl = "https://open.weixin.qq.com/connect/oauth2/authorize?redirect_uri=";

        StringBuilder finalurl = new StringBuilder();
        finalurl.append(wxurl).append(UriUtils.encode(redirectUrl, "utf-8")).append("&appid=").append(this.appid)
                .append("&response_type=code&scope=snsapi_base&state=123#wechat_redirect");
        return "redirect:" + finalurl.toString();
    }

    @RequestMapping("/login")
    public ModelAndView wxlogin(@RequestParam(name = "code") String code,
            @RequestParam(name = "plugin", required = false) String plugin,
            @RequestParam(name = "data", required = false) String data, HttpServletRequest request) {

        HttpSession session = request.getSession();

        try {

            // 获取openid
            StringBuilder accessTokenUrl = new StringBuilder();
            accessTokenUrl.append("appid=").append(appid).append("&secret=").append(secret).append("&code=")
                    .append(code).append("&grant_type=authorization_code");
            String web_accessTokenStr = HttpRequest.sendGet("https://api.weixin.qq.com/sns/oauth2/access_token",
                    accessTokenUrl.toString());

            JsonNode web_accessTokenJson;

            web_accessTokenJson = new ObjectMapper().readTree(web_accessTokenStr);

            String openid = web_accessTokenJson.path("openid").asText();
            System.out.println(openid);
            // TODO: 用户登陆
            session.setAttribute("openid", openid);
            // 前端js-sdk认证
            // 获取api-accessTocken
            String api_accessTocken = redisClient.opsForValue().get("api-accessTocken");
            if (api_accessTocken == null) {
                String api_accessTockenStr = HttpRequest.sendGet("https://api.weixin.qq.com/cgi-bin/token",
                        "grant_type=client_credential&appid=" + appid + "&secret=" + secret);

                JsonNode api_accessTockenJson = new ObjectMapper().readTree(api_accessTockenStr);
                api_accessTocken = api_accessTockenJson.path("access_token").asText();
                redisClient.opsForValue().set("api-accessTocken", api_accessTocken, 7200, TimeUnit.SECONDS);
            }
            // 获取js-accessTocken
            String js_tocken = redisClient.opsForValue().get("jsapi_tocken");
            if (js_tocken == null) {
                String js_tockenStr = HttpRequest.sendGet("https://api.weixin.qq.com/cgi-bin/ticket/getticket",
                        "access_token=" + api_accessTocken + "&type=jsapi");
                JsonNode js_apiTockenJson = new ObjectMapper().readTree(js_tockenStr);
                js_tocken = js_apiTockenJson.path("ticket").asText();
                redisClient.opsForValue().set("jsapi_tocken", js_tocken, 7200, TimeUnit.SECONDS);
            }
            // 计算校验变量(noncestr,timestamp,singature)
            String noncestr = "topofthemontain";
            String timestamp = String.valueOf(LocalDateTime.now().toEpochSecond(ZoneOffset.of("+8")));
            // timestamp = timestamp.substring(0, timestamp.length() - 3);
            String url = domain + "/weixin/login?" + request.getQueryString();
            String string1 = "jsapi_ticket=" + js_tocken + "&noncestr=" + noncestr + "&timestamp=" + timestamp + "&url="
                    + url;
            String singature = "";
            System.out.println(string1);
            try {
                MessageDigest messageDigest = MessageDigest.getInstance("SHA1");
                messageDigest.update(string1.getBytes());
                byte[] bytes = messageDigest.digest();
                int len = bytes.length;
                StringBuilder buf = new StringBuilder(len * 2);
                // 把密文转换成十六进制的字符串形式
                for (int j = 0; j < len; j++) {
                    buf.append(HEX_DIGITS[(bytes[j] >> 4) & 0x0f]);
                    buf.append(HEX_DIGITS[bytes[j] & 0x0f]);
                }
                singature = buf.toString();
            } catch (Exception e) {
                throw new RuntimeException(e);
            }

            // 添加业务逻辑
            // 判断是不是填写问卷调查和是否领取奖
            System.out.println("你好openid：" + openid);

            ModelAndView view = new ModelAndView();
            view.addObject("singature", singature);
            view.addObject("timestamp", timestamp);
            view.addObject("noncestr", noncestr);
       
            // view.addObject("plugin", plugin);
            // view.addObject("data", data);
            view.setViewName("index");

            // 获取js token
            return view;
        } catch (IOException e) {
            e.printStackTrace();
            ModelAndView view = new ModelAndView();
            view.setViewName("error");
            return view;
        }
    }

}