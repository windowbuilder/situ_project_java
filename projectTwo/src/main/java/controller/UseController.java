package controller;

import dao.impl.UseDao;
import model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import service.IUseService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.math.BigDecimal;
import java.util.Enumeration;
import java.util.Map;

/**
 * @author feiyang
 * @create 2022-08-02 19:50
 * @Description:
 * @FileName: UseController
 * @History:
 */
@Controller
@RequestMapping("/use")
public class UseController {
    @Autowired
    private IUseService useService;

    @RequestMapping("/loginPage")
    public String loginPage(){
        return "/reception/login";
    }

    //退出登录并清空缓存
    @RequestMapping("/quitPage")
    public String quitPage(HttpServletRequest req){
        Enumeration em = req.getSession().getAttributeNames();  //得到session中所有的属性名
        while (em.hasMoreElements()) {
            req.getSession().removeAttribute(em.nextElement().toString()); //遍历删除session中的值
        }
        return "/reception/login";
    }

    //登录验证
    @RequestMapping("/login")
    @ResponseBody
    public String login(User user, HttpServletRequest req){
        User u = useService.queryI(user);
        if (u == null) {
            return "0";
        }
        //创建session
        HttpSession session = req.getSession();
        session.setAttribute("u", u);
        session.setMaxInactiveInterval(900);//设置有效期15分钟
        return u.getId().toString();
    }

    //初始化并进入主页
    @RequestMapping("/ind")
    @ResponseBody
    public ModelAndView index(HttpServletRequest req){
        //创建session
        HttpSession session = req.getSession();
        //根据id从session中获取用户对象
        User m = (User) session.getAttribute("u");
        ModelAndView mv = new ModelAndView();
        mv.addObject("uName", m.getUName());
        mv.addObject("uid", m.getId());
        mv.setViewName("/reception/rec_index");
        return mv;
    }

    //进入个人界面
    @RequestMapping("/pri")
    public String privateInfo(){
        return "/reception/private";
    }

    //进入个人信息界面
    @RequestMapping("/per")
    @ResponseBody
    public ModelAndView personInfo(HttpServletRequest req){
        //创建session
        HttpSession session = req.getSession();
        //根据id从session中获取用户对象
        User m = (User) session.getAttribute("u");
        ModelAndView mv = new ModelAndView();
        mv.addObject("uId", m.getId());
        mv.setViewName("/reception/person");
        return mv;
    }

    //个人信息更改
    @RequestMapping("/upt")
    @ResponseBody
    public String update(User user,HttpServletRequest req){
        int i = useService.updateI(user);
        HttpSession session = req.getSession();
        User u = useService.queryI(new User(user.getId()));
        session.setAttribute("u", u);
        return String.valueOf(i);
    }

    //初始化并进入个人余额界面
    @RequestMapping("/bal")
    @ResponseBody
    public ModelAndView balance(HttpServletRequest req){
        //创建session
        HttpSession session = req.getSession();
        //根据id从session中获取用户对象
        User m = (User) session.getAttribute("u");
        ModelAndView mv = new ModelAndView();
        mv.addObject("uMoney", m.getUMoney());
        mv.setViewName("/reception/balance");
        return mv;
    }

    //进入充值界面
    @RequestMapping("/rea")
    public String reach(){
        return "/reception/reach";
    }

    //充值
    @RequestMapping("/adm")
    @ResponseBody
    public String addMoney(Integer uMoney,HttpServletRequest req){
        HttpSession session = req.getSession();
        User m = (User) session.getAttribute("u");
        Map<String, Object> map = useService.updateM(uMoney,m);
        User u = useService.queryI(new User(m.getId()));
        session.setAttribute("u", u);
        String code = String.valueOf((Integer)map.get("code"));
        return code;
    }
}
