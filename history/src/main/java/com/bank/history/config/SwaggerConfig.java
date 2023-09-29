package com.bank.history.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Класс конфигурации Сваггера
 * интерфейс доступен по адресу localhost:8088/api/history/swagger-ui/index.html
 * описание доступно по адресу(возвращает json) localhost:8088/api/history/v3/api-docs
 * документация доступна по пути  localhost:8088/api/history/v3/api-docs.yaml
 */
@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("History API")
                        .version("1.0.0")
                        .description("This is a sample History API")
                        .contact(new Contact().url("http://какой-то адрес")
                                .name("SomeName")
                                .email("someEmail@mail.ru")));
    }
}
