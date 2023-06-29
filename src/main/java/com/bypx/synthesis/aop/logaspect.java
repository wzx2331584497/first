package com.bypx.synthesis.aop;
        /*创建切面  创建切点于功能产生关联 创建日志功能*/


import com.bypx.synthesis.bean.Log;
import com.bypx.synthesis.service.LogdaoService;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.Date;


@Component
@Aspect//定义文件是切面
public class logaspect {
    @Resource
    private LogdaoService logService;//定义一个LogService属性 然后用    @Resource转换围殴对象用于传递日志数据到数据库
    //创建切点让已有的功能于切面里面的功能产生关联
    @Pointcut("execution(* com.bypx.synthesis.controller..*.*(..))")
    public void LogPoint() {
    }  //第一个*表示返回的任意类型
//com.example.aaa.controller文件所在的包名sss
    //第二个*表示包下面的任意文件
    //第三个表示文件内的任意方法名
    //(..)表示输入任意参数

    //日志功能--切面里面的功能
    @After("LogPoint()")//目标功能实现后再执行日志功能  后置通知 就是增强内容
    //功能
    public void dolog(JoinPoint jp) {  /*
            JointPoint类是AOP编程中用来描述切入点的类
    一般用法如下，通过JoinPoint可以获取被代理方法的各种信息，如方法参数，
    方法所在类的class对象，然后执行反射操作
    Object getThis()	获取代理类对象
Object getTarget()	获取被代理类对象
Object[] getArgs()	获取目标方法的参数数组
Signature getSignature()	获取被代理类的签名
    */
        System.out.println("日志成功执行1");
        String name = jp.getSignature().getName();
        Object[] args = jp.getArgs();//获取目标方法的参数数组
        //springmvc 底层 httpservlet-httpserveletreques
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
        HttpServletRequest request = attributes.getRequest();
        System.out.println(request.getParameter("account"));
        Log log = new Log(name, new Date());//传入方法名字和时间对象
        System.out.println(log.toString());
        logService.add(log);
    }

}

