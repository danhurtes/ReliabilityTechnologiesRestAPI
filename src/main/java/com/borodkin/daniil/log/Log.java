package com.borodkin.daniil.log;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class Log {
	Logger logger = LoggerFactory.getLogger(Log.class);

	@Pointcut(value = "execution(* com.borodkin.daniil.controllers.CodeController.*(..))")
	public void pointCut() {

	}

	@Around("pointCut()")
	public Object applicationLogger(ProceedingJoinPoint point) throws Throwable {
		ObjectMapper mapper = new ObjectMapper();

		String methodName = point.getSignature().getName();
		String className = point.getTarget().getClass().getName();
		Object[] args = point.getArgs();

		logger.info(String.format("Вызванный метод - %s: %s() с аргументами: %s", className, methodName, mapper.writeValueAsString(args)));

		Object proceed = point.proceed();
		logger.info(String.format("Метод %s: %s() отработал и вернул - %s", className, methodName, mapper.writeValueAsString(proceed)));
		return proceed;
	}
}
