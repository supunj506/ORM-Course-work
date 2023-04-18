/*
 * @author : Supun Jaysinghe
 * Date    : 17 April 2023
 * Time    : 10:54 PM
 * Project : d24_hotel_manage_system
 * Created by IntelliJ IDEA.
 */
package lk.ccns.d24.dto;

import java.time.LocalDate;

public class ReservationDTO {
    private String reserve_id;
    private LocalDate date;
    private String student_id;
    private String room_id;
    private String status;

    public ReservationDTO() {
    }

    public ReservationDTO(String reserve_id, LocalDate date, String student_id, String room_id, String status) {
        this.reserve_id = reserve_id;
        this.date = date;
        this.student_id = student_id;
        this.room_id = room_id;
        this.status = status;
    }

    public ReservationDTO(String reserve_id, String student_id, String room_id, String status) {
        this.reserve_id = reserve_id;
        this.student_id = student_id;
        this.room_id = room_id;
        this.status = status;
    }

    @Override
    public String toString() {
        return "ReservationDTO{" +
                "reserve_id='" + reserve_id + '\'' +
                ", date=" + date +
                ", student_id='" + student_id + '\'' +
                ", room_id='" + room_id + '\'' +
                ", status='" + status + '\'' +
                '}';
    }

    public String getReserve_id() {
        return reserve_id;
    }

    public void setReserve_id(String reserve_id) {
        this.reserve_id = reserve_id;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public String getStudent_id() {
        return student_id;
    }

    public void setStudent_id(String student_id) {
        this.student_id = student_id;
    }

    public String getRoom_id() {
        return room_id;
    }

    public void setRoom_id(String room_id) {
        this.room_id = room_id;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
