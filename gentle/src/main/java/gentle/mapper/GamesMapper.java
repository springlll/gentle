package gentle.mapper;

import gentle.entity.Games;
import gentle.entity.User;
import gentle.entity.UserExample;

import java.util.List;

import org.apache.ibatis.annotations.*;
import tk.mybatis.mapper.common.Mapper;

public interface GamesMapper extends Mapper<Games> {

    int countByExample(UserExample example);

    int deleteByExample(UserExample example);

    int deleteByPrimaryKey(Long id);

    @Select("select count(games_id) from gnt_games")
    int selectTotalNum();

    int insertSelective(User user);

    List<Games> selectByExample(UserExample example);

    Games selectByPrimaryKey(int games_id);

    int updateByExampleSelective(@Param("record") User record, @Param("example") UserExample example);

    int updateByExample(@Param("record") User record, @Param("example") UserExample example);

    int updateByPrimaryKeySelective(User record);
}