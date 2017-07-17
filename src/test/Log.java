package test;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;

@Aspect
@Component
public class Log{
    @Pointcut("execution(* *(int,int))")
    public void declareExpression(){}

    @Order(1)
    @Before("declareExpression()")
    public void before(JoinPoint jp) {
        String methodName = jp.getSignature().getName();
        List args = Arrays.asList(jp.getArgs());
        System.out.println("-----before-----Name:" + methodName + "---Args:" + args);
    }

    @After("execution(* *(int,int))")
    public void after(JoinPoint jp) {
        String methodName = jp.getSignature().getName();
        List args = Arrays.asList(jp.getArgs());
        System.out.println("-----after-----Name:" + methodName + "---Args:" + args);
    }

    @AfterReturning(value = "execution(* *(int,int))", returning = "result")
    public void afterReturn(JoinPoint jp, Object result) {
        String methodName = jp.getSignature().getName();
        List args = Arrays.asList(jp.getArgs());
        System.out.println("-----afterReturn  -----Name:" + methodName + "---Args:" + args + "----result:" + result);
    }


    @AfterThrowing(value = "execution(* *(int,int))", throwing = "e")
    public void afterThrow(JoinPoint jp, Exception e) {
        String methodName = jp.getSignature().getName();
        List args = Arrays.asList(jp.getArgs());
        System.out.println("-----afterThrow  -----Name:" + methodName + "----Exception:" + e);
    }

//    @Around("execution(* *(int,int))")
//    public Object around(ProceedingJoinPoint pjp) {
//        System.out.println("around:"+pjp.getSignature()+Arrays.asList(pjp.getArgs()));
//        Object result=null;
//        try {
//            result=pjp.proceed();
//        } catch (Throwable throwable) {
//            throwable.printStackTrace();
//        }
//        return result;
//    }
}
