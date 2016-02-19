package com.litvinov.bank.accounting.service.logging;

import java.util.Arrays;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;

@Aspect
public class LoggingAspect {

	@After("execution(* com.litvinov.bank.accounting.service.models.UserInterface.*(..))")
	public void logAfter(JoinPoint joinPoint) {

		System.out.println("Method : " + joinPoint.getSignature().getName());
		System.out.println("Method arguments : " + Arrays.toString(joinPoint.getArgs()));
		System.out.println("-------------------");

	}

}
