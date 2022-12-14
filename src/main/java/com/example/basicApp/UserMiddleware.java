package com.example.basicApp;

import com.example.basicApp.data.Dao;
import org.aopalliance.intercept.Joinpoint;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.AutoConfigureBefore;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@EnableAspectJAutoProxy
@Configuration
public class UserMiddleware {
//    private Logger logger = LoggerFactory.getLogger(this.getClass());
//
//    @AutoConfigureBefore(Business business)
//    public void before(Joinpoint joinpoint) {
//        logger.info("checking for user access");
//    }
}
