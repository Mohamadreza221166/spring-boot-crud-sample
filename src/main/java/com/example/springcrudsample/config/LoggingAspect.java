package com.example.springcrudsample.config;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.env.Environment;
import org.springframework.core.env.Profiles;

@Aspect
public class LoggingAspect {

    private final Environment env;

    public LoggingAspect(Environment env) {
        this.env = env;
    }

    @Pointcut(
        "within(@org.springframework.stereotype.Repository *)" +
        " || within(@org.springframework.stereotype.Service *)" +
        " || within(@org.springframework.web.bind.annotation.RestController *)"
    )
    public void springBeanPointcut() {}


    @Pointcut(
        "within(com.example.springcrudsample.repository..*)" + " || within(com.example.springcrudsample.service..*)" + " || within(com.example.springcrudsample.web.rest..*)"
    )
    public void applicationPackagePointcut() {}

    private Logger logger(JoinPoint joinPoint) {
        return LoggerFactory.getLogger(joinPoint.getSignature().getDeclaringTypeName());
    }

    @AfterThrowing(pointcut = "applicationPackagePointcut() && springBeanPointcut()", throwing = "e")
    public void logAfterThrowing(JoinPoint joinPoint, Throwable e) {
        String signature = joinPoint.getSignature().getName();
        if (env.acceptsProfiles(Profiles.of(Constants.SPRING_PROFILE_DEVELOPMENT))) {
            logger(joinPoint).error("خطا در حین اجرای عملیات: " + e.getMessage());
        } else {
            logger(joinPoint).error("خطا در حین اجرای عملیات");
        }
    }

    @Around("applicationPackagePointcut() && springBeanPointcut()")
    public Object logAround(ProceedingJoinPoint joinPoint) throws Throwable {
        Logger log = logger(joinPoint);
        String signature = joinPoint.getSignature().getName();
        if (log.isDebugEnabled()) {
            log.debug("در حال اجرای: " + signature);
        }
        try {
            Object result = joinPoint.proceed();
            if (log.isDebugEnabled()) {
                log.debug("خروج در" + signature);
            }
            return result;
        } catch (IllegalArgumentException e) {
            log.error(": بروز خطای ناخواسته در حین logging" + signature);
            throw e;
        }
    }
}
