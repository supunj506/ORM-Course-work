/*
 * @author : Supun Jaysinghe
 * Date    : 14 April 2023
 * Time    : 12:40 PM
 * Project : d24_hotel_manage_system
 * Created by IntelliJ IDEA.
 */
package lk.ccns.d24.entity;

import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.time.LocalDate;
@Entity
public class Reservation {
    @Id
    private String res_id;
    @CreationTimestamp
    private LocalDate date;

    @ManyToOne
    @JoinColumn(name = "student_id",referencedColumnName = "student_id")
    private Student student;

    @ManyToOne
    @JoinColumn(name = "room_id",referencedColumnName = "room_id")
    private Room room;

    private String status;

    public Reservation() {
    }

    public Reservation(String res_id, LocalDate date, Student student, Room room, String status) {
        this.res_id = res_id;
        this.date = date;
        this.student = student;
        this.room = room;
        this.status = status;
    }

    public Reservation(String res_id, Student student, Room room, String status) {
        this.res_id = res_id;
        this.student = student;
        this.room = room;
        this.status = status;
    }

    @Override
    public String toString() {
        return "Reservation{" +
                "res_id='" + res_id + '\'' +
                ", date=" + date +
                ", student=" + student +
                ", room=" + room +
                ", status='" + status + '\'' +
                '}';
    }

    public String getRes_id() {
        return res_id;
    }

    public void setRes_id(String res_id) {
        this.res_id = res_id;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public Room getRoom() {
        return room;
    }

    public void setRoom(Room room) {
        this.room = room;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
