package com.apirest.apirest.config

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration


@EnableSwagger2
@Configuration
class SwaggerConfig {

    @Bean
    fun productApi(): Docket {
        return Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.sceballosdev.backend"))
                .paths(regex("/rest.*"))
                .build()
    }
}
