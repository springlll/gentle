package gentle.entity;

import lombok.Data;
/**
 *
 * @author silence
 * @date 2018/7/6 10:58
 */
@Data
public class User {

    private String name;
    private int age;

    public User( String name, int age) {
        this.name = name;
        this.age = age;
    }
}
