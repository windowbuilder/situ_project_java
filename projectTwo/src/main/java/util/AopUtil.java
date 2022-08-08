package util;

import model.User;
import org.aspectj.lang.JoinPoint;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author feiyang
 * @create 2022-07-21 9:51
 * @Description:
 * @FileName: AopUtil
 * @History:
 */
public class AopUtil {
    /*
    * 异常通知 after-throwing
    1作用:在目标方法抛出异常时执行的通知
    * 2用法:
        可以配置传入JoinPoint获取目标对象和目标方法相关信息，但必须处在参数列表第一位
        还可以配置参数，让异常通知可以接收到目标方法抛出的异常对象。
    3应用场景:异常处理、控制事务
    */
    public void speakException(JoinPoint jp, Throwable e){
        Class clz = jp.getTarget().getClass();
        String name = jp.getSignature().getName();
        System.out.println("aop----after-throwing..["+clz+"]..["+name+"].."+e.getMessage());
    }

    //生成订单号
    public String getOrderNo(User m){
        String phone = m.getUPhone();
        String suffix = phone.substring(phone.length() - 4);
        SimpleDateFormat sf = new SimpleDateFormat("yyyyMMddHHmmss");
        String prefix = sf.format(new Date());
        String orderNo = prefix + suffix;
        return orderNo;
    }
}
