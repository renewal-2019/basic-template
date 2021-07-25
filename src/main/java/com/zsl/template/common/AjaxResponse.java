package com.zsl.template.common;

import lombok.Data;

/**
 * AjaxResponse
 *
 * @author swiftzsl
 * @date 2021/7/25 13:25
 */
@Data
public class AjaxResponse<T> {
    /**
     * 请求是否处理成功
     */
    private boolean isOk;

    /**
     * 请求响应状态码（200、400、500）
     */
    private int code;

    /**
     * 请求结果描述信息
     */
    private String message;

    /**
     * 请求结果数据（通常用于查询操作）
     */
    private T data;

    private AjaxResponse(){}

    /**
     * 请求成功的响应，不带查询数据（用于删除、修改、新增接口）
     *
     * @return
     */
    public static AjaxResponse success(){
        AjaxResponse ajaxResponse = new AjaxResponse();
        ajaxResponse.setOk(true);
        ajaxResponse.setCode(200);
        ajaxResponse.setMessage("请求响应成功!");
        return ajaxResponse;
    }

    /**
     * 请求成功的响应，带有查询数据（用于数据查询接口）
     *
     * @param data
     * @return
     */
    public static <T> AjaxResponse success(T data){
        AjaxResponse ajaxResponse = new AjaxResponse();
        ajaxResponse.setOk(true);
        ajaxResponse.setCode(200);
        ajaxResponse.setMessage("请求响应成功!");
        ajaxResponse.setData(data);
        return ajaxResponse;
    }

    /**
     * 请求成功的响应，带有查询数据（用于数据查询接口）
     *
     * @param data
     * @param message
     * @return
     */
    public static <T> AjaxResponse success(T data,String message){
        AjaxResponse ajaxResponse = new AjaxResponse();
        ajaxResponse.setOk(true);
        ajaxResponse.setCode(200);
        ajaxResponse.setMessage(message);
        ajaxResponse.setData(data);
        return ajaxResponse;
    }
}
