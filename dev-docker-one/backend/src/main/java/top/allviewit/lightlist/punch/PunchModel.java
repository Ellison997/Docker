package top.allviewit.lightlist.punch;

import java.sql.Timestamp;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import com.fasterxml.jackson.annotation.JsonProperty;

import top.allviewit.lightlist.activity.ActivityModel;

@Entity
@Table(name = "punch")

public class PunchModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonProperty
    private int Id;

    @ManyToOne
    @JoinColumn(name = "activity_id")
    private ActivityModel activity;

    private String openid;

    @JsonProperty
    private String name;
    @JsonProperty
    private String phone;
    @JsonProperty
    private Timestamp createTime;

    public Timestamp getCreateTime() {
        return this.createTime;
    }

    public void setCreateTime(Timestamp createTime) {
        this.createTime = createTime;
    }

    public int getId() {
        return this.Id;
    }

    public void setId(int Id) {
        this.Id = Id;
    }

    public ActivityModel getActivity() {
        return this.activity;
    }

    public void setActivity(ActivityModel activity) {
        this.activity = activity;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return this.phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getOpenid() {
        return this.openid;
    }

    public void setOpenid(String openid) {
        this.openid = openid;
    }

}