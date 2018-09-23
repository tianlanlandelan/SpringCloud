package com.originaldreams.logcenter.util;

import com.originaldreams.logcenter.service.EmailLogService;
import com.originaldreams.logcenter.service.LogonLogService;
import com.originaldreams.logcenter.service.TableMaintenanceService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;


/**
 * Created by william on 2018/7/28.
 */
@Component
@EnableScheduling
public class ScheduledTask {
    private Logger logger = LoggerFactory.getLogger(ScheduledTask.class);
    private SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");

    @Autowired
    TableMaintenanceService maintenanceService;
    @Autowired
    LogonLogService logonLogService;
    @Autowired
    EmailLogService emailLogService;


//    @Scheduled(cron = "0/30 * * * * ?")
//    public void createNewTable() throws ParseException {
//        //TODO   定时创建表，后天和大后天的表
//        updateTableDaily("sign");
//        updateTableDaily("email");
//    }


//    private void updateTableDaily(String type) throws ParseException {
//        Map<String,Object> params=new HashMap<String,Object>();
//        params.put("table_type",type);
//        TableMaintenance _main=maintenanceService.getLastestTable(params);
//        if(_main!=null){
//            Date latest_date=sdf.parse(_main.getTable_create_day());
//            Calendar latest = Calendar.getInstance();
//            latest.setTime(latest_date);
//            latest.set(Calendar.DATE, latest.get(Calendar.DATE) + 1);
//
//            Calendar after_tomorrow = Calendar.getInstance();
//            Date now_day=sdf.parse(sdf.format(new Date()));
//            after_tomorrow.setTime(now_day);
//            after_tomorrow.set(Calendar.DATE, after_tomorrow.get(Calendar.DATE) + 3);
//
//            logger.info("after_tomorrow"+sdf.format(after_tomorrow.getTime()));
//
//            while(latest.before(after_tomorrow)){
//                String days=sdf.format(latest.getTime());
//                logonLogService.createNewTableByDate(_main,days);
//                logger.info("创建表:"+days);
//
//                latest.set(Calendar.DATE, latest.get(Calendar.DATE) + 1);
//            }
//        }
//    }


}
