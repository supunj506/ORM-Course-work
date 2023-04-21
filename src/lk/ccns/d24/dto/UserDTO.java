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
    private String email;
    private String status;

    public UserDTO() {
    }

    public UserDTO(String user_name, String password, String email, String status) {
        this.user_name = user_name;
        this.password = password;
        this.email = email;
        this.status = status;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
