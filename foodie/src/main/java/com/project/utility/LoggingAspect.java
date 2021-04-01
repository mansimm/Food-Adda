package com.project.utility;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;


@Component
@Aspect
public class LoggingAspect {

	@AfterThrowing(pointcut = "execution(* com.project.repository.*Repo.*(..))", throwing = "exception")	
	public void logExceptionFromDAO(Exception exception) throws Exception {
		log(exception);
	}
	
	@AfterThrowing(pointcut = "execution(* com.project.service.*Impl.*(..))", throwing = "exception")	
	public void logExceptionFromService(Exception exception) throws Exception {
			log(exception);
	}

	
	private void log(Exception exception) {
		Logger logger = LogManager.getLogger(this.getClass());
		if(exception.getMessage()!=null && 
				(exception.getMessage().contains("Service") || exception.getMessage().contains("Validator"))){
			logger.error(exception.getMessage());
		}
		else{
			logger.error(exception.getMessage(), exception);
		}
	}

}
