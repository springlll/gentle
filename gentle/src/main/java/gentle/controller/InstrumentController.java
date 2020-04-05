package gentle.controller;
import javax.sound.midi.Instrument;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import gentle.base.Result;
import gentle.service.IGamesService;

@RestController
@RequestMapping("/instrument")
public class InstrumentController {
 
    //注入
    @Autowired
    private IGamesService instrumentService;
 
    /**
     * 添加
     *
     * @param instrument
     * @return
     */
    @RequestMapping("/save")
    public Result save(Instrument instrument) {
        if(instrument != null){
            try{
                instrumentService.save(instrument);
                
                return new Result(true,"添加成功");
            }catch (Exception e){
                e.printStackTrace();
            }
        }
        return new Result(false, "发生未知错误");
    }
}