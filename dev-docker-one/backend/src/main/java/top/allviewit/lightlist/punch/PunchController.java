package top.allviewit.lightlist.punch;

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
@RequestMapping("/punch")
public class PunchController {

    @Autowired
    PunchRepository punchRepository;

    @Autowired
    ActivityRepository activityRepository;

    @RequestMapping("/list")
    @ResponseBody
    public RespModel index() {
        List<PunchModel> punchs = punchRepository.findAll();
        return new RespModel(true, "", punchs);
    }

    @RequestMapping("/mylist")
    @ResponseBody
    public RespModel myList(HttpServletRequest request) {
        HttpSession session = request.getSession();
        List<PunchModel> punchs = punchRepository.findByOpenid(session.getAttribute("openid").toString());
        return new RespModel(true, "", punchs);
    }

    @RequestMapping("/mypunch")
    @ResponseBody
    public RespModel myList(int activityid, HttpServletRequest request) {
        HttpSession session = request.getSession();
        ActivityModel activity = new ActivityModel();
        activity.setId(activityid);
        List<PunchModel> punchs = punchRepository.findByOpenidAndActivity(session.getAttribute("openid").toString(),
                activity);
        if (punchs.size() == 0) {
            PunchModel punch = new PunchModel();
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
        PunchModel punch = new PunchModel();
        ActivityModel activity = new ActivityModel();
        activity.setId(activityid);
        punch.setActivity(activity);
        punch.setName(name);
        punch.setPhone(phone);
        punch.setCreateTime(Timestamp.valueOf(LocalDateTime.now()));
        punch.setOpenid(session.getAttribute("openid").toString());
        punchRepository.save(punch);
        return new RespModel(true, "", null);
    }

    @RequestMapping("/del")
    @ResponseBody
    public RespModel del(@RequestParam(name = "id") int id) {
        punchRepository.deleteById(id);
        return new RespModel(true, "", null);
    }

}