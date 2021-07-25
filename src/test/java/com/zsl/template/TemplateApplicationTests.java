package com.zsl.template;

import com.zsl.template.entity.PersonInfo;
import com.zsl.template.util.JsonUtil;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class TemplateApplicationTests {

    @Test
    void contextLoads() {
        PersonInfo personInfo1 = new PersonInfo();
        personInfo1.setAge(18);
        personInfo1.setName("zsl");
        System.out.println(JsonUtil.javaBeanToJsonString(personInfo1));
        PersonInfo personInfo = JsonUtil.JsonStringToJavaBean("{\"name\":\"zsl\",\"age\":18}", PersonInfo.class);
        System.out.println(personInfo.getAge() + "   " + personInfo.getName());
    }

}
