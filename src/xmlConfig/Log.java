package xmlConfig;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;

import java.util.Arrays;
import java.util.List;

public class Log {
    public void before(JoinPoint jp) {
        String methodName = jp.getSignature().getName();
        List args = Arrays.asList(jp.getArgs());
        System.out.println("-----before-----Name:" + methodName + "---Args:" + args);
    }

    public void after(JoinPoint jp) {
        String methodName = jp.getSignature().getName();
        List args = Arrays.asList(jp.getArgs());
        System.out.println("-----after-----Name:" + methodName + "---Args:" + args);
    }

    public void afterReturn(JoinPoint jp, Object result) {
        String methodName = jp.getSignature().getName();
        List args = Arrays.asList(jp.getArgs());
        System.out.println("-----afterReturn  -----Name:" + methodName + "---Args:" + args + "----result:" + result);
    }


    public void afterThrow(JoinPoint jp, Exception e) {
        String methodName = jp.getSignature().getName();
        List args = Arrays.asList(jp.getArgs());
        System.out.println("-----afterThrow  -----Name:" + methodName + "----Exception:" + e);
    }

//    public Object around(ProceedingJoinPoint pjp) {
//        System.out.println("around:" + pjp.getSignature() + Arrays.asList(pjp.getArgs()));
//        Object result = null;
//        try {
//            result = pjp.proceed();
//        } catch (Throwable throwable) {
//            throwable.printStackTrace();
//        }
//        return result;
//    }

}
