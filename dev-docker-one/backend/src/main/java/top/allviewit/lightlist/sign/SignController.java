package top.allviewit.lightlist.sign;

import top.allviewit.lightlist.activity.ActivityModel;
import top.allviewit.lightlist.activity.ActivityRepository;
import top.allviewit.lightlist.common.RespModel;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/sign")
public class SignController {

    @Autowired
    SignRepository signRepository;

    @Autowired
    ActivityRepository activityRepository;

    @RequestMapping("/list")
    @ResponseBody
    public RespModel List() {
        List<SignModel> signs = signRepository.findAll();
        return new RespModel(true, "", signs);
    }

    @RequestMapping("/mylist")
    @ResponseBody
    public RespModel myList(HttpServletRequest request) {
        HttpSession session = request.getSession();
        List<SignModel> signs = signRepository.findByOpenid(session.getAttribute("openid").toString());
        return new RespModel(true, "", signs);
    }

    @RequestMapping("/mysigh")
    @ResponseBody
    public RespModel myList(int activityid, HttpServletRequest request) {
        HttpSession session = request.getSession();
        ActivityModel activity = new ActivityModel();
        activity.setId(activityid);
        List<SignModel> punchs = signRepository.findByOpenidAndActivity(session.getAttribute("openid").toString(),
                activity);
        if (punchs.size() == 0) {
            SignModel punch = new SignModel();
            ActivityModel act = activityRepository.findById(activityid).get();
            punch.setActivity(act);
            punchs.add(punch);
        }
        return new RespModel(true, "", punchs);
    }

    @RequestMapping("/add")
    @ResponseBody
    public RespModel add(int activityid, String name, String phone, HttpServletRequest request) {
        HttpSession session = request.getSession();
        SignModel sign = new SignModel();
        ActivityModel activity = new ActivityModel();
        activity.setId(activityid);
        sign.setActivity(activity);
        sign.setName(name);
        sign.setPhone(phone);
        sign.setOpenid(session.getAttribute("openid").toString());
        sign.setCreateTime(Timestamp.valueOf(LocalDateTime.now()));
        signRepository.save(sign);
        return new RespModel(true, "", null);
    }

    @RequestMapping("/del")
    @ResponseBody
    public RespModel del(@RequestParam(name = "id") int id) {
        signRepository.deleteById(id);
        return new RespModel(true, "", null);
    }

}