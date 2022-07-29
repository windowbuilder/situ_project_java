package controller;


import model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import service.IManService;
import service.IOrdService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.*;

/**
 * @author feiyang
 * @create 2022-07-21 17:29
 * @Description:
 * @FileName: ManController
 * @History:
 */
@Controller
@RequestMapping("/man")
public class ManController {
    @Autowired
    private IManService manService;



    @RequestMapping("/loginPage")
    public String loginPage(){
        return "/manager/login";
    }

    //退出登录并清空缓存
    @RequestMapping("/quitPage")
    public String quitPage(HttpServletRequest req){
        Enumeration em = req.getSession().getAttributeNames();  //得到session中所有的属性名
        while (em.hasMoreElements()) {
            req.getSession().removeAttribute(em.nextElement().toString()); //遍历删除session中的值
        }
        return "/manager/login";
    }

    //登录验证
    @RequestMapping("/login")
    @ResponseBody
    public String login(Manager manager, HttpServletRequest req){
        Manager m = manService.querylogin(manager);
        if (m == null) {
            return "0";
        }
        //创建session
        HttpSession session = req.getSession();
        session.setAttribute(m.getId().toString(), m);
        session.setMaxInactiveInterval(900);//设置有效期15分钟
        return m.getId().toString();
    }

    //初始化并进入主页
    @RequestMapping("/ind")
    @ResponseBody
    public ModelAndView index(String id, HttpServletRequest req){
        //创建session
        HttpSession session = req.getSession();
        //根据id从session中获取管理员对象
        Manager m = (Manager) session.getAttribute(id);
        ModelAndView mv = new ModelAndView();
        mv.addObject("mName", m.getmName());
        mv.addObject("mLevel", m.getmLevel());
        mv.setViewName("/manager/index");
        return mv;
    }

    //加载管理员界面
    @RequestMapping("/que")
    @ResponseBody
    public ModelAndView query(Manager manager,Integer pageNo){
        ModelAndView mv = new ModelAndView();
        Map<String,Object> map = manService.query(manager,pageNo);
        mv.addObject("map",map);
        mv.setViewName("/manager/admin");
        return mv;
    }

    @RequestMapping("/adPage")
    public String adPage(){
        return "/manager/add_admin";
    }

    //管理员添加处理
    @RequestMapping("/add")
    @ResponseBody
    public String add(Manager manager){
        int add;
        try {
            add = manService.add(manager);
        }catch (Exception e){
            add = 0;
        }
        return String.valueOf(add);
    }

    //启用、禁用功能
    @RequestMapping("/update")
    @ResponseBody
    public String update(String id,String mStatus){
        System.out.println("id="+id+" ,mStatus="+mStatus);
        Manager manager = new Manager();
        manager.setId(Integer.valueOf(id));
        if (mStatus.equals("1")){
            mStatus = "0";
        }else {
            mStatus = "1";
        }
        manager.setMStatus(Integer.valueOf(mStatus));
        int update = manService.update(manager);
        return String.valueOf(update);
    }

}
