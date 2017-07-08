package com.wuxian99.finance.basedata.web.spring;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.wuxian99.finance.basedata.domain.model.SigninUser;
import com.wuxian99.finance.basedata.support.util.SigninUserUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;
import org.springframework.web.util.UriUtils;

import java.io.UnsupportedEncodingException;

/**
 * Created by sxjiang on 2017/3/23.
 */
public class SigninCheckInterceptor extends HandlerInterceptorAdapter {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        HttpSession session = request.getSession();
        if(session.getAttribute("signinUser") == null){
            String servletPath = request.getServletPath();
            if(StringUtils.isNotEmpty(request.getQueryString())){
                servletPath = StringUtils.join(request.getServletPath(),"?",request.getQueryString());
            }
            response.sendRedirect(StringUtils.join(request.getContextPath(),"/toSignin?destination=",UriUtils.encodeQueryParam(servletPath,"utf-8")));
            return false;
        }
        SigninUserUtil.set((SigninUser) session.getAttribute("signinUser"));
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        SigninUserUtil.clear();
    }

    public static void main(String[] args) throws UnsupportedEncodingException {
        System.out.println(UriUtils.encodeQueryParam("http://fdp.99wuxian.com/fdp/uploadRecord/list?key=zzz","utf-8"));
        ;
    }
}
