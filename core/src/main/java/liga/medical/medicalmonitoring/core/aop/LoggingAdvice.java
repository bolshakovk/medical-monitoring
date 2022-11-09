package liga.medical.medicalmonitoring.core.aop;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import model.LogDto;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;
import service.LogService;

@Slf4j
@Aspect
@Component
@RequiredArgsConstructor
public class LoggingAdvice {
    private final LogService logService;
    @Pointcut(value = "execution(* liga.medical.medicalmonitoring.core.service.*.*(..))")
    private void generalPointCut(){}

    @Around(value = "generalPointCut()")
    public Object applicationLogger(ProceedingJoinPoint proceedingJoinPoint) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        String className = proceedingJoinPoint.getTarget().getClass().toString();
        String methodName = proceedingJoinPoint.getSignature().getName();
        Object[] array = proceedingJoinPoint.getArgs();
        log.info("method has been invoked : " + className + ": with args: " + methodName
                +" in service: " + proceedingJoinPoint.getTarget() + mapper.writeValueAsString(array));

        Object obj = null;
        try {
            obj = proceedingJoinPoint.proceed();
            logService.addLogsInDBDebug((LogDto) obj);
        } catch (Throwable e) {
            log.error(e.getMessage());
            logService.addLogsInDBExeption((LogDto) obj);
            e.printStackTrace();
        }
        log.info(className + ":" + methodName + "() " + "Response: " + mapper.writeValueAsString(obj));
        return obj;
    }
}