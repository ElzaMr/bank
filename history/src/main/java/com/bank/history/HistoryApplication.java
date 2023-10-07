package com.bank.history;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
/**
 * Этот микросервис отвечает за историю и за все операции, которые отвечают за логику и за изменение сущностей данного микросервиса
 * в каждом микросервисе есть таблица с названием audit, а этот микросервис собирает со всех микросервисов данные с этих таблиц
 * агрегирует информацию, так же может отдавать информацию из одного микросервиса эта логика предполагается за счёт хранения
 * технического идентификатора каждого аудита.
 */
@SpringBootApplication
@EnableAspectJAutoProxy
@EnableDiscoveryClient
public class HistoryApplication {
    public static void main(String[] args) {
        SpringApplication.run(HistoryApplication.class, args);
    }
}
