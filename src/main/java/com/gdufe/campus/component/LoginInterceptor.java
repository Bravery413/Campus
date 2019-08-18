package com.gdufe.campus.component;


import com.gdufe.campus.pojo.DTO.UserDTO;
import org.springframework.web.servlet.HandlerInterceptor;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
/**
 * 登陆检查，
 */
public class LoginInterceptor implements HandlerInterceptor {
    public final static String USER_SESSON = "loginUser";
    //目标方法执行之前
    @Override
    public boolean preHandle(HttpServletRequest request,
                             HttpServletResponse response,
                             Object handler) throws Exception {
        //查询session,如果有则放行,没有则跳到登录页面
        UserDTO user = (UserDTO)request.getSession().getAttribute(USER_SESSON);
        if (user==null||user.getId()==null)  {
            response.sendRedirect("/user/loginPage");
            return false;
        }
        return true;
    }
//    @Override
//    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
//        //postHandle：请求处理之后进行调用，但是在视图被渲染之前（Controller方法调用之后）
//    }
//
//    @Override
//    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
//        //afterCompletion：在整个请求结束之后被调用，
//        // 也就是在DispatcherServlet 渲染了对应的视图之后执行
//        // （主要是用于进行资源清理工作）
//    }
}
