package gentle.service.impl;/**
 * @Description:
 * @Auther: silence
 * @Date: 2019/1/4 09:28
 */

import gentle.entity.Games;
import gentle.entity.User;
import gentle.mapper.GamesMapper;
import gentle.mapper.UserMapper;
import gentle.service.IGamesService;
import gentle.service.IUserService;
import org.apache.commons.lang3.StringUtils;
import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

import javax.sound.midi.Instrument;

/**
 * @author silence
 * @date 2019/1/4 9:28
 */
@Service
public class GamesServiceImpl implements IGamesService {

    @Autowired
    private GamesMapper gamesMapper;

    /**
     * 查所用户表总条数
     *
     * @return
     */
    @Override
    public int getTotalGamesNum(Games games_id) throws Exception {

        Example example = new Example(Games.class);
        if (User.isAllFieldNull(games_id)) {
            return gamesMapper.selectTotalNum();
        }
        Example.Criteria criteria = example.createCriteria(); // 条件查询
        if (StringUtils.isNotBlank(games_id.getNickname())) {
            criteria.andLike("nickname", "%" + games_id.getNickname() + "%");
        }
		/*
		 * if (StringUtils.isNotBlank(games.getAccount())) { criteria.andLike("account",
		 * "%" + games.getAccount() + "%"); } if
		 * (StringUtils.isNotBlank(user.getRoleName())) { criteria.andLike("roleName",
		 * "%" + user.getRoleName() + "%"); } if
		 * (StringUtils.isNotBlank(user.getGender())) { criteria.andEqualTo("gender",
		 * user.getGender().equals("女") ? "0" : "1   "); } if
		 * (StringUtils.isNotBlank(user.getRegion())) { criteria.andLike("region", "%" +
		 * user.getRegion() + "%"); }
		 */
        return gamesMapper.selectByExample(example).size();
    }




    /**
     * 查所有用户
     *
     * @return
     */
    @Override
    public List<Games> getGamesList(Games games, Integer currentPage) {

        int offset = null == currentPage ? 0 : (currentPage - 1) * 5; // 起始行数
        int limit = 5; // 每页条数

        RowBounds rowBounds = new RowBounds(offset, limit);
        Example example = new Example(User.class);
        if (null == games) {
            return gamesMapper.selectByExampleAndRowBounds(example, rowBounds);
        }
        System.out.println(gamesMapper.selectByExampleAndRowBounds(example, rowBounds));
		return gamesMapper.selectByExampleAndRowBounds(example, rowBounds);

    }




	@Override
	public void save(Instrument instrument) {
		// TODO Auto-generated method stub
		
	}

}
