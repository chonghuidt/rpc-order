package com.lp;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * @auther lp
 * @date 2020/6/20 0020 16:43
 */
@Configuration
@ComponentScan("com.lp")
public class BootStrap {
        public static void main(String [] args){
            ApplicationContext applicationContext=
                    new AnnotationConfigApplicationContext(BootStrap.class);

            }

}
