package top.allviewit.lightlist.punch;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
@FixMethodOrder(MethodSorters.DEFAULT)
// 报名
public class PunchControllerTest {

    @Autowired
    private MockMvc mvc;

    public void addActivity() throws Exception {
        mvc.perform(
                MockMvcRequestBuilders.post("/activity/add").param("title", "123").accept(MediaType.APPLICATION_JSON));
    }

    @Test
    public void addPunch() throws Exception {
        addActivity();
        ResultActions result = mvc
                .perform(MockMvcRequestBuilders.post("/punch/add").sessionAttr("openid", "a1a").param("activityid", "1")
                        .param("name", "aa").param("phone", "123123").accept(MediaType.APPLICATION_JSON));
        result.andDo(print());
        result.andExpect(status().isOk());
        result.andExpect(MockMvcResultMatchers.jsonPath("success").value("true"));
    }

    @Test
    public void getMyPunchOneActivity() throws Exception {
        addPunch();
        ResultActions result = mvc.perform(MockMvcRequestBuilders.get("/punch/mypunch").sessionAttr("openid", "a1a")
                .param("activityid", "1").accept(MediaType.APPLICATION_JSON));
        result.andDo(print());
        result.andExpect(status().isOk());
        result.andExpect(MockMvcResultMatchers.jsonPath("success").value("true"));
        result.andExpect(MockMvcResultMatchers.jsonPath("data[0].phone").value("123123"));
    }

    @Test
    public void getMyPunchList() throws Exception {
        addPunch();
        ResultActions result = mvc.perform(MockMvcRequestBuilders.get("/punch/mylist").sessionAttr("openid", "a1a")
                .accept(MediaType.APPLICATION_JSON));
        result.andDo(print());
        result.andExpect(status().isOk());
        result.andExpect(MockMvcResultMatchers.jsonPath("success").value("true"));
        result.andExpect(MockMvcResultMatchers.jsonPath("data[0].phone").value("123123"));
    }

    @Test
    public void getPunchList() throws Exception {
        addPunch();
        ResultActions result = mvc
                .perform(MockMvcRequestBuilders.get("/punch/list").accept(MediaType.APPLICATION_JSON));
        result.andDo(print());
        result.andExpect(status().isOk());
        result.andExpect(MockMvcResultMatchers.jsonPath("success").value("true"));
        result.andExpect(MockMvcResultMatchers.jsonPath("data[0].phone").value("123123"));
    }

    @Test
    public void delPunch() throws Exception {
        addPunch();
        ResultActions results = mvc
                .perform(MockMvcRequestBuilders.post("/punch/del").param("id", "1").accept(MediaType.APPLICATION_JSON));
        results.andDo(print());
        results.andExpect(status().isOk());
        results.andExpect(MockMvcResultMatchers.jsonPath("success").value("true"));
    }

}