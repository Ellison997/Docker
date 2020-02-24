package top.allviewit.lightlist.sign;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import top.allviewit.lightlist.activity.ActivityModel;

public interface SignRepository extends JpaRepository<SignModel, Integer> {
    public List<SignModel> findByOpenid(String openid);

    public List<SignModel> findByOpenidAndActivity(String string, ActivityModel activity);
}