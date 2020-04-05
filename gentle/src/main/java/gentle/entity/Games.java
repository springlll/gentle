package gentle.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import gentle.util.GenderSerializer;
import lombok.Data;

import javax.persistence.Table;
import java.lang.reflect.Field;
import java.util.Date;

@Table(name = "gnt_games")
public class Games {

    private Long games_id;

    private String id; // 账号

    @JsonIgnore // 忽略此字段、不参与序列化（json 不包含这个字段）
    private String name; // 盐

    @JsonIgnore
    private String url; // 密码
    private String picture;
    @JsonIgnore
    private String nickname; // 昵称
        
    private String create_name; // 角色id
    
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Data create_time; // 创建时间  create_time
    
    @JsonFormat(pattern = "yyyy-MM-dd")    
    private Date modified_time; // 时间
    
    private String modified_name; // 性别    modified_name
    
	public String getPicture() {
		return picture;
	}

	public void setPicture(String picture) {
		this.picture = picture;
	}

	public Long getGames_id() {
		return games_id;
	}

	public void setGames_id(Long games_id) {
		this.games_id = games_id;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	public String getCreate_name() {
		return create_name;
	}

	public void setCreate_name(String create_name) {
		this.create_name = create_name;
	}

	public Data getCreate_time() {
		return create_time;
	}

	public void setCreate_time(Data create_time) {
		this.create_time = create_time;
	}

	public Date getModified_time() {
		return modified_time;
	}

	public void setModified_time(Date modified_time) {
		this.modified_time = modified_time;
	}

	public String getModified_name() {
		return modified_name;
	}

	public void setModified_name(String modified_name) {
		this.modified_name = modified_name;
	}

	@Override
	public String toString() {
		return "Games [games_id=" + games_id + ", id=" + id + ", name=" + name + ", url=" + url + ", picture=" + picture
				+ ", nickname=" + nickname + ", create_name=" + create_name + ", create_time=" + create_time
				+ ", modified_time=" + modified_time + ", modified_name=" + modified_name + "]";
	}

	/**
     * 判断是否所有属性为空
     *
     * @param obj
     * @return boolean
     * @throws Exception
     */
    public static boolean isAllFieldNull(Object obj) throws Exception {
        Class stuCla = (Class) obj.getClass(); // 得到类对象
        Field[] fs = stuCla.getDeclaredFields(); //得到属性集合
        boolean flag = true;
        for (Field f : fs) {//遍历属性
            f.setAccessible(true); // 设置属性是可以访问的(私有的也可以)
            Object val = f.get(obj); // 得到此属性的值
            if (val != null) { //只要有1个属性不为空,那么就不是所有的属性值都为空
                flag = false;
                break;
            }
        }
        return flag;
    }
}