package com.originaldreams.usermanagercenter.mapper;

import com.originaldreams.common.mybatis.MyBaseMapper;
import com.originaldreams.usermanagercenter.entity.UserInfo;
import com.originaldreams.usermanagercenter.view.PageList;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import java.util.List;

@Mapper
public interface UserInfoMapper  extends MyBaseMapper {
    String tableName = "user_info";

     @Insert("INSERT INTO " + tableName + "(id, nickName, birthday, gender, address, signature, userPortrait, email, phone, createTime, mask) VALUES (#{id}, #{nickName}, #{birthday}, #{gender}, #{address}, #{signature}, #{userPortrait}, #{email}, #{phone}, #{createTime}, #{mask})")
     Integer insert(UserInfo userInfo);

     @Delete("DELETE FROM " + tableName + " WHERE id = #{id}")
     Integer deleteById(Integer id);

     @Update("UPDATE " + tableName + " SET nickName=#{nickName}, birthday=#{birthday}, gender=#{gender}, address=#{address}, signature=#{signature}, userPortrait=#{userPortrait}, email=#{email}, phone=#{phone}, createTime=#{createTime}, mask=#{mask} WHERE id = #{id}")
     Integer update(UserInfo userInfo);
}
