package com.zsl.template;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpMethod;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import javax.annotation.Resource;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

/**
 * PersonInfoTest
 * 不加注解则没有启动servlet,所以如果controller层里有注入bean,则无法实现测试
 *  启动servlet测试需要添加注解 :
 *  @SpringBootTest
 *  @ExtendWith(SpringExtension.class)
 *
 * @author swiftzsl
 * @date 2021/7/25 14:50
 */
@Slf4j
// 自动构建mock对象
@AutoConfigureMockMvc
@SpringBootTest
// 需要注入bean
@ExtendWith(SpringExtension.class)
public class PersonInfoTest {

    @Resource
    private MockMvc mockMvc;

//    /**
//     * 在所有测试方法之前执行
//     */
//    @BeforeAll
//    static void setUp() {
//        Object[] controllers;
//        mockMvc = MockMvcBuilders.standaloneSetup(new HelloController()).build();
//    }

    @Test
    public void getPersonInfo() throws Exception {
        String personInfo = "{\"name\":\"zsl\",\"age\":18}";
        // 通过mock对象执行http请求
        MvcResult mvcResult = mockMvc.perform(
                MockMvcRequestBuilders
                        // 针对person接口
                        .request(HttpMethod.GET, "/test/person")
                        // 发送json数据
//                        .contentType("application/json")
                        // 要发送的内容
//                        .content()
        )
                // 期望返回值为200
                .andExpect(MockMvcResultMatchers.status().isOk())
                // 期望返回的name字段为zsl
                .andExpect(MockMvcResultMatchers.jsonPath("$.data.name").value("dinky"))
                .andDo(print())
                .andReturn();

        // 打印响应体
        log.info(mvcResult.getResponse().getContentAsString());
    }
}
