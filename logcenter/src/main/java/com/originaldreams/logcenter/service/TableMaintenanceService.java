package com.originaldreams.logcenter.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.originaldreams.logcenter.entity.TableMaintenance;
import com.originaldreams.logcenter.mapper.TableMaintenanceMapper;
import java.util.List;
import java.util.Map;

@Service
public class TableMaintenanceService {
    @Autowired
    private TableMaintenanceMapper tableMaintenanceMapper;

    public TableMaintenance getById(Integer id){

        return tableMaintenanceMapper.getById(id);
    }

    public TableMaintenance getLastestTable(Map params){
        return tableMaintenanceMapper.getLastestTable(params);
    }


    public List<TableMaintenance> getAll(){
        return tableMaintenanceMapper.getAll();
    }

    public Integer insert(TableMaintenance tableMaintenance){
        return tableMaintenanceMapper.insert(tableMaintenance);
    }

    public Integer deleteById(Integer id){
        return tableMaintenanceMapper.deleteById(id);
    }

    public Integer update(TableMaintenance tableMaintenance){
        return tableMaintenanceMapper.update(tableMaintenance);
    }


}
