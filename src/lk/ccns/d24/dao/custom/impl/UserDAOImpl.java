/*
 * @author : Supun Jaysinghe
 * Date    : 14 April 2023
 * Time    : 2:11 PM
 * Project : d24_hotel_manage_system
 * Created by IntelliJ IDEA.
 */
package lk.ccns.d24.dao.custom.impl;

import lk.ccns.d24.dao.custom.UserDAO;
import lk.ccns.d24.entity.CustomEntity;
import lk.ccns.d24.entity.User;
import lk.ccns.d24.util.FactoryConfiguration;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class UserDAOImpl implements UserDAO {
    @Override
    public boolean add(User user) throws IOException {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();
        session.save(user);
        transaction.commit();
        session.close();
        return true;
    }

    @Override
    public boolean update(User user) throws IOException {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();
        session.update(user);
        transaction.commit();
        session.close();
        return true;
    }

    @Override
    public boolean delete(User user) throws IOException {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();
        session.delete(user);
        transaction.commit();
        session.close();
        return true;
    }

    @Override
    public User find(String user_name) throws IOException {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();
        User user = session.get(User.class, user_name);
        transaction.commit();
        session.close();
        return user;

    }

    @Override
    public List<User> findAll() {
        return null;
    }

    @Override
    public Session getSession() throws IOException {
        return null;
    }

    @Override
    public List<User>  getOnlineUser() throws IOException {


        Session session = FactoryConfiguration.getInstance().getSession();
        String sql="SELECT u.user_name ,u.password ,u.email FROM User u  WHERE u.status='online'";
        Query query = session.createQuery(sql);
        List<Object[]> list = query.list();
        List<User> all=new ArrayList<>();
        for(Object[] objects:list){
            all.add(new User(
                    objects[0].toString(),
                    objects[1].toString(),
                    objects[2].toString(),
                    "online"));

        }
        return all;

    }
}
