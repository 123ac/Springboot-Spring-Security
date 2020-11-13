package com.hjl.springsecurity.config;

import com.hjl.springsecurity.controller.SystemLogController;
import com.hjl.springsecurity.entity.SysLog;
import com.hjl.springsecurity.entity.SysUser;
import com.hjl.springsecurity.service.SysLogService;
import com.hjl.springsecurity.util.IpUtiles;
import com.hjl.springsecurity.util.JsonUtiles;
import nl.bitwalker.useragentutils.Browser;
import nl.bitwalker.useragentutils.UserAgent;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.lang.reflect.Method;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;

/**
 * @Author: hjl
 * @Date: 2020/11/13 0013 10:22
 */
@Aspect
@Component
@SuppressWarnings("all")
public class SystemLogAspect {

    @Autowired
    private SysLogService sysLogService;

    //本地异常日志记录对象
    private static final Logger logger = LoggerFactory.getLogger(SystemLogAspect.class);
    //Service层切点
    @Pointcut("@annotation(com.hjl.springsecurity.config.SystemLogService)")
    public void serviceAspect(){
    }

    //Controller层切点
    @Pointcut("@annotation(com.hjl.springsecurity.controller.SystemLogController)")
    public void controllerAspect(){
    }

    /**
     * @Description  前置通知  用于拦截Controller层记录用户的操作
     * @date 2018年9月3日 10:38
     */
    @Before("controllerAspect()")
    public void doBefore(JoinPoint joinPoint){
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        //读取用户
        String  name = SecurityContextHolder.getContext().getAuthentication().getName();
        //获取ip
        String ip = IpUtiles.getRealIp(request);
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
        try {
            //*========控制台输出=========*//
//            System.out.println("==============前置通知开始==============");
//            System.out.println("请求接口 uri："+request.getRequestURI().toString()); //接口
////            logger.info("URL : " + request.getRequestURL().toString()); //完整地址
//            System.out.println("请求方式 method："+request.getMethod());
////            System.out.println("请求方法：" + joinPoint.getSignature().toString());
//            System.out.println("方法描述 method_describe：" + getControllerMethodDescription(joinPoint));
//            System.out.println("参数信息 params:" + Arrays.toString(joinPoint.getArgs()));
//            System.out.println("请求人 username："+name);
//            System.out.println("请求 ip："+ip);
//            System.out.println("请求时间 create_date："+df.format(new Date()));
            String ua = request.getHeader("User-Agent");
            //转成UserAgent对象
            UserAgent userAgent = UserAgent.parseUserAgentString(ua);
            //获取浏览器信息
            Browser browser = userAgent.getBrowser();
            String browserName = browser.getName();
            System.out.println("浏览器 browser："+browserName);

            //*========数据库日志=========*//
            SysLog sysLog=new SysLog();
            sysLog.setUri(request.getRequestURI().toString());//请求接口
            sysLog.setMethod(request.getMethod());//请求方式
            sysLog.setMethodDescribe(getControllerMethodDescription(joinPoint));//描述
            sysLog.setParams(Arrays.toString(joinPoint.getArgs()));//参数信息
            sysLog.setUsername(name);//请求人
            sysLog.setIp(ip);//ip
            sysLog.setCreateDate(df.format(new Date()));//请求时间
            sysLog.setBrowser(browserName);//浏览器类型
            sysLogService.InsertSysLog(sysLog);

        }catch (Exception e){
            //记录本地异常日志
            logger.error("==前置通知异常==");
            logger.error("异常信息：{}",e.getMessage());
        }
    }


    @AfterReturning(returning = "ret", pointcut = "controllerAspect()")
    public void doAfterReturning(Object ret) throws Throwable {
        // 处理完请求，返回内容
        logger.info("RESPONSE : " + ret);
    }



    /**
     * @Description  异常通知 用于拦截service层记录异常日志
     * @date 2018年9月3日 下午5:43
     */
    @AfterThrowing(pointcut = "serviceAspect()",throwing = "e")
    public void doAfterThrowing(JoinPoint joinPoint,Throwable e){
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        HttpSession session = request.getSession();
        //读取session中的用户
        SysUser user = (SysUser) session.getAttribute("user");
        //获取请求ip
        String ip = IpUtiles.getRealIp(request);
        //获取用户请求方法的参数并序列化为JSON格式字符串
        String params = "";
        if (joinPoint.getArgs()!=null&&joinPoint.getArgs().length>0){
            for (int i = 0; i < joinPoint.getArgs().length; i++) {
                params+= JsonUtiles.objectToJson(joinPoint.getArgs()[i])+";";
            }
        }
        try{
            /*========控制台输出=========*/
//            System.out.println("=====异常通知开始=====");
//            System.out.println("异常代码:" + e.getClass().getName());
//            System.out.println("异常信息:" + e.getMessage());
//            System.out.println("异常方法:" + (joinPoint.getTarget().getClass().getName() + "." + joinPoint.getSignature().getName() + "()"));
//            System.out.println("方法描述:" + getServiceMethodDescription(joinPoint));
//            System.out.println("请求人:" + user.getName());
//            System.out.println("请求IP:" + ip);
//            System.out.println("请求参数:" + params);
            /*==========数据库日志=========*/

        }catch (Exception ex){
            //记录本地异常日志
            logger.error("==异常通知异常==");
            logger.error("异常信息:{}", ex.getMessage());
        }
    }


    /**
     * @Description  获取注解中对方法的描述信息 用于service层注解
     * @date 2018年9月3日 下午5:05
     */
    public static String getServiceMethodDescription(JoinPoint joinPoint)throws Exception{
        String targetName = joinPoint.getTarget().getClass().getName();
        String methodName = joinPoint.getSignature().getName();
        Object[] arguments = joinPoint.getArgs();
        Class targetClass = Class.forName(targetName);
        Method[] methods = targetClass.getMethods();
        String description = "";
        for (Method method:methods) {
            if (method.getName().equals(methodName)){
                Class[] clazzs = method.getParameterTypes();
                if (clazzs.length==arguments.length){
                    description = method.getAnnotation(SystemLogService.class).description();
                    break;
                }
            }
        }
        return description;
    }



    /**
     * @Description  获取注解中对方法的描述信息 用于Controller层注解
     * @date 2018年9月3日 上午12:01
     */
    public static String getControllerMethodDescription(JoinPoint joinPoint) throws Exception {
        String targetName = joinPoint.getTarget().getClass().getName();
        String methodName = joinPoint.getSignature().getName();//目标方法名
        Object[] arguments = joinPoint.getArgs();
        Class targetClass = Class.forName(targetName);
        Method[] methods = targetClass.getMethods();
        String description = "";
        for (Method method:methods) {
            if (method.getName().equals(methodName)){
                Class[] clazzs = method.getParameterTypes();
                if (clazzs.length==arguments.length){
                    description = method.getAnnotation(SystemLogController.class).description();
                    break;
                }
            }
        }
        return description;
    }
}
