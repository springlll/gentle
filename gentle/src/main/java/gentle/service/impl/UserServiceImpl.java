package gentle.service.impl;/**
 * @Description:
 * @Auther: silence
 * @Date: 2019/1/4 09:28
 */

import gentle.entity.User;
import gentle.mapper.UserMapper;
import gentle.service.IUserService;
import org.apache.commons.lang3.StringUtils;
import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

/**
 * @author silence
 * @date 2019/1/4 9:28
 */
@Service
public class UserServiceImpl implements IUserService {

    @Autowired
    private UserMapper userMapper;

    /**
     * 查所用户表总条数
     *
     * @return
     */
    @Override
    public int getTotalUserNum(User user) throws Exception {

        Example example = new Example(User.class);
        if (User.isAllFieldNull(user)) {
            return userMapper.selectTotalNum();
        }
        Example.Criteria criteria = example.createCriteria(); // 条件查询
        if (StringUtils.isNotBlank(user.getNickname())) {
            criteria.andLike("nickname", "%" + user.getNickname() + "%");
        }
        if (StringUtils.isNotBlank(user.getAccount())) {
            criteria.andLike("account", "%" + user.getAccount() + "%");
        }
        if (StringUtils.isNotBlank(user.getRoleName())) {
            criteria.andLike("roleName", "%" + user.getRoleName() + "%");
        }
        if (StringUtils.isNotBlank(user.getGender())) {
            criteria.andEqualTo("gender", user.getGender().equals("女") ? "0" : "1   ");
        }
        if (StringUtils.isNotBlank(user.getRegion())) {
            criteria.andLike("region", "%" + user.getRegion() + "%");
        }
        return userMapper.selectByExample(example).size();
    }

    /**
     * 编辑用户
     *
     * @param user
     * @return
     */
    @Override
    public Integer updateUser(User user) {
        if (null == user || user.getId() == null) {
            return null;
        }
        return userMapper.updateByPrimaryKey(user);
    }

    /**
     * 删除用户
     *
     * @param userIds
     * @return
     */
    @Override
    public Integer delUser(String userIds) {
        if (StringUtils.isEmpty(userIds)) {
            return null;
        }

        Example example = new Example(User.class);
        // andIn方法参数为：User类属性，实参（List类型）
        example.createCriteria().andIn("id", Arrays.asList(userIds.split(",")));
        return userMapper.deleteByExample(example);
    }

    /**
     * 新增用户
     *
     * @param user
     * @return
     */
    @Override
    public Integer addUser(User user) {
        if (null == user || StringUtils.isEmpty(user.getAccount())) {
            return null;
        }
        user.setRoleName("普通用户");
        user.setRoleId(8L);
        user.setBirthday(new Date());
        return userMapper.insert(user);
    }

    /**
     * 查所有用户
     *
     * @return
     */
    @Override
    public List<User> getUserList(User user, Integer currentPage) {

        int offset = null == currentPage ? 0 : (currentPage - 1) * 5; // 起始行数
        int limit = 5; // 每页条数

        RowBounds rowBounds = new RowBounds(offset, limit);
        Example example = new Example(User.class);
        if (null == user) {
            return userMapper.selectByExampleAndRowBounds(example, rowBounds);
        }
        Example.Criteria criteria = example.createCriteria(); // 条件查询
        if (null != user.getId()) {
            criteria.andEqualTo("id", user.getId());
        }
        if (StringUtils.isNotBlank(user.getNickname())) {
            criteria.andLike("nickname", "%" + user.getNickname() + "%");
        }
        if (StringUtils.isNotBlank(user.getAccount())) {
            criteria.andLike("account", "%" + user.getAccount() + "%");
        }
        if (StringUtils.isNotBlank(user.getRoleName())) {
            criteria.andLike("roleName", "%" + user.getRoleName() + "%");
        }
        if (StringUtils.isNotBlank(user.getGender())) {

            criteria.andEqualTo("gender", user.getGender().equals("女") ? "0" : "1   ");
        }
        if (null == user.getBirthday()) {
            criteria.andEqualTo("birthday", user.getBirthday());
        }
        if (StringUtils.isNotBlank(user.getRegion())) {
            criteria.andLike("region", "%" + user.getRegion() + "%");
        }
        return userMapper.selectByExampleAndRowBounds(example, rowBounds);
    }

}
