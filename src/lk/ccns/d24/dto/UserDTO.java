/*
 * @author : Supun Jaysinghe
 * Date    : 14 April 2023
 * Time    : 7:08 PM
 * Project : d24_hotel_manage_system
 * Created by IntelliJ IDEA.
 */
package lk.ccns.d24.dto;

public class UserDTO {
    private String user_name;
    private String password;

    public UserDTO() {
    }

    public UserDTO(String user_name, String password) {
        this.user_name = user_name;
        this.password = password;
    }

    @Override
    public String toString() {
        return "UserDTO{" +
                "user_name='" + user_name + '\'' +
                ", password='" + password + '\'' +
                '}';
    }

    public String getUser_name() {
        return user_name;
    }

    public void setUser_name(String user_name) {
        this.user_name = user_name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
