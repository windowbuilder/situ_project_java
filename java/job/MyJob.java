package job;

import model.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import service.IOrdService;

/**
 * @author feiyang
 * @create 2022-08-08 11:32
 * @Description:
 * @FileName: MyJob
 * @History:
 */
@Component
public class MyJob {
    @Autowired
    private IOrdService ordService;

    //定时清理待付款的订单
    @Scheduled(cron = "0 */15 * * * ?")
    public void clearOrder(){
        ordService.updateAO(new Order());
    }
}
