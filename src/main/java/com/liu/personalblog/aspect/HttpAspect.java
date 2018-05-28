package com.liu.personalblog.aspect;

import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class HttpAspect {

	private static final Logger logger = LoggerFactory
			.getLogger(HttpAspect.class);

	@Pointcut("execution(public * com.liu.personalblog.Controller.*.*(..))")
	public void pointCut() {
	}

	@Before("pointCut()")
	public void doBefore() {
		logger.info("-------------------------------------------------------------------------------");
	}

	@After("pointCut()")
	public void doAfter() {
		logger.info("+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");
	}
}
