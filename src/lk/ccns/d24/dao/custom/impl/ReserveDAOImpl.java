/*
 * @author : Supun Jaysinghe
 * Date    : 18 April 2023
 * Time    : 9:21 AM
 * Project : d24_hotel_manage_system
 * Created by IntelliJ IDEA.
 */
package lk.ccns.d24.dao.custom.impl;

import com.sun.xml.bind.v2.model.core.ID;
import lk.ccns.d24.dao.custom.ReserveDAO;
import lk.ccns.d24.entity.Reservation;
import lk.ccns.d24.util.FactoryConfiguration;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.io.IOException;
import java.util.List;

public class ReserveDAOImpl implements ReserveDAO {

    @Override
    public boolean add(Reservation reservation) throws IOException {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();
        session.save(reservation);
        transaction.commit();
        session.close();
        return true;
    }

    @Override
    public boolean update(Reservation reservation) throws IOException {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();
        session.update(reservation);
        transaction.commit();
        session.close();
        return true;
    }

    @Override
    public boolean delete(Reservation reservation) throws IOException {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();
        session.delete(reservation);
        transaction.commit();
        session.close();
        return true;
    }

    @Override
    public Reservation find(String s) throws IOException {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();
        Reservation reservation = session.get(Reservation.class, s);
        transaction.commit();
        session.close();
        return reservation;

    }


    @Override
    public List<Reservation> findAll() throws IOException {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();
        List<Reservation> reservationList = null;
        Query from_reservation = session.createQuery("from Reservation ");
        reservationList = from_reservation.list();
        transaction.commit();
        session.close();
        return reservationList;
    }

    @Override
    public Session getSession() throws IOException {
        return null;
    }
}
