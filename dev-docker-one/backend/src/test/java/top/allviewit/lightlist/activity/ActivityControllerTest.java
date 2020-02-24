package top.allviewit.lightlist.activity;

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
@FixMethodOrder(MethodSorters.JVM)
public class ActivityControllerTest {

    @Autowired
    private MockMvc mvc;

    @Test
    public void addActivity() throws Exception {
        ResultActions result = mvc.perform(
                MockMvcRequestBuilders.post("/activity/add").param("title", "111").accept(MediaType.APPLICATION_JSON));
        result.andDo(print());
        result.andExpect(status().isOk());
        result.andExpect(MockMvcResultMatchers.jsonPath("success").value("true"));
    }

    @Test
    public void updateActivity() throws Exception {
        ResultActions result = mvc.perform(MockMvcRequestBuilders.post("/activity/update").param("id", "1")
                .param("title", "123").accept(MediaType.APPLICATION_JSON));
        result.andDo(print());
        result.andExpect(status().isOk());
        result.andExpect(MockMvcResultMatchers.jsonPath("success").value("true"));
    }

    @Test
    public void getActivityList() throws Exception {
        ResultActions result = mvc
                .perform(MockMvcRequestBuilders.get("/activity/list").accept(MediaType.APPLICATION_JSON));
        result.andDo(print());
        result.andExpect(status().isOk());
        result.andExpect(MockMvcResultMatchers.jsonPath("success").value("true"));
        result.andExpect(MockMvcResultMatchers.jsonPath("data[0].title").value("123"));
    }

    @Test
    public void delActivity() throws Exception {
        ResultActions results = mvc.perform(
                MockMvcRequestBuilders.post("/activity/del").param("id", "1").accept(MediaType.APPLICATION_JSON));
        results.andDo(print());
        results.andExpect(status().isOk());
        results.andExpect(MockMvcResultMatchers.jsonPath("success").value("true"));
    }

}