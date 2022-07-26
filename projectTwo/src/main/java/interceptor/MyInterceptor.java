package interceptor;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author feiyang
 * @create 2022-07-21 9:18
 * @Description:
 * @FileName: MyInterceptor
 * @History:
 */
public class MyInterceptor implements HandlerInterceptor {
    /*
     * 在控制器controller处理靖求之前被调用
     * 1 [拦截]该方法的返回值是布尔值Boolean类型的，当它返回为fllse 时，表示请求结束，后续的Interceptor和Controller都不会再执行;
     * 2 [放行]当返回值为true时就会继续调用下一个Interceptor的preHandle 方法，如果已经是最后-一个Interceptor的时候就会是调用当前请求的Controller方法
     * */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        System.out.println("interceptor---------preHandle");
        return true;
    }
    /*
     * 在controller处理请求结束之后，在dispatcherServlet进行视图数据渲染之前，
     * 调用该方法所以我们可以在这个方法中对Controller处理之后的NodelAndView对象进行操作
     * */
    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        System.out.println("interceptor---------postHandle");
    }
    /*
     * 在DispatcherServlet渲染了对应的视图
     * 之后执行这个方法的主要作用是用于进行资源清理工作
     * */
    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        System.out.println("interceptor---------afterCompletion");
    }
}
