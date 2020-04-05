package gentle.util;

import gentle.entity.User;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.TreeSet;
import java.util.stream.Collectors;

/**
 * List 工具类
 * @author silence
 * @date 2018/9/18 8:56
 * */


public class ListUtil {

    public static void main(String[] args) {

        List<String> strList = new ArrayList(){{add("我");}{add("你");}{add("他");}{add("他");}{add("他");}};
        List<Integer> intList = new ArrayList(){{add(11);}{add(11);}{add(33);}{add(11);}};

        System.out.print("\n 去重前：\n");
        listFor(strList);
        System.out.println("");
        listFor(intList);

        System.out.println("\n 去重后：");
        System.out.println(deleteSame(strList));
        System.out.println(deleteSame(intList));


  List<User> UserList = new ArrayList<User>(){
         {add(new User("花间",24L));}
         {add(new User("暮云",28L));}
         {add(new User("暮云",28L));}
         {add(new User("彻风",24L));}
         {add(new User("弥禁",16L));}
         {add(new User("清寞",24L));}};

        System.out.println("\n ");
        System.out.println("去掉同龄的：\n"+deleteSameByOne(UserList));
        System.out.println("去掉同龄且同名的：\n"+deleteSameByMany(UserList));
    }

    /**
     * for循环
     * @param list
     * @param <T>
     */
    public static <T> void listFor(List<T> list) {
        list.forEach(each -> System.out.print(each+" "));
    }

    /**
     * 基本数据类型去重
     * @param list
     * @param <T>
     * @return
     */
    public static <T> List<T> deleteSame(List<T> list) {
        return list.stream().distinct().collect(Collectors.toList());
    }

    /**
     * 按对象属性去重
     * @param Users
     * @return
     */
    public static List<User> deleteSameByOne(List<User> Users) {

        // 1.单个属性去重 （ 性别 ）
        return Users.stream().collect(Collectors.collectingAndThen(
                Collectors.toCollection(() -> new TreeSet<>(Comparator.comparing(User::getGender))),
                ArrayList::new));
    }

    /**
     * 按对象属性去重
     * @param Users
     * @return
     */
    public static List<User> deleteSameByMany(List<User> Users) {

        // 2.多属性去重( 昵称和性别 )
        return Users.stream().collect(Collectors.collectingAndThen(
                Collectors.toCollection(() -> new TreeSet<>(
                        Comparator.comparing(UserDep -> UserDep.getNickname() + UserDep.getGender()))),
                ArrayList::new));
    }
}

