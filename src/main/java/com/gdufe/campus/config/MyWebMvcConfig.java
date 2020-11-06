package com.gdufe.campus.config;

        import com.gdufe.campus.component.LoginInterceptor;
        import org.springframework.context.annotation.Configuration;
        import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
        import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @author bravery
 * @date 2019/8/15 14:49
 */
@Configuration
public class MyWebMvcConfig implements WebMvcConfigurer {
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        System.out.println("配置类启动");
        registry.addInterceptor(new LoginInterceptor())
                //拦截路径
                .addPathPatterns("/**")
                //非拦截路径
                .excludePathPatterns("/user/**")
                .excludePathPatterns("user/**")
                .excludePathPatterns("/captcha/**")
                .excludePathPatterns("/css/**")
                .excludePathPatterns("/js/**")
                .excludePathPatterns("/icons/**")
                .excludePathPatterns("/date")
                .excludePathPatterns("/sort")
                .excludePathPatterns("/images/**");
    }

}
