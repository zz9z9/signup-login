package com.study.login.config;

import com.study.login.web.AuthenticationInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebMvcConfig implements WebMvcConfigurer {

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        AuthenticationInterceptor authInterceptor = new AuthenticationInterceptor();

        registry.addInterceptor(authInterceptor)
                .addPathPatterns(authInterceptor.loginEssential);
                // .excludePathPatterns(authInterceptor.loginInessential);
    }

}
