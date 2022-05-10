package com.zsl.template;

import com.zsl.template.entity.PersonInfo;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpMethod;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import javax.annotation.Resource;

import static org.mockito.Mockito.when;
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
// 会注入所有的bean,重量级
//@SpringBootTest
// 只注入指定的bean,轻量级
//@WebMvcTest(HelloController.class)
// 需要注入bean
@ExtendWith(SpringExtension.class)
public class PersonInfoTest2 {

    @Resource
    private MockMvc mockMvc;

    // 注入mockBean
//    @MockBean
//    private PersonService personService;

    @Test
    public void getPersonInfo() throws Exception {
        String personInfo = "{\"name\":\"zsl\",\"age\":18}";

        // 打桩
        // 当执行personService.getPersonInfo()方法时 ,直接返回指定的结果
//        when(personService.getPersonInfo()).thenReturn(PersonInfo.builder().name("zsl").age(18).build());

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
                .andExpect(MockMvcResultMatchers.jsonPath("$.data.name").value("zsl"))
                .andDo(print())
                .andReturn();

        // 打印响应体
        log.info(mvcResult.getResponse().getContentAsString());
    }
}

/**
 * //模拟GET请求：
 * mockMvc.perform(MockMvcRequestBuilders.get("/user/{id}", userId));
 *
 * //模拟Post请求：
 * mockMvc.perform(MockMvcRequestBuilders.post("uri", parameters));
 *
 * //模拟文件上传：
 * mockMvc.perform(MockMvcRequestBuilders.multipart("uri").file("fileName", "file".getBytes("UTF-8")));
 *
 *
 * //模拟session和cookie：
 * mockMvc.perform(MockMvcRequestBuilders.get("uri").sessionAttr("name", "value"));
 * mockMvc.perform(MockMvcRequestBuilders.get("uri").cookie(new Cookie("name", "value")));
 *
 * //设置HTTP Header：
 * mockMvc.perform(MockMvcRequestBuilders
 *                         .get("uri", parameters)
 *                         .contentType("application/x-www-form-urlencoded")
 *                         .accept("application/json")
 *                         .header("", ""));
 */
