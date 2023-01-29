package com.cheese.util.codenav.aspect;

import com.alibaba.fastjson.JSON;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.*;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Executor;

/**
 * @author wangzh
 * tip:如有必要，通过数据库存储错误请求响应信息
 * RequestAttributes requestAttributes = RequestContextHolder.getRequestAttributes();
 * ((ServletRequestAttributes) requestAttributes).getRequest()获取request对象，获取HTTP请求的数据
 * @description 系统日志，切点为所有controller标注的方法
 */
@Aspect
@Order
@Configuration
public class WebLogAspect {

    private static final Logger log = LoggerFactory.getLogger(WebLogAspect.class);

    private static ThreadLocal<Long> startTime = new ThreadLocal<>();

    @Autowired
    private Executor taskExecutor;

    /**
     * 切点
     */
    @Pointcut("execution(public * com.cheese.util.codenav.*.*.controller..*(..))")
    public void logPoint() {

    }

    /**
     * 前置通知
     */
    @Before("logPoint()")
    public void before() {
        startTime.set(System.currentTimeMillis());
    }

    /**
     * 后置通知
     *
     * @param joinPoint joinPoint对象
     */
    @AfterReturning(pointcut = "logPoint()", returning = "returnValue")
    public void doAfter(JoinPoint joinPoint, Object returnValue) {
        long costTime = System.currentTimeMillis() - startTime.get();
        log.info("=======> 请求时长:{}ms", costTime);
        startTime.remove();
        //在当前线程先获取请求中信息
        taskExecutor.execute(() -> logServiceHandler(joinPoint, returnValue));
    }

    /**
     * 切面操作
     *
     * @param joinPoint joinPoint对象
     */
    private void logServiceHandler(JoinPoint joinPoint, Object returnValue) {
        try {
            //获取类名
            String className = joinPoint.getTarget().getClass().getName();
            //获取签名
            Signature signature = joinPoint.getSignature();
            MethodSignature methodSignature = (MethodSignature) signature;
            //获取方法名
            String methodName = signature.getName();
            log.info("=======> 操作类：{},操作方法：{}", className, methodName);
            //获取请求参数
            if (joinPoint.getArgs() != null && joinPoint.getArgs().length > 0) {
                Object parameter = this.getParameter(methodSignature.getMethod(), joinPoint.getArgs());
                log.info("=======> 参数:{}", JSON.toJSONString(parameter));
            }
            log.info("=======> 响应数据：{}", returnValue);
        } catch (Exception e) {
            e.printStackTrace();
            log.info("=======> 日志方法异常");
            log.error(e.getMessage(), e);
        }
    }

    /**
     * 异常拦截
     * 有全局异常处理，此处算是多做一次
     *
     * @param joinPoint
     * @param e
     */
    @AfterThrowing(pointcut = "logPoint()", throwing = "e")
    public void doAfterThrowing(JoinPoint joinPoint, Throwable e) {
        //请求方法的参数并序列化为JSON格式字符串
        taskExecutor.execute(() -> {
            try {
                String className = joinPoint.getTarget().getClass().getName();
                Signature signature = joinPoint.getSignature();
                String methodName = signature.getName();
                log.info("=======> 异常拦截：操作类：{},操作方法：{}", className, methodName);
                log.error("=======> 操作方法异常", e);
            } catch (Exception e1) {
                log.info("=======> 异常拦截方法异常");
                e1.printStackTrace();
            }
        });
    }

    /**
     * 根据方法和传入的参数获取请求参数
     */
    private Object getParameter(Method method, Object[] args) {
        List<Object> argList = new ArrayList<>();
        Parameter[] parameters = method.getParameters();
        Map<String, Object> map = new HashMap<>(16);
        for (int i = 0; i < parameters.length; i++) {
            //将RequestBody注解修饰的参数作为请求参数
            RequestBody requestBody = parameters[i].getAnnotation(RequestBody.class);
            if (requestBody != null) {
                argList.add(args[i]);
            }
            //将RequestParam注解修饰的参数作为请求参数
            RequestParam requestParam = parameters[i].getAnnotation(RequestParam.class);
            if (requestParam != null) {
                String key = parameters[i].getName();
                if (!StringUtils.isEmpty(requestParam.value())) {
                    key = requestParam.value();
                }
                map.put(key, args[i]);
                argList.add(map);
            }
            //将PathVariable注解修饰的参数作为请求参数
            PathVariable pathVariable = parameters[i].getAnnotation(PathVariable.class);
            if (pathVariable != null) {
                String key = parameters[i].getName();
                if (!StringUtils.isEmpty(pathVariable.value())) {
                    key = pathVariable.value();
                }
                map.put(key, args[i]);
                argList.add(map);
            }
        }
        return argList;
    }
}
