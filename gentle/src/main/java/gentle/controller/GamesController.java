package gentle.controller;

import com.google.common.collect.Lists;

import gentle.entity.Games;
import gentle.entity.User;
import gentle.service.IGamesService;
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
@RequestMapping("games")
public class GamesController {

    @Autowired
    private IGamesService gamseService;

    /**
     * 查询
     *
     * @param user
     * @return
     */
    @RequestMapping("/getGames")
    @CrossOrigin
    private List<Object> getUserList(Games games_id, Integer currentPage) throws Exception {

        List<Object> userList = Lists.newArrayList();
        List<Games> userL = gamseService.getGamesList(games_id, currentPage);
        int total = gamseService.getTotalGamesNum(games_id);
        // 每页5行。总页数：向上取整。
        userList.add((total % 5 == 0 ? total / 5 : total / 5 + 1));

        for (Games u : userL) {
            List<Object> userArray = Lists.newArrayList();
            userArray.add(u.getId());
            userArray.add(u.getGames_id());
            userArray.add(u.getName());
            userArray.add(u.getNickname());
            userArray.add(u.getUrl());
            userArray.add(u.getPicture());
            userArray.add(u.getCreate_time());
            userArray.add(u.getCreate_name());
            userArray.add(u.getModified_time());
            userArray.add(u.getModified_name());
//            if (null != u.getGender()) {
//                userArray.add(u.getGender().equals("0") ? "女" : "男");
//            } else {
//                userArray.add(u.getGender());
//            }
//            if (null != u.getBirthday()) {
//                userArray.add(DateUtil.dateToString(u.getBirthday()));
//            }
//            userArray.add(u.getRegion());
            System.out.println(userArray);
            userList.add(userArray);
        }
        return userList;
    }




}
