/*
 * @author : Supun Jaysinghe
 * Date    : 18 April 2023
 * Time    : 11:23 AM
 * Project : d24_hotel_manage_system
 * Created by IntelliJ IDEA.
 */
package lk.ccns.d24.view.tm;

public class ReservationTM {
    private String reservation_id;
    private String date;
    private String status;
    private String room_id;
    private String student_id;

    public ReservationTM() {
    }

    public ReservationTM(String reservation_id, String date, String status, String room_id, String student_id) {
        this.reservation_id = reservation_id;
        this.date = date;
        this.status = status;
        this.room_id = room_id;
        this.student_id = student_id;
    }

    public String getReservation_id() {
        return reservation_id;
    }

    public void setReservation_id(String reservation_id) {
        this.reservation_id = reservation_id;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getRoom_id() {
        return room_id;
    }

    public void setRoom_id(String room_id) {
        this.room_id = room_id;
    }

    public String getStudent_id() {
        return student_id;
    }

    public void setStudent_id(String student_id) {
        this.student_id = student_id;
    }
}
