package top.allviewit.lightlist.activity;

import top.allviewit.lightlist.common.RespModel;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/activity")
public class ActivityController {

    @Autowired
    ActivityRepository activityRepository;

    @RequestMapping("/list")
    @ResponseBody
    public RespModel index() {
        List<ActivityModel> activities = activityRepository.findAll();
        return new RespModel(true, "", activities);
    }

    @RequestMapping("/add")
    @ResponseBody
    public RespModel add(@RequestParam(name = "title") String title) {
        ActivityModel activity = new ActivityModel();
        activity.setTitle(title);
        activityRepository.save(activity);
        return new RespModel(true, "", null);
    }

    @RequestMapping("/del")
    @ResponseBody
    public RespModel del(@RequestParam(name = "id") int id) {
        activityRepository.deleteById(id);
        return new RespModel(true, "", null);
    }

    @RequestMapping("/update")
    @ResponseBody
    public RespModel update(@RequestParam(name = "id") int id, @RequestParam(name = "title") String title) {
        ActivityModel activity = new ActivityModel();
        activity.setId(id);
        activity.setTitle(title);
        activityRepository.saveAndFlush(activity);
        return new RespModel(true, "", null);
    }
}