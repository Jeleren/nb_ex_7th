package com.jeleren.utils;

import com.jeleren.bean.UserInfo;
import org.springframework.web.servlet.HandlerInterceptor;
import java.io.PrintWriter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.web.servlet.ModelAndView;
import com.alibaba.fastjson.JSONObject;
//import com.jeleren.charles.model.Admin;
//import com.xforce.charles.model.Login;
import com.jeleren.utils.JWT;
import com.jeleren.utils.ResponseData;

public class TokenInterceptor implements HandlerInterceptor {

    public void afterCompletion(HttpServletRequest request,
                                HttpServletResponse response, Object handler, Exception arg3)
            throws Exception {
    }

    public void postHandle(HttpServletRequest request, HttpServletResponse response,
                           Object handler, ModelAndView model) throws Exception {
    }

    //拦截每个请求
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response,
                             Object handler) throws Exception {
        response.setCharacterEncoding("utf-8");
        String token = request.getHeader("Authorization");
        System.out.println("interceptor token    "+token);
        ResponseData responseData = ResponseData.ok();
        //token不存在
        if (null != token) {
            UserInfo user = JWT.unsign(token, UserInfo.class);
            if(user!=null) {
                return true;
            } else {
                responseData = ResponseData.forbidden();
                responseMessage(response, response.getWriter(), responseData);
                return false;
            }
        } else {
            responseData = ResponseData.forbidden();
            responseMessage(response, response.getWriter(), responseData);
            return false;
        }
    }

    //请求不通过，返回错误信息给客户端
    private void responseMessage(HttpServletResponse response, PrintWriter out, ResponseData responseData) {
        responseData = ResponseData.forbidden();
        response.setContentType("application/json; charset=utf-8");
        String json = JSONObject.toJSONString(responseData);
        out.print(json);
        out.flush();
        out.close();
    }

}
