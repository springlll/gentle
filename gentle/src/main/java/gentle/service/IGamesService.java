package gentle.service;

import gentle.entity.Games;
import gentle.entity.User;

import java.util.List;

import javax.sound.midi.Instrument;

/**
 * @author silence
 * @date 2019/1/4 9:45
 */
public interface IGamesService {


    /**
     * 查总条数
     * @param user
     * @return
     */
    int getTotalGamesNum(Games games_id) throws Exception;



    /**
     * 查所有用户
     * @param User
     * @param currentPage
     * @return
     */
    List<Games> getGamesList(Games games_id,Integer currentPage);



	void save(Instrument instrument);


}
