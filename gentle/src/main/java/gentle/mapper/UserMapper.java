package gentle.mapper;

import gentle.entity.User;
import gentle.entity.UserExample;

import java.util.List;

import org.apache.ibatis.annotations.*;
import tk.mybatis.mapper.common.Mapper;

public interface UserMapper extends Mapper<User> {

    int countByExample(UserExample example);

    int deleteByExample(UserExample example);

    int deleteByPrimaryKey(Long id);

    @Insert(" insert into sys_user (account, salt, password, " +
            "      role_id, role_name, nickname, " +
            "      gender, birthday, region )" +
            "    values (#{account,jdbcType=VARCHAR}, #{salt,jdbcType=VARCHAR}, #{password,jdbcType=VARCHAR}, " +
            "      #{roleId,jdbcType=BIGINT}, #{roleName,jdbcType=VARCHAR}, #{nickname,jdbcType=VARCHAR}, " +
            "      #{gender,jdbcType=BIT}, #{birthday,jdbcType=TIMESTAMP}, #{region,jdbcType=VARCHAR} )")
    int insert(User user);

    @Select("select count(id) from sys_user")
    int selectTotalNum();

    int insertSelective(User user);

    List<User> selectByExample(UserExample example);

    User selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") User record, @Param("example") UserExample example);

    int updateByExample(@Param("record") User record, @Param("example") UserExample example);

    int updateByPrimaryKeySelective(User record);

    @Update("update sys_user set account=#{user.account,jdbcType=VARCHAR},gender =#{user.gender,jdbcType=VARCHAR}, nickname=#{user.nickname,jdbcType=VARCHAR}, region=#{user.region,jdbcType=VARCHAR} where id=#{user.id,jdbcType=VARCHAR}")
    int updateByPrimaryKey(@Param("user") User user);
}