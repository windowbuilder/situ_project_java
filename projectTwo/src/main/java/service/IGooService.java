package service;/**
 * @author feiyang
 * @create 2022-07-22 20:57
 * @Description:
 * @FileName: IGooService
 * @History:
 */

import model.Goods;
import model.GoodsSort;

import java.util.List;
import java.util.Map;

/**
 * @author: feiyang
 * @create 2022-07-22 20:57
 * @Description:
 * @FileName: IGooService
 * @History:
 * @自定义内容：
 */
public interface IGooService {
    public Map<String,Object> queryRI(Integer sid, Integer pageNo,String gName);

    public Goods queryRO(Integer id);

    public Map<String,Object> queryG(Goods goods, Integer pageNo);


    public Goods queryGo(Map<String,Object> map);

    public List<GoodsSort> queryClassG(GoodsSort goodsSort);

    public int addG(Goods goods);

    public int addp(GoodsSort goodsSort);

    public int updateG(Goods goods);

    public int upsn(GoodsSort goodsSort);

    public int updown(String[] arr,Goods goods);

    public int delg(Integer id);
}
