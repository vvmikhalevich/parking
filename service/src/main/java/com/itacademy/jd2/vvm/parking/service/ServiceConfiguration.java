package com.itacademy.jd2.vvm.parking.service;

import com.itacademy.jd2.vvm.parking.PersistanceConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@ComponentScan("com.itacademy.jd2.vvm.parking.service")
@Import({
    PersistanceConfiguration.class
})
public class ServiceConfiguration {

}
