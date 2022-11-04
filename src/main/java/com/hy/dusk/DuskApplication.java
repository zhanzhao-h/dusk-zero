package com.hy.dusk;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import tk.mybatis.spring.annotation.MapperScan;

@Slf4j
@SpringBootApplication
@MapperScan(basePackages = "com.hy.dusk.mapper")
public class DuskApplication {
    public static void main(String[] args) {
        SpringApplication.run(DuskApplication.class);
    }
}
