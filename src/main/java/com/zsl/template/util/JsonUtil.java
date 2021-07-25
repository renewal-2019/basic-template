package com.zsl.template.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * JsonUtil
 *
 * @author swiftzsl
 * @date 2021/7/25 13:47
 */
public class JsonUtil<T> {
    private static ObjectMapper objectMapper = new ObjectMapper();

    /**
     * java对象装换为json
     *
     * @param targetObj
     * @param <T>
     * @return
     */
    public static <T> String javaBeanToJsonString(T targetObj) {
        String result = null;
        try {
            result = objectMapper.writeValueAsString(targetObj);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return result;
    }

    /**
     * json字符创转换为java对象
     *
     * 注意 : 在类的定义处加上  @JsonIgnoreProperties(ignoreUnknown = true) 可以解决类定义字段少于json字段时异常问题
     *
     * @param jsonString
     * @param targetClass
     * @param <T>
     * @return
     */
    public static <T> T JsonStringToJavaBean(String jsonString, Class<T> targetClass) {
        T result = null;
        try {
            result = objectMapper.readValue(jsonString,targetClass);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return result;
    }
}
