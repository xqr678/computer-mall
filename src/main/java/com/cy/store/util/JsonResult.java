package com.cy.store.util;


import java.io.Serializable;

/**
 * @author XQR
 * @date 2022/10/6 2022/10/6
 * @dsecription 类的描述和介绍
 */
public class JsonResult<E> implements Serializable {
//    响应的状态码
    private Integer state;
//    描述信息
    private String message;
//    对应的数据
    private E data;

    public JsonResult() {
        super();
    }

    public JsonResult(Integer state) {
        super();
        this.state = state;
    }

    /** 出现异常时调用 */
    public JsonResult(Throwable e) {
        super();
        // 获取异常对象中的异常信息
        this.message = e.getMessage();
    }

    public JsonResult(Integer state, E data) {
        super();
        this.state = state;
        this.data = data;
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public E getData() {
        return data;
    }

    public void setData(E data) {
        this.data = data;
    }
}
