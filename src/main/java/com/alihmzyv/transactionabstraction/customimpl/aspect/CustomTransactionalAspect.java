package com.alihmzyv.transactionabstraction.customimpl.aspect;

import com.alihmzyv.transactionabstraction.customimpl.util.CustomSimpleTransactionManager;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Aspect
@Component
@Slf4j
public class CustomTransactionalAspect {
    private final CustomSimpleTransactionManager transactionManager;

    @SneakyThrows
    @Around("@annotation(com.alihmzyv.transactionabstraction.customimpl.annotation.CustomTransactional)")
    public Object advice(ProceedingJoinPoint joinPoint) {
        transactionManager.beginTransaction();
        try {
            Object result = joinPoint.proceed();
            transactionManager.commit();
            return result;
        } catch (RuntimeException e) {
            transactionManager.rollback();
            throw e;
        } catch (Throwable e) {
            transactionManager.commit();
            throw e;
        }
    }
}
