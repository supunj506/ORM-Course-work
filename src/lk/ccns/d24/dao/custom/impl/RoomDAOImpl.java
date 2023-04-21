/*
 * @author : Supun Jaysinghe
 * Date    : 14 April 2023
 * Time    : 11:15 PM
 * Project : d24_hotel_manage_system
 * Created by IntelliJ IDEA.
 */
package lk.ccns.d24.dao.custom.impl;

import lk.ccns.d24.dao.custom.RoomDAO;
import lk.ccns.d24.entity.Room;
import lk.ccns.d24.util.FactoryConfiguration;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.io.IOException;
import java.util.List;

public class RoomDAOImpl implements RoomDAO {
    @Override
    public boolean add(Room room) throws IOException {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();
        session.save(room);
        transaction.commit();
        session.close();
        return true;
    }

    @Override
    public boolean update(Room room) throws IOException {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();
        session.update(room);
        transaction.commit();
        session.close();
        return true;
    }

    @Override
    public boolean delete(Room room) throws IOException {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();
        session.delete(room);
        transaction.commit();
        session.close();
        return true;
    }

    @Override
    public Room find(String roomId) throws IOException {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();
        Room room = session.get(Room.class, roomId);
        transaction.commit();
        session.close();
        return room;
    }

    @Override
    public List<Room> findAll() throws IOException {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();
        List<Room> roomList;
        Query from_room = session.createQuery("from Room");
        roomList=from_room.list();
        transaction.commit();
        session.close();
        return roomList;
    }

    @Override
    public Session getSession() throws IOException {
        return FactoryConfiguration.getInstance().getSession();
    }

}
