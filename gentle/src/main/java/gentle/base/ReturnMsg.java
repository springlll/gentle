package gentle.base;

import java.util.HashMap;
import java.util.Map;

public class ReturnMsg {
private int code;
private String msg;
private Map<String, Object> map = new HashMap<String, Object>();

public ReturnMsg(String string, String fileName) {
	// TODO Auto-generated constructor stub
}

public int getCode() {
    return code;
}
   
public void setCode(int code) {
    this.code = code;
}

public String getMsg() {
    return msg;
}

public void setMsg(String msg) {
    this.msg = msg;
}

public Map<String, Object> getMap() {
    return map;
}

public void setMap(Map<String, Object> map) {
    this.map = map;
}
 
public static ReturnMsg success(){
	ReturnMsg msg = new ReturnMsg(null, null);
    msg.setCode(100);
    msg.setMsg("成功");
    return msg;
}

public static ReturnMsg error(){
	ReturnMsg msg = new ReturnMsg(null, null);
    msg.setCode(200);
    msg.setMsg("失败");
    return msg;
}

public ReturnMsg add(String key,Object value) {
    map.put(key, value);
    return this;
}
 
}
