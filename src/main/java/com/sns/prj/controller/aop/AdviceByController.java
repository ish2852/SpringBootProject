package com.sns.prj.controller.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Service;

import com.sns.prj.controller.dto.ResultDto;

@Service
@Aspect
public class AdviceByController {
	@Pointcut("execution(* com.sns.prj.controller..*Api.*(..)) && !execution(* com.sns.prj.controller..*Api.delete*(..)) && !execution(* com.sns.prj.controller..*Api.*Follow*(..))")
	public void allPointcutByControllerApi() {
	}
	
	@Pointcut("execution(* com.sns.prj.controller..*Api.delete*(..)) && !execution(* com.sns.prj.controller..*Api.*Follow*(..))")
	public void deletePointcutByControllerApi() {
	}
	
	@Pointcut("execution(* com.sns.prj.controller..*Api.*Follow*(..))")
	public void followPointcutByControllerApi() {
	}

	@Around("allPointcutByControllerApi()")
	public ResultDto allAdviceByControllerApi(ProceedingJoinPoint pjp) throws Throwable {

		Object data = pjp.proceed();

		ResultDto resultDto = new ResultDto();
		if (data != null) {
			resultDto.setCode(200);
			resultDto.setMessage("Success");
			resultDto.setData(data);
		} else {
			resultDto.setCode(500);
			resultDto.setMessage("Error");
			resultDto.setData(null);
		}
		return resultDto;
	}
	
	@Around("deletePointcutByControllerApi()")
	public ResultDto deleteAdviceByControllerApi(ProceedingJoinPoint pjp) throws Throwable {

		int data = (int) pjp.proceed();

		ResultDto resultDto = new ResultDto();
		if (data == 1) {
			resultDto.setCode(200);
			resultDto.setMessage("Success");
			resultDto.setData(null);
		} else {
			resultDto.setCode(500);
			resultDto.setMessage("Error");
			resultDto.setData(null);
		}
		return resultDto;
	}
	
	@Around("followPointcutByControllerApi()")
	public ResultDto followAdviceByControllerApi(ProceedingJoinPoint pjp) throws Throwable {
		int data = (int) pjp.proceed();

		ResultDto resultDto = new ResultDto();
		if (data != 0) {
			resultDto.setCode(200);
			resultDto.setMessage("ok");
			resultDto.setData("Success");
		} else {
			resultDto.setCode(500);
			resultDto.setMessage("no");
			resultDto.setData("Fail");
		}
		return resultDto;
	}
}
