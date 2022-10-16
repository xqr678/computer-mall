package com.cy.store.interceptor;

import org.springframework.web.servlet.HandlerInterceptor;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/** 定义处理器拦截器 */
public class LoginInterceptor implements HandlerInterceptor {
    /**
     * 检测全局session对象中是否有uid数据，如果有则放行，如果没有重定向到登录界面
     * @param request 请求对象
     * @param response 响应对象
     * @param handler 处理器（url+Controller:映射）
     * @return 如果返回值为true表示放行当前的请求，如果返回为false则表示拦截当前的请求
     * @throws Exception
     */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
//        HttpServletRequest对象来获取session对象
        if (request.getSession().getAttribute("uid") == null) {
            response.sendRedirect("/web/login.html");
            return false;
        }
        return true;
    }
}
