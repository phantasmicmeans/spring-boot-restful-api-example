package com.sw.project.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

//@Aspect
//@Component
public class RestControllerAspect {

	protected final Logger log = LoggerFactory.getLogger(this.getClass());
	
//	@Pointcut("execution(* com.sw.project.controller.*(..)")
//	private void pointCutBeforeApi() {
//	}
	
//@Before("pointCutBeforeApi()")
//	public void loggerBeforeRestCall(JoinPoint joinPoint) throws Throwable{
//		
//		log.info("======== AOP Before RestAPI Call ==========" + joinPoint);
//	}
//	
}
