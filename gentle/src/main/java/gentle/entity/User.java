package gentle.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import gentle.util.GenderSerializer;

import javax.persistence.Table;
import java.lang.reflect.Field;
import java.util.Date;

@Table(name = "sys_user")
public class User {

    private Long id;

    private String account; // 账号

    @JsonIgnore // 忽略此字段、不参与序列化（json 不包含这个字段）
    private String salt; // 盐

    @JsonIgnore
    private String password; // 密码

    @JsonIgnore
    private Long roleId; // 角色id

    private String roleName; // 角色名称

    private String nickname; // 昵称

    @JsonSerialize(using = GenderSerializer.class)
    private String gender; // 性别

    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date birthday; // 生日

    private String region; // 地区

    public User() {
    }

    public User(String nickname, Long roleId) {
        this.roleId = roleId;
        this.nickname = nickname;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getSalt() {
        return salt;
    }

    public void setSalt(String salt) {
        this.salt = salt;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Long getRoleId() {
        return roleId;
    }

    public void setRoleId(Long roleId) {
        this.roleId = roleId;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
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