package cn.h2uwei.unsafe;

/**
 * 用户模型
 *
 * @author h.mongo
 * @date 2023/7/28 22:56
 */
public class User {
    private String name;

    public User() {
        this.name = "h.mongo";
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
