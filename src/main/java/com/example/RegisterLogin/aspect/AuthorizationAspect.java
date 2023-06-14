package com.example.RegisterLogin.aspect;

//import com.example.RegisterLogin.util.JwtTokenUtil;
//import jakarta.servlet.http.HttpServletRequest;
//import org.aspectj.lang.ProceedingJoinPoint;
//import org.aspectj.lang.annotation.Around;
//import org.aspectj.lang.annotation.Aspect;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.core.annotation.Order;
//import org.springframework.stereotype.Component;
//import org.springframework.web.context.request.RequestContextHolder;
//import org.springframework.web.context.request.ServletRequestAttributes;
//
//@Aspect
//@Component
//@Order(1)
public class AuthorizationAspect {

//    @Autowired
//    JwtTokenUtil jwtTokenUtil;
//
//    @Around("execution(* com.example.RegisterLogin.controller.*.*(..))")
//    public Object checkAuthorizationToken(ProceedingJoinPoint joinPoint) throws Throwable {
//        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes()).getRequest();
//        String jwtToken = request.getHeader("Authorization");
//        if (jwtToken == null) {
//            throw new RuntimeException("Missing or invalid Authorization token");
//        }
//
////        String jwtToken = authorizationHeader.substring(7); // Remove "Bearer " prefix
//        if (!jwtTokenUtil.isJwtValid(jwtToken)) {
//            throw new RuntimeException("Invalid JWT token");
//        }
//
//        return joinPoint.proceed();
//    }
}
