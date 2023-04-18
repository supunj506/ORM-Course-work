/*
 * @author : Supun Jaysinghe
 * Date    : 14 April 2023
 * Time    : 11:01 PM
 * Project : d24_hotel_manage_system
 * Created by IntelliJ IDEA.
 */

package lk.ccns.d24.bo.Custom;

import javafx.collections.ObservableList;
import lk.ccns.d24.bo.SuperBO;
import lk.ccns.d24.dto.RoomDTO;

import java.io.IOException;

public interface ManageRoomBO extends SuperBO {
    boolean add(RoomDTO roomDTO)throws IOException;

    ObservableList<RoomDTO> getAllData() throws IOException;

    boolean update(RoomDTO roomDTO) throws IOException;

    RoomDTO findById(String text) throws IOException;

    boolean delete(RoomDTO roomDTO) throws IOException;
}
