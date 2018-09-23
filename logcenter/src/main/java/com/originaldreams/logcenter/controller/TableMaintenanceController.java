package com.originaldreams.logcenter.controller;

import com.originaldreams.logcenter.entity.TableMaintenance;
import com.originaldreams.logcenter.service.TableMaintenanceService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("/tableMaintenance")
public class TableMaintenanceController {
    private Logger logger = LoggerFactory.getLogger(TableMaintenanceController.class);

    @Resource
    private TableMaintenanceService tableMaintenanceService;

    @RequestMapping(value = "/getById",method = RequestMethod.GET)
    ResponseEntity getById(Integer id){
        TableMaintenance result = tableMaintenanceService.getById(id);
        return ResponseEntity.ok().contentType(MediaType.APPLICATION_JSON).body(result);
    }



    @RequestMapping(value = "/getAll",method = RequestMethod.GET)
    ResponseEntity getAll(){
        List<TableMaintenance> result = tableMaintenanceService.getAll();
        return ResponseEntity.ok().contentType(MediaType.APPLICATION_JSON).body(result);
    }

    @RequestMapping(value = "/insert",method = RequestMethod.POST)
    ResponseEntity insert(TableMaintenance tableMaintenance){
        Integer result = tableMaintenanceService.insert(tableMaintenance);
        return ResponseEntity.ok().contentType(MediaType.APPLICATION_JSON).body(result);
    }

    @RequestMapping(value = "/deleteById",method = RequestMethod.DELETE)
    ResponseEntity deleteById(Integer id){
        Integer result = tableMaintenanceService.deleteById(id);
        return ResponseEntity.ok().contentType(MediaType.APPLICATION_JSON).body(result);
    }

    @RequestMapping(value = "/update",method = RequestMethod.PUT)
    ResponseEntity update(TableMaintenance tableMaintenance){
        Integer result = tableMaintenanceService.update(tableMaintenance);
        return ResponseEntity.ok().contentType(MediaType.APPLICATION_JSON).body(result);
    }

}
