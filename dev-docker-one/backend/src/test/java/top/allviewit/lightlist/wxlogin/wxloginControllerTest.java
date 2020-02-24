package top.allviewit.lightlist.wxlogin;

import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.*;

import java.time.LocalDateTime;
import java.time.ZoneOffset;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
@FixMethodOrder(MethodSorters.JVM)
public class wxloginControllerTest {

    @Autowired
    private MockMvc mvc;

    @Autowired
    private StringRedisTemplate redisClient;

    @Test
    public void testIndex() throws Exception {
        ResultActions result = mvc.perform(
                MockMvcRequestBuilders.post("/wxlogin/i").param("title", "111").accept(MediaType.APPLICATION_JSON));
        result.andDo(print());
        // result.andExpect(MockMvcResultMatchers.jsonPath("success").value("true"));
    }

}