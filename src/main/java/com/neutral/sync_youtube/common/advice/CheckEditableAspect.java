package com.neutral.sync_youtube.common.advice;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;

@Aspect
public class CheckEditableAspect {
    @Around("execution(* com.neutral.sync_youtube.*.domain.set*(*))")
    public void doCheckEditable(ProceedingJoinPoint joinPoint) {
    }
}
