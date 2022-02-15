package com.study.springcore.coursework3.aop;

import com.study.springcore.coursework3.template.QueryLogDao;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class QueryLogger {
    @Autowired
    private QueryLogDao queryLogDao;

    @Before(value = "execution(* com.study.springcore.coursework3.template.EmployeeDao.queryAll(..))")
    public void logBeforeQuery(JoinPoint joinPoint) {
        queryLogDao.add(joinPoint.getSignature().getName());
    }
}
