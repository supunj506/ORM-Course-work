/*
 * @author : Supun Jaysinghe
 * Date    : 14 April 2023
 * Time    : 2:06 PM
 * Project : d24_hotel_manage_system
 * Created by IntelliJ IDEA.
 */
package lk.ccns.d24.bo.Custom.impl;

import lk.ccns.d24.bo.Custom.LoginBO;
import lk.ccns.d24.dao.DAOFactory;
import lk.ccns.d24.dao.custom.UserDAO;
import lk.ccns.d24.dto.UserDTO;
import lk.ccns.d24.entity.User;

import java.io.IOException;
import java.util.List;

public class LoginBOImpl implements LoginBO {
    private final UserDAO userDAO=(UserDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.USER);

    @Override
    public User getUserData(String user_Name) throws IOException {
        return null;
    }

    @Override
    public List<UserDTO> lordAllUser() {
        return null;
    }

    @Override
    public boolean updateUser(UserDTO userDTO) {
        return false;
    }

    @Override
    public boolean addUser(UserDTO userDTO) throws IOException {
        User user = userDAO.find(userDTO.getUser_name());
        if(user == null){
            return userDAO.add(new User(userDTO.getUser_name(), userDTO.getPassword()));
        }
        return false;
    }

    @Override
    public boolean login(UserDTO userDTO) throws IOException {
        User user = userDAO.find(userDTO.getUser_name());
        return user != null && user.getPassword().equals(userDTO.getPassword());
    }

}
