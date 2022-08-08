package controller;

import model.Detail;
import model.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import service.IOrdService;

import java.util.List;
import java.util.Map;

/**
 * @author feiyang
 * @create 2022-07-29 13:52
 * @Description:
 * @FileName: OrdController
 * @History:
 */
@Controller
@RequestMapping("/ord")
public class OrdController {
    @Autowired
    private IOrdService ordService;

    //前台加载订单界面
    @RequestMapping("/geo")
    @ResponseBody
    public ModelAndView getOrd(String orderNo){
        ModelAndView mv = new ModelAndView();
        mv.addObject("orderNo",orderNo);
        mv.setViewName("/reception/get_ord");
        return mv;
    }

    //前台加载订单界面
    @RequestMapping("/qio")
    @ResponseBody
    public ModelAndView queryIO(Integer uId){
        ModelAndView mv = new ModelAndView();
        List<Order> list = ordService.queryIO(uId);
        List<Detail> list1 = ordService.queryACD(new Detail());
        mv.addObject("list",list);
        mv.addObject("list1",list1);
        mv.setViewName("/reception/ordering");
        return mv;
    }

    //加载订单列表界面
    @RequestMapping("/quo")
    @ResponseBody
    public ModelAndView queryGO(Order order, Integer pageNo){
        ModelAndView mv = new ModelAndView();
        Map<String,Object> map = ordService.queryO(order,pageNo);
        mv.addObject("map",map);
        mv.addObject("orderNo",order.getOrderNo());
        mv.addObject("orderStatus",order.getOrderStatus());
        mv.setViewName("/manager/order");
        return mv;
    }

    //发货功能
    @RequestMapping("/set")
    @ResponseBody
    public String set(String[] arr){
        Order order = new Order();
        order.setOrderStatus(3);
        int set = ordService.updateO(arr,order);
        return String.valueOf(set);
    }

    //加载订单详情界面
    @RequestMapping("/look")
    @ResponseBody
    public ModelAndView look(String orderNo, Integer pageNo){
        ModelAndView mv = new ModelAndView();
        Detail detail = new Detail();
        Order order = new Order();
        detail.setOrderNo(orderNo);
        order.setOrderNo(orderNo);
        List<Detail> list = ordService.queryCD(detail);
        Map<String,Object> map = ordService.queryO(order,pageNo);
        mv.addObject("list1",map.get("list"));
        mv.addObject("map",map);
        mv.addObject("list",list);
        mv.setViewName("/manager/detail");
        return mv;
    }

    //加载退款界面
    @RequestMapping("/ref")
    @ResponseBody
    public ModelAndView ref(Integer pageNo){
        ModelAndView mv = new ModelAndView();
        Order order = new Order();
        order.setOrderStatus(5);
        Map<String,Object> map = ordService.queryO(order,pageNo);
        mv.addObject("map",map);
        mv.setViewName("/manager/refund");
        return mv;
    }

    //退款功能
    @RequestMapping("/refu")
    @ResponseBody
    public String refu(String[] arr){
        Order order = new Order();
        order.setOrderStatus(6);
        int set = ordService.updateO(arr,order);
        return String.valueOf(set);
    }

    //退款功能
    @RequestMapping("/up")
    @ResponseBody
    public String update(String orderNo,Integer orderStatus){
        Order order = new Order();
        order.setOrderStatus(orderStatus);
        order.setOrderNo(orderNo);
        int set = ordService.updateAO(order);
        return String.valueOf(set);
    }
}
