package com.originaldreams.usermanagercenter.service;

import com.originaldreams.common.response.ResultData;
import com.originaldreams.common.util.ConfigUtils;
import com.originaldreams.usermanagercenter.view.PageList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.originaldreams.usermanagercenter.entity.UserInfo;
import com.originaldreams.usermanagercenter.mapper.UserInfoMapper;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author yangkaile
 * @date 2018-11-16 21:00:34
 */
@Service
public class UserInfoService {
    @Resource
    private UserInfoMapper userInfoMapper;

    public ResultData getById(Integer id){
        if(id == null){
            return ResultData.error("用户ID为空");
        }
        return ResultData.success(userInfoMapper.getById(id));
    }

    public ResultData getAll(){
        return ResultData.success(userInfoMapper.getAll());
    }

    public ResultData getPageList(int currentPage,int pageSize){
        if(currentPage < 1 || pageSize < 0 || pageSize > ConfigUtils.MAX_PAGE_SIZE){
            return ResultData.error("非法数据");
        }else {
            PageList pageList = new PageList();
            if(currentPage == 1){
                pageList.setTotal(userInfoMapper.getCount());
            }
            pageList.setCurrentPage(currentPage);
            pageList.setPageSize(pageSize);
            pageList.setData(userInfoMapper.getPageList(pageList));
            return ResultData.success(pageList);
        }
    }

    public Integer deleteById(Integer id){
        return userInfoMapper.deleteById(id);
    }

    public Integer update(UserInfo userInfo){
        return userInfoMapper.update(userInfo);
    }


}
