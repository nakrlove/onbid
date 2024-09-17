package com.smtech.onbid.conf

import org.springframework.context.annotation.Configuration
import org.springframework.web.servlet.config.annotation.CorsRegistry
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer

@Configuration
class WebConfig : WebMvcConfigurer {

    override fun addCorsMappings(registry: CorsRegistry) {
        registry.addMapping("/**")
            .allowedOrigins(
                "http://10.211.55.13:3000",   // 첫 번째 React 앱의 주소
                "http://localhost:3000"       // 로컬 개발 환경
            )
//            .allowedMethods("*")
            .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS")
//            .allowedHeaders("Content-Type, Authorization")
//        headers.add("Access-Control-Allow-Origin", "*");
//        headers.add("Access-Control-Allow-Methods", "POST, GET, OPTIONS, DELETE");
//        headers.add("Access-Control-Allow-Headers", "Content-Type, Authorization");
    }



    override fun addResourceHandlers(registry: ResourceHandlerRegistry) {
        registry.addResourceHandler("/**")
//            .addResourceLocations("file:/Users/nakrlove/Desktop/dev/onbidweb/public/")
            .addResourceLocations("file:/Users/nakrlove/Desktop/dev/onbidweb/build/")
    }

    override fun addViewControllers(registry: ViewControllerRegistry) {
        registry.addViewController("/{spring:[^.]*}")
            .setViewName("forward:/index.html")
    }
}