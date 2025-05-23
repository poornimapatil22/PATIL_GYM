package com.xworkz.gym.configuration;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

import javax.sql.DataSource;

@Configuration
@EnableWebMvc
@ComponentScan("com.xworkz.gym")
@Slf4j
public class WebConfiguaration {
        int sizeOfImg=5*1024*1024;
        public WebConfiguaration(){
            log.info("const of config");
        }
        @Bean
        public LocalContainerEntityManagerFactoryBean localContainerEntityManagerFactoryBean(){
            LocalContainerEntityManagerFactoryBean bean=new LocalContainerEntityManagerFactoryBean();
            bean.setDataSource(dataSource());
            log.info("this is information of PATTL_GYM");
            bean.setPackagesToScan("com.xworkz.gym.entity");
            bean.setJpaVendorAdapter(new HibernateJpaVendorAdapter());
            return bean;

        }

        @Bean
        public DataSource dataSource(){
            DriverManagerDataSource drivermngrdatasouce=new DriverManagerDataSource();
            drivermngrdatasouce.setDriverClassName("com.mysql.cj.jdbc.Driver");
            drivermngrdatasouce.setUsername("root");
            drivermngrdatasouce.setUrl("jdbc:mysql://localhost:3306/patil_gym_db");
            drivermngrdatasouce.setPassword("Poornima!22");
            return drivermngrdatasouce;
        }

        @Bean
        public PasswordEncoder passwordEncoder(){
            return new BCryptPasswordEncoder();
        }

        @Bean
        public ViewResolver viewResolver() {
            return new InternalResourceViewResolver("/",".jsp");
        }

        @Bean(name="multipartResolver")
        public CommonsMultipartResolver multipartResolver(){
            CommonsMultipartResolver commonsMultipartResolver=new CommonsMultipartResolver();
            commonsMultipartResolver.setMaxUploadSize(sizeOfImg);
            commonsMultipartResolver.setMaxInMemorySize(sizeOfImg);
            return commonsMultipartResolver;
        }

    }
