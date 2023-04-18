/*
 * @author : Supun Jaysinghe
 * Date    : 14 April 2023
 * Time    : 2:00 PM
 * Project : d24_hotel_manage_system
 * Created by IntelliJ IDEA.
 */

package lk.ccns.d24.bo.Custom;

import lk.ccns.d24.bo.SuperBO;
import lk.ccns.d24.dto.UserDTO;
import lk.ccns.d24.entity.User;

import java.io.IOException;
import java.util.List;

public interface LoginBO extends SuperBO {
    User getUserData(String user_Name) throws IOException;
    List<UserDTO> lordAllUser();
    boolean updateUser(UserDTO userDTO);
    boolean addUser(UserDTO userDTO) throws IOException;
    boolean login(UserDTO userDTO)throws IOException;



}
