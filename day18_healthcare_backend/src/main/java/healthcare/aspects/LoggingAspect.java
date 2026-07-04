package healthcare.aspects;

import java.time.LocalDateTime;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;

@Aspect //cross cutting concern (recurring task - logging)
@Component //to declare it as a spring bean
@Slf4j //injects  "log" field - Logger - trace -> debug -> info -> warn -> error
public class LoggingAspect {
	//before advice 
	@Before(".....")
	public void interceptForLogging(JoinPoint joinPoint)
	{
		log.info("Before advice fired {} at TS {}",joinPoint,LocalDateTime.now());
	}
	

}
