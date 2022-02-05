package com.study.springcore.coursework4.aop;

import com.study.springcore.coursework4.dao.BookDao;
import com.study.springcore.coursework4.dao.OrderLogDao;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Component
@Aspect
public class OrderLogAspect {
    @Autowired
    private OrderLogDao orderLogDao;

    @Autowired
    private BookDao bookDao;

    @Pointcut("execution(* com.study.springcore.coursework4.service.BookService.buyOne(..))")
    private void buyOne() {
    }

    @Pointcut("execution(* com.study.springcore.coursework4.service.BookService.buyMany(..))")
    private void buyMany() {
    }

    @After(value = "buyOne()")
    public void logBuyOne(JoinPoint joinPoint) {
        Integer wid = (Integer) joinPoint.getArgs()[0];
        Integer bid = (Integer) joinPoint.getArgs()[1];
        Integer bookPrice = bookDao.getPrice(bid);
        orderLogDao.log(wid, bid, bookPrice, 1);
    }

    @After(value = "buyMany()")
    public void logBuyMany(JoinPoint joinPoint) {
        Integer wid = (Integer) joinPoint.getArgs()[0];
        Integer[] bids = (Integer[]) joinPoint.getArgs()[1];

        Map<Integer, Integer> booksAmount = groupByBid(bids); // key: bid, value: amount

        booksAmount.forEach((bid, amount) -> {
            Integer bookPrice = bookDao.getPrice(bid);
            orderLogDao.log(wid, bid, bookPrice, amount);
        });
    }

    private Map<Integer, Integer> groupByBid(Integer[] bids) {
        return Stream.of(bids)
                .collect(Collectors.groupingBy(
                        integer -> integer,
                        Collectors.summingInt(value -> 1)
                ));
    }
}
