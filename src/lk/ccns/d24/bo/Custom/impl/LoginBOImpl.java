/*
 * @author : Supun Jaysinghe
 * Date    : 14 April 2023
 * Time    : 2:06 PM
 * Project : d24_hotel_manage_system
 * Created by IntelliJ IDEA.
 */
package lk.ccns.d24.bo.Custom.impl;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import lk.ccns.d24.bo.Custom.LoginBO;
import lk.ccns.d24.dao.DAOFactory;
import lk.ccns.d24.dao.custom.UserDAO;
import lk.ccns.d24.dto.CustomDTO;
import lk.ccns.d24.dto.UserDTO;
import lk.ccns.d24.entity.CustomEntity;
import lk.ccns.d24.entity.User;

import java.io.IOException;
import java.util.List;

public class LoginBOImpl implements LoginBO {
    private final UserDAO userDAO=(UserDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.USER);

    @Override
    public UserDTO getUserData(String user_Name) throws IOException {
        return getUserDTO(userDAO.find(user_Name));
    }

    @Override
    public List<UserDTO> lordAllUser() {
        return null;
    }

    @Override
    public boolean updateUser(UserDTO userDTO) throws IOException {
        return userDAO.update(getUserEntity(userDTO));
    }

    @Override
    public boolean addUser(UserDTO userDTO) throws IOException {
        return userDAO.add(getUserEntity(userDTO));
    }

    @Override
    public UserDTO login(String userName) throws IOException {
        return getUserDTO(userDAO.find(userName));
    }

    @Override
    public UserDTO getOnlineUser() throws IOException {
        List<User> onlineUser = userDAO.getOnlineUser();

        for(User user:onlineUser){
            return new UserDTO(
                    user.getUser_name(),
                    user.getPassword(),
                    user.getEmail(),
                    user.getStatus());
        }
        return null;
    }


    private UserDTO getUserDTO(User user) {
        if(user==null){
            return null;
        }else {
            return new UserDTO(
                    user.getUser_name(),
                    user.getPassword(),
                    user.getEmail(),
                    user.getStatus()
            );
        }
    }

    private User getUserEntity(UserDTO userDTO) {
        return new User(
                userDTO.getUser_name(),
                userDTO.getPassword(),
                userDTO.getEmail(),
                userDTO.getStatus()
        );
    }

}
