/*
 * @author : Supun Jaysinghe
 * Date    : 18 April 2023
 * Time    : 9:17 AM
 * Project : d24_hotel_manage_system
 * Created by IntelliJ IDEA.
 */

package lk.ccns.d24.bo.Custom;

import com.jfoenix.controls.JFXTextField;
import javafx.collections.ObservableList;
import lk.ccns.d24.bo.SuperBO;
import lk.ccns.d24.dto.CustomDTO;
import lk.ccns.d24.dto.ReservationDTO;

import java.io.IOException;

public interface ManageReserveBO extends SuperBO {
    boolean saveReserve(ReservationDTO reservationDTO) throws IOException;

    ObservableList<ReservationDTO> getAllDetails() throws IOException;


    boolean updateReserveData(ReservationDTO reserveDTO) throws IOException;

    boolean deleteReserveData(ReservationDTO reserveDTO) throws IOException;

    ObservableList<CustomDTO> getRemainingKeyMoneyStudentDetails() throws IOException;

    int getReserveCount() throws IOException;

    ReservationDTO checkExistsReserve(String id) throws IOException;
}
