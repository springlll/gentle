package gentle.controller;

import com.google.common.collect.Lists;
import com.mysql.cj.log.Log;

import gentle.entity.User;
import gentle.service.IUserService;
import gentle.util.DateUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * 用户
 *
 * @author silence
 * @date 2018/7/6 11:00
 */

@RestController
@RequestMapping("user")
public class UserController {

    @Autowired
    private IUserService userService;

    /**
     * 查询
     *
     * @param user
     * @return
     */
    @RequestMapping("/getUser")
    @CrossOrigin
    private List getUserList(User user, Integer currentPage) throws Exception {
    	System.out.println("xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx");
        List userList = Lists.newArrayList();
        List<User> userL = userService.getUserList(user, currentPage);
        int total = userService.getTotalUserNum(user);
        // 每页5行。总页数：向上取整。
        userList.add((total % 5 == 0 ? total / 5 : total / 5 + 1));

        for (User u : userL) {
            List userArray = Lists.newArrayList();
            userArray.add(u.getId());
            userArray.add(u.getAccount());
            userArray.add(u.getRoleName());
            userArray.add(u.getNickname());
            if (null != u.getGender()) {
                userArray.add(u.getGender().equals("0") ? "女" : "男");
            } else {
                userArray.add(u.getGender());
            }
            if (null != u.getBirthday()) {
                userArray.add(DateUtil.dateToString(u.getBirthday()));
            }
            userArray.add(u.getRegion());
            userList.add(userArray);
        }
        return userList;
    }

    /**
     * 新增
     *
     * @param user
     * @return
     */
    @RequestMapping("/addUser")
    private Integer addUser(User user) {
    	System.out.println("xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx");
        return userService.addUser(user);
    }

    /**
     * 删除
     *
     * @param userIds
     * @return
     */
    @RequestMapping("/delUser")
    private Integer delUser(String userIds) {

        return userService.delUser(userIds);
    }

    /**
     * 编辑
     *
     * @param user
     * @return
     */
    @RequestMapping("/updateUser")
    private Integer updateUser(User user) {

        return userService.updateUser(user);
    }

}
