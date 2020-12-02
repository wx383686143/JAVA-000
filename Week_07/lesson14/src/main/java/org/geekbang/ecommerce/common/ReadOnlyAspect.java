package org.geekbang.ecommerce.common;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;

@Aspect
@Component
public class ReadOnlyAspect {

    @Pointcut("@annotation(org.geekbang.ecommerce.common.ReadOnly)")
    private void pointCut() {}

    @Before("pointCut()")
    public void beforeMethod(JoinPoint joinPoint) {
        try {
            MethodSignature signature = (MethodSignature) joinPoint.getSignature();
            Method method = signature.getMethod();
            System.out.println(method.toString());
            ReadOnly readOnly = method.getAnnotation(ReadOnly.class);
            if(readOnly != null) {
                DynamicDataSource.setDataSource("slave");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @After("pointCut()")
    public void after() {
        DynamicDataSource.clearDataSource();
    }
}
