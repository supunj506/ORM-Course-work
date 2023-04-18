/*
 * @author : Supun Jaysinghe
 * Date    : 14 April 2023
 * Time    : 11:01 PM
 * Project : d24_hotel_manage_system
 * Created by IntelliJ IDEA.
 */
package lk.ccns.d24.bo.Custom.impl;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import lk.ccns.d24.bo.Custom.ManageRoomBO;
import lk.ccns.d24.dao.DAOFactory;
import lk.ccns.d24.dao.custom.RoomDAO;
import lk.ccns.d24.dto.RoomDTO;
import lk.ccns.d24.entity.Room;

import java.io.IOException;
import java.util.List;

public class ManageRoomBOImpl implements ManageRoomBO {
    private final RoomDAO roomDAO=(RoomDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.ROOM);
    @Override
    public boolean add(RoomDTO roomDTO) throws IOException {
       return roomDAO.add(getRoomEntity(roomDTO));
    }

    @Override
    public ObservableList<RoomDTO> getAllData() throws IOException {
        List<Room> all = roomDAO.findAll();
        ObservableList<RoomDTO> obRoomList= FXCollections.observableArrayList();
        for(Room room:all){
            obRoomList.add(getRoomDTO(room));
        }
        return obRoomList;
    }

    @Override
    public boolean update(RoomDTO roomDTO) throws IOException {
        return roomDAO.update(getRoomEntity(roomDTO));
    }

    @Override
    public RoomDTO findById(String roomId) throws IOException {
         return getRoomDTO(roomDAO.find(roomId));
    }

    @Override
    public boolean delete(RoomDTO roomDTO) throws IOException {
        return roomDAO.delete(getRoomEntity(roomDTO));
    }


    private Room getRoomEntity(RoomDTO roomDTO){
        return new Room(roomDTO.getRoom_id(),roomDTO.getType(),roomDTO.getKey_money(),roomDTO.getQty());
    }

    private RoomDTO getRoomDTO(Room room){
        return new RoomDTO(room.getRoom_id(), room.getType(), room.getKey_money(), room.getQty());
    }
}
