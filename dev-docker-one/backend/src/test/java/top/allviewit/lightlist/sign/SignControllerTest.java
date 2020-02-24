package top.allviewit.lightlist.sign;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
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
// 报名
public class SignControllerTest {

    @Autowired
    private MockMvc mvc;

    @Before
    public void dataInit() throws Exception {
        mvc.perform(
                MockMvcRequestBuilders.post("/activity/add").param("title", "123").accept(MediaType.APPLICATION_JSON));
        mvc.perform(MockMvcRequestBuilders.post("/punch/add").sessionAttr("openid", "1a1").param("activityid", "1")
                .param("phone", "aa").param("name", "aa").accept(MediaType.APPLICATION_JSON));
    }

    @Test
    public void sign() throws Exception {
        ResultActions result = mvc.perform(MockMvcRequestBuilders.post("/sign/add").sessionAttr("openid", "1a1")
                .param("activityid", "1").param("name", "aa").param("phone", "aa").accept(MediaType.APPLICATION_JSON));
        result.andDo(print());
        result.andExpect(status().isOk());
        result.andExpect(MockMvcResultMatchers.jsonPath("success").value("true"));
    }

    @Test
    public void getMySignList() throws Exception {
        sign();
        ResultActions result = mvc.perform(MockMvcRequestBuilders.get("/sign/mylist").sessionAttr("openid", "1a1")
                .accept(MediaType.APPLICATION_JSON));
        result.andDo(print());
        result.andExpect(status().isOk());
        result.andExpect(MockMvcResultMatchers.jsonPath("success").value("true"));
        result.andExpect(MockMvcResultMatchers.jsonPath("data[0].phone").value("aa"));
    }

    @Test
    public void getSignList() throws Exception {
        sign();
        ResultActions result = mvc.perform(MockMvcRequestBuilders.get("/sign/list").accept(MediaType.APPLICATION_JSON));
        result.andDo(print());
        result.andExpect(status().isOk());
        result.andExpect(MockMvcResultMatchers.jsonPath("success").value("true"));
        result.andExpect(MockMvcResultMatchers.jsonPath("data[0].phone").value("aa"));
    }

    @Test
    public void delSign() throws Exception {
        sign();
        ResultActions results = mvc
                .perform(MockMvcRequestBuilders.post("/sign/del").param("id", "1").accept(MediaType.APPLICATION_JSON));
        results.andDo(print());
        results.andExpect(status().isOk());
        results.andExpect(MockMvcResultMatchers.jsonPath("success").value("true"));
    }

}