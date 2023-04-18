/*
 * @author : Supun Jaysinghe
 * Date    : 14 April 2023
 * Time    : 7:09 PM
 * Project : d24_hotel_manage_system
 * Created by IntelliJ IDEA.
 */
package lk.ccns.d24.entity;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class User {
    @Id
    private String User_name;
    private String password;

    public User() {
    }

    public User(String user_name, String password) {
        User_name = user_name;
        this.password = password;
    }

    public String getUser_name() {
        return User_name;
    }

    public void setUser_name(String user_name) {
        User_name = user_name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
