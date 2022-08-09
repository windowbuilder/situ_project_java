package controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import service.ICarService;
import service.IGooService;

import java.util.Map;

/**
 * @author feiyang
 * @create 2022-08-04 15:25
 * @Description:
 * @FileName: CarController
 * @History:
 */
@Controller
@RequestMapping("/car")
public class CarController {
    @Autowired
    private ICarService carService;

    //初始化，查询前台商品列表界面
    @RequestMapping("/quc")
    @ResponseBody
    public ModelAndView queryIC(Integer uid, Integer pageNo){
        ModelAndView mv = new ModelAndView();
        Map<String, Object> map = carService.queryIC(uid, pageNo);
        mv.addObject("map",map);
        mv.setViewName("/reception/buy_car");
        return mv;
    }
}
