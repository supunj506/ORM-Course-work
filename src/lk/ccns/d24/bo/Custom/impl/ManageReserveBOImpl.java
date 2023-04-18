/*
 * @author : Supun Jaysinghe
 * Date    : 18 April 2023
 * Time    : 9:18 AM
 * Project : d24_hotel_manage_system
 * Created by IntelliJ IDEA.
 */
package lk.ccns.d24.bo.Custom.impl;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import lk.ccns.d24.bo.Custom.ManageReserveBO;
import lk.ccns.d24.dao.DAOFactory;
import lk.ccns.d24.dao.custom.QueryDAO;
import lk.ccns.d24.dao.custom.ReserveDAO;
import lk.ccns.d24.dao.custom.RoomDAO;
import lk.ccns.d24.dao.custom.StudentDAO;
import lk.ccns.d24.dto.CustomDTO;
import lk.ccns.d24.dto.ReservationDTO;
import lk.ccns.d24.entity.CustomEntity;
import lk.ccns.d24.entity.Reservation;
import lk.ccns.d24.entity.Room;
import lk.ccns.d24.entity.Student;

import java.io.IOException;
import java.util.List;

public class ManageReserveBOImpl implements ManageReserveBO {
    private final ReserveDAO reserveDAO = (ReserveDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.RESERVE);
    private final StudentDAO studentDAO = (StudentDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.STUDENT);
    private final RoomDAO roomDAO = (RoomDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.ROOM);
    private final QueryDAO queryDAO = (QueryDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.QUERY_DAO);

    @Override
    public boolean saveReserve(ReservationDTO reservationDTO) throws IOException {
        return reserveDAO.add(getReserveEntity(reservationDTO));
    }

    @Override
    public ObservableList<ReservationDTO> getAllDetails() throws IOException {
        List<Reservation> all = reserveDAO.findAll();
        ObservableList<ReservationDTO> allReservation = FXCollections.observableArrayList();
        for (Reservation reservation : all) {
            allReservation.add(getReserveDTO(reservation));
        }
        return allReservation;

    }

    @Override
    public boolean updateReserveData(ReservationDTO reserveDTO) throws IOException {
        Student student = studentDAO.find(reserveDTO.getStudent_id());
        Room room = roomDAO.find(reserveDTO.getRoom_id());
        return reserveDAO.update(new Reservation(
                reserveDTO.getReserve_id(),
                reserveDTO.getDate(),
                student,
                room,
                reserveDTO.getStatus()));
    }

    @Override
    public boolean deleteReserveData(ReservationDTO reserveDTO) throws IOException {
        return reserveDAO.delete(getReserveEntity(reserveDTO));

    }

    @Override
    public ObservableList<CustomDTO> getRemainingKeyMoneyStudentDetails() throws IOException {
        List<CustomEntity> detail=queryDAO.getStudentDetail();
        ObservableList<CustomDTO> allDetails=FXCollections.observableArrayList() ;
        for(CustomEntity custom:detail){
            allDetails.add(new CustomDTO(
                    custom.getDate(),
                    custom.getStudent_id(),
                    custom.getName()
            ));
        }
        return allDetails;
    }


    private ReservationDTO getReserveDTO(Reservation reservation) {
        return new ReservationDTO(
                reservation.getRes_id(),
                reservation.getDate(),
                reservation.getStudent().getStudent_id(),
                reservation.getRoom().getRoom_id(),
                reservation.getStatus()
        );
    }


    private Reservation getReserveEntity(ReservationDTO reservationDTO) throws IOException {
        Student student = studentDAO.find(reservationDTO.getStudent_id());
        Room room = roomDAO.find(reservationDTO.getRoom_id());
        return new Reservation(
                reservationDTO.getReserve_id(),
                student,
                room,
                reservationDTO.getStatus()
        );

    }
}
