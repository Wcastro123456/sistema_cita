package com.example.sistema_cita;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.jdbc.autoconfigure.DataSourceAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication(exclude = DataSourceAutoConfiguration.class)
@ComponentScan(basePackages = {"com.citas", "com.example.sistema_cita"})
public class SistemaCitaApplication {

    public static void main(String[] args) {
        SpringApplication.run(SistemaCitaApplication.class, args);
    }
}
