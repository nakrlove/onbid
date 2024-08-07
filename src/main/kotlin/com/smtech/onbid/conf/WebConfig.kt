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
            .allowedOrigins("http://localhost:3000")
//            .allowedMethods("*")
            .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS");
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