package top.allviewit.lightlist.punch;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import top.allviewit.lightlist.activity.ActivityModel;

public interface PunchRepository extends JpaRepository<PunchModel, Integer> {
    public List<PunchModel> findByOpenid(String openid);

    public List<PunchModel> findByOpenidAndActivity(String string, ActivityModel activityid);
}