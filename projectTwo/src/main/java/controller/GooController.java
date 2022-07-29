package controller;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import model.Goods;
import model.GoodsSort;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import service.IGooService;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author feiyang
 * @create 2022-07-29 8:39
 * @Description:
 * @FileName: GooController
 * @History:
 */
@Controller
@RequestMapping("/goo")
public class GooController {
    @Autowired
    private IGooService gooService;
    private final String PICTURE = "http://127.0.0.1:8887/";

    //加载分类列表界面
    @RequestMapping("/num")
    @ResponseBody
    public ModelAndView num(Integer pageNo){
        ModelAndView mv = new ModelAndView();
        GoodsSort goodsSort = new GoodsSort();
        goodsSort.setParentId(-1);
        List<GoodsSort> list = gooService.queryClassG(goodsSort);
        mv.addObject("list",list);
        mv.setViewName("/manager/shop_num");
        return mv;
    }

    //加载添加类别界面
    @RequestMapping("/addPage")
    @ResponseBody
    public ModelAndView addPage(String pId,String pName){
        ModelAndView mv = new ModelAndView();
        mv.addObject("pId",pId);
        mv.addObject("pName",pName);
        mv.setViewName("/manager/sort_add");
        return mv;
    }

    //类别添加
    @RequestMapping("/addP")
    @ResponseBody
    public String addP(String parentId,String sName){
        GoodsSort goodsSort = new GoodsSort();
        goodsSort.setParentId(Integer.valueOf(parentId));
        goodsSort.setSName(sName);
        int addp = gooService.addp(goodsSort);
        return String.valueOf(addp);
    }

    //类别删除
    @RequestMapping("/dels")
    @ResponseBody
    public String dels(String id){
        int addp = gooService.delg(Integer.valueOf(id));
        return String.valueOf(addp);
    }

    //类别更新
    @RequestMapping("/upsn")
    @ResponseBody
    public String upsn(String id,String sName){
        GoodsSort goodsSort = new GoodsSort();
        goodsSort.setId(Integer.valueOf(id));
        goodsSort.setSName(sName);
        int upsn = gooService.upsn(goodsSort);
        return String.valueOf(upsn);
    }

    //加载商品列表界面
    @RequestMapping("/queg")
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
        mv.setViewName("/manager/shop");
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
        Map<String, Object> map = new HashMap<>();
        map.put("id",Integer.valueOf(id));
        Goods goods = new Goods();
        map.put("goods",goods);
        Goods goods1 = gooService.queryGo(map);
        mv.addObject("goods",goods1);
        mv.setViewName("/manager/update_shop");
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
    @RequestMapping(value = "/two",produces = "text/html;charset=utf-8")
    @ResponseBody
    public String two(String id,String soName){
        JSONArray arr = new JSONArray();
        if (id == null){
            return arr.toString();
        }
        GoodsSort gs = new GoodsSort();
        gs.setParentId(Integer.valueOf(id));
        List<GoodsSort> list = gooService.queryClassG(gs);
        if (list == null){
            return arr.toString();
        }
        for (GoodsSort goodsSort : list) {
            JSONObject obj = new JSONObject();
            obj.put("id",goodsSort.getId());
            obj.put("sName",goodsSort.getsName());
            System.out.println(soName);
            obj.put("soName",soName);
            arr.add(obj);
        }
        return arr.toString();
    }

    //加载添加商品界面
    @RequestMapping("/addsh")
    @ResponseBody
    public ModelAndView addsh(){
        ModelAndView mv = new ModelAndView();
        GoodsSort goodsSort = new GoodsSort();
        goodsSort.setParentId(-1);
        List<GoodsSort> list = gooService.queryClassG(goodsSort);
        mv.addObject("list",list);
        mv.setViewName("/manager/add_shop");
        return mv;
    }

    //图片上传测试
    @RequestMapping("/upload")
    @ResponseBody
    public String uploadFile(@RequestParam("file") MultipartFile imgFile) throws IOException {
        //@RequestParam用于将指定名称的请求参数赋值给方法中的形参
        String contentType = imgFile.getContentType();//IMAGE/PNG，文件类型
        System.out.println(contentType);
        String name = imgFile.getName();//请求的参数的名字
        System.out.println(name);
        String originalName = imgFile.getOriginalFilename();//原文件名
        System.out.println(originalName);
        long size = imgFile.getSize();//文件大小,字节
        System.out.println(size);
        InputStream in = null;
        OutputStream out = null;
        //文件目的地址
        String filePath = "E:/img/";
        String Path = "";
        File file = new File(filePath);
        if(!file.exists()){
            file.mkdir();
        }
        try{
            //根据源文件获取输入流
            in = imgFile.getInputStream();
            SimpleDateFormat sf = new SimpleDateFormat("yyyyMMddHHmmss");
            //生成目标文件名
            Path = sf.format(new Date())+".png";
            filePath += Path;
            //创建文件输出流
            out = new FileOutputStream(new File(filePath));
            //读取输入流，将读取的字节写入到数据流并刷新输出流
            IOUtils.copy(in, out);
        } catch (IOException e){
            e.printStackTrace();
        } finally {
            if (in != null){
                in.close();
            }
            if (out != null){
                out.close();
            }
        }
        JSONObject json = new JSONObject();
        json.put("code", 1);
        json.put("imgUrl", PICTURE + Path);
        return json.toJSONString();
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

    //加载下架商品列表界面
    @RequestMapping("/qud")
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
        mv.setViewName("/manager/down_shop");
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

}
