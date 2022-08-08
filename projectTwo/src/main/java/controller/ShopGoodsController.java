package controller;

import model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import service.IShoGooService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Map;

/**
 * @author feiyang
 * @create 2022-08-05 13:56
 * @Description:
 * @FileName: ShopGoodsController
 * @History:
 */
@Controller
@RequestMapping("/shop/order")
public class ShopGoodsController {
    @Autowired
    private IShoGooService shoGooService;

    //进入支付界面
    @RequestMapping("/rdi")
    @ResponseBody
    public ModelAndView getOrding(Integer id, Integer buyCount, HttpServletRequest req){
        ModelAndView mv = new ModelAndView();
        HttpSession session = req.getSession();
        User m = (User) session.getAttribute("u");
        Map<String, Object> map = shoGooService.createOrd(m.getId(), id, buyCount);
        Integer code = (Integer)map.get("code");
        if (code == 1){
            mv.addObject("goods",map.get("goods"));
            mv.addObject("buyCount",buyCount);
            mv.addObject("uid",m.getId());
            mv.addObject("orderNo",map.get("orderNo"));
            mv.addObject("orderMoney",map.get("orderMoney"));
            mv.setViewName("/reception/buy");
        }else {
            mv.addObject("msg",map.get("msg"));
            mv.setViewName("/reception/buy");
        }
        return mv;
    }

    //点击支付 1 验证支付密码 2 验证账户余额是否充足 3 扣减账户余额 4 生成支付流水
    @RequestMapping("/odin")
    @ResponseBody
    public String ording(String orderNo,String uPass){
        Map<String, Object> map = shoGooService.ordIng(orderNo,uPass);
        String code = String.valueOf((Integer)map.get("code"));
        return code;
    }

    //加入购物车
    @RequestMapping("/aca")
    @ResponseBody
    public String addCar(Integer id,Integer buyCount, HttpServletRequest req){
        HttpSession session = req.getSession();
        User m = (User) session.getAttribute("u");
        Map<String, Object> map = shoGooService.addCar(m.getId(), id, buyCount);
        String code = String.valueOf((Integer)map.get("code"));
        return code;
    }

    //购买
    @RequestMapping("/gbu")
    @ResponseBody
    public String getbuy(Integer id, Integer buyCount, HttpServletRequest req){
        HttpSession session = req.getSession();
        User m = (User) session.getAttribute("u");
        Map<String, Object> map = shoGooService.buyDeleteCar(m.getId(), id, buyCount);
        String orderNo = "0";
        String on = (String)map.get("orderNo");
        if(on != null && on != ""){
            orderNo = on;
        }
        return orderNo;
    }

    //删除
    @RequestMapping("/dec")
    @ResponseBody
    public String deleteCar(Integer id){
        int i = shoGooService.deleteCar(id);
        return String.valueOf(i);
    }
}
