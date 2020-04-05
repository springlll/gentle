package gentle.service;

import gentle.entity.User;

import java.util.List;

/**
 * @author silence
 * @date 2019/1/4 9:45
 */
public interface IUserService {


    /**
     * 查总条数
     * @param user
     * @return
     */
    int getTotalUserNum(User user) throws Exception;

    /**
     * 编辑用户
     *
     * @param user
     * @return
     */
    Integer updateUser(User user);

    /**
     * 查所有
     * @param User
     * @param currentPage
     * @return
     */
    List<User> getUserList(User User,Integer currentPage);

    /**
     * 新增用户
     *
     * @param user
     * @return
     */
    Integer addUser(User user);

    /**
     * 删除用户
     *
     * @param userIds
     * @return
     */
    Integer delUser(String userIds);

}
