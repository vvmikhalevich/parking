package com.itacademy.jd2.vvm.parking.service.impl.spring;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.itacademy.jd2.vvm.parking.dao.api.IBrandDao;
import com.itacademy.jd2.vvm.parking.service.IBrandService;

public class ServiceSpringContextExample {
	private static final Logger LOGGER = LoggerFactory.getLogger(ServiceSpringContextExample.class);

	public static void main(String[] args) {
		ApplicationContext context = new ClassPathXmlApplicationContext("service-context.xml");
		LOGGER.info(context.getBean(IBrandService.class).toString());

		// TODO show multiple candidates with Qualifier

	}
}
