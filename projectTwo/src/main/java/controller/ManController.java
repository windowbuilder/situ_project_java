package controller;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import service.IGooService;
import service.IManService;
import service.IOrdService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
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
    @Autowired
    private IGooService gooService;
    @Autowired
    private IOrdService ordService;

    @RequestMapping("/loginPage")
    public String loginPage(){
        return "manager/login";
    }

    //退出登录并清空缓存
    @RequestMapping("/quitPage")
    public String quitPage(HttpServletRequest req){
        Enumeration em = req.getSession().getAttributeNames();  //得到session中所有的属性名
        while (em.hasMoreElements()) {
            req.getSession().removeAttribute(em.nextElement().toString()); //遍历删除session中的值
        }
        return "manager/login";
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
    @RequestMapping("ind")
    @ResponseBody
    public ModelAndView index(String id, HttpServletRequest req){
        //创建session
        HttpSession session = req.getSession();
        //根据id从session中获取管理员对象
        Manager m = (Manager) session.getAttribute(id);

        ModelAndView mv = new ModelAndView();
        mv.addObject("mName", m.getmName());
        mv.addObject("mLevel", m.getmLevel());
        mv.setViewName("manager/index");
        return mv;
    }

    //加载管理员界面
    @RequestMapping("que")
    @ResponseBody
    public ModelAndView query(Manager manager,Integer pageNo){
        ModelAndView mv = new ModelAndView();
        Map<String,Object> map = manService.query(manager,pageNo);
        mv.addObject("map",map);
        mv.setViewName("manager/admin");
        return mv;
    }

    @RequestMapping("/adPage")
    public String adPage(){
        return "manager/add_admin";
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

    //加载商品列表界面
    @RequestMapping("queg")
    @ResponseBody
    public ModelAndView queryG(Goods goods, Integer pageNo){
        ModelAndView mv = new ModelAndView();
        GoodsSort goodsSort = new GoodsSort();
        goodsSort.setParentId(-1);
        goods.setGStatus(1);
        Map<String,Object> map = gooService.queryG(goods,pageNo);
        List<GoodsSort> list = gooService.queryClassG(goodsSort);
        mv.addObject("map",map);
        mv.addObject("list",list);
        mv.addObject("gName",goods.getgName());
        mv.addObject("soName",goods.getSoName());
        mv.addObject("sName",goods.getsName());
        mv.setViewName("manager/shop");
        return mv;
    }

    //下架功能
    @RequestMapping("/downg")
    @ResponseBody
    public String downG(String[] arr){
        Goods goods = new Goods();
        goods.setGStatus(0);
        int down = gooService.updown(arr,goods);
        return String.valueOf(down);
    }

    //更新界面初始化
    @RequestMapping("/updPage")
    @ResponseBody
    public ModelAndView updPage(String id){
        ModelAndView mv = new ModelAndView();
        HashMap<String, Object> map = new HashMap<>();
        map.put("id",Integer.valueOf(id));
        Goods goods = new Goods();
        map.put("goods",goods);
        Goods goods1 = gooService.queryGo(map);
        mv.addObject("goods",goods1);
        mv.setViewName("manager/update_shop");
        return mv;
    }

    //更新
    @RequestMapping("/upda")
    @ResponseBody
    public String upda(Goods goods){
        int updateG = gooService.updateG(goods);
        return String.valueOf(updateG);
    }

    //二级联动
    @RequestMapping(value = "/two")
    @ResponseBody
    public String two(String sName){
        GoodsSort gs = new GoodsSort();
        gs.setSName(sName);
        List<GoodsSort> list = gooService.queryClassG(gs);
        gs.setParentId(list.get(0).getId());
        gs.setSName(null);
        list = gooService.queryClassG(gs);
        JSONArray arr = new JSONArray();
        for (GoodsSort goodsSort : list) {
            JSONObject obj = new JSONObject();
            obj.put("id",goodsSort.getId());
            obj.put("sName",goodsSort.getsName());
            arr.add(obj);
        }
        return arr.toString();
    }

    //加载下架商品列表界面
    @RequestMapping("qud")
    @ResponseBody
    public ModelAndView queryGD(Goods goods, Integer pageNo){
        ModelAndView mv = new ModelAndView();
        GoodsSort goodsSort = new GoodsSort();
        goodsSort.setParentId(-1);
        goods.setGStatus(0);
        Map<String,Object> map = gooService.queryG(goods,pageNo);
        List<GoodsSort> list = gooService.queryClassG(goodsSort);
        mv.addObject("map",map);
        mv.addObject("list",list);
        mv.addObject("gName",goods.getgName());
        mv.addObject("soName",goods.getSoName());
        mv.addObject("sName",goods.getsName());
        mv.setViewName("manager/down_shop");
        return mv;
    }

    //上架功能
    @RequestMapping("/dou")
    @ResponseBody
    public String downGU(String[] arr){
        Goods goods = new Goods();
        goods.setGStatus(1);
        int down = gooService.updown(arr,goods);
        return String.valueOf(down);
    }

    //加载添加商品界面
    @RequestMapping("addsh")
    @ResponseBody
    public ModelAndView addsh(){
        ModelAndView mv = new ModelAndView();
        GoodsSort goodsSort = new GoodsSort();
        goodsSort.setParentId(-1);
        List<GoodsSort> list = gooService.queryClassG(goodsSort);
        mv.addObject("list",list);
        mv.setViewName("manager/add_shop");
        return mv;
    }

    //图片上传测试
    @RequestMapping("upload")
    @ResponseBody
    public Map<String,Object> upload(MultipartFile file, HttpServletRequest request){
        System.out.println("dasdasdasdaswdsdasdasd----------------------------------");
        String prefix="";
        String dateStr="";
        //保存上传
        OutputStream out = null;
        InputStream fileInput=null;
        try{
            if(file!=null){
                String originalName = file.getOriginalFilename();
                prefix=originalName.substring(originalName.lastIndexOf(".")+1);
                Date date = new Date();
                String uuid = UUID.randomUUID()+"";
                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
                dateStr = simpleDateFormat.format(date);
                String filepath = "D:\\idea\\workSpace\\projectTwo\\src\\main\\webapp\\img\\" + dateStr+"\\"+uuid+"." + prefix;
                File files=new File(filepath);
                //打印查看上传路径
                System.out.println(filepath);
                if(!files.getParentFile().exists()){
                    files.getParentFile().mkdirs();
                }
                file.transferTo(files);
                Map<String,Object> map2=new HashMap<>();
                Map<String,Object> map=new HashMap<>();
                map.put("code",0);
                map.put("msg","");
                map.put("data",map2);
                map2.put("src","/images/"+ dateStr+"/"+uuid+"." + prefix);
                return map;
            }
        }catch (Exception e){
        }finally{
            try {
                if(out!=null){
                    out.close();
                }
                if(fileInput!=null){
                    fileInput.close();
                }
            } catch (IOException e) {
            }
        }
        Map<String,Object> map=new HashMap<>();
        map.put("code",1);
        map.put("msg","");
        return map;
    }

    //商品添加处理
    @RequestMapping("/adds")
    @ResponseBody
    public String adds(Goods goods){
        int add;
        try {
            add = gooService.addG(goods);
        }catch (Exception e){
            add = 0;
        }
        return String.valueOf(add);
    }

    //加载订单列表界面
    @RequestMapping("quo")
    @ResponseBody
    public ModelAndView queryGO(Order order, Integer pageNo){
        ModelAndView mv = new ModelAndView();
        Map<String,Object> map = ordService.queryO(order,pageNo);
        mv.addObject("map",map);
        mv.addObject("orderNo",order.getOrderNo());
        mv.addObject("orderStatus",order.getOrderStatus());
        mv.setViewName("manager/order");
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
    @RequestMapping("look")
    @ResponseBody
    public ModelAndView look(String orderNo, Integer pageNo){
        System.out.println(orderNo);
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
        mv.setViewName("manager/detail");
        return mv;
    }

    //加载退款界面
    @RequestMapping("ref")
    @ResponseBody
    public ModelAndView ref(String orderNo, Integer pageNo){
        System.out.println(orderNo);
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
        mv.setViewName("manager/detail");
        return mv;
    }
}
