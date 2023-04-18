/*
 * @author : Supun Jaysinghe
 * Date    : 15 April 2023
 * Time    : 6:36 PM
 * Project : d24_hotel_manage_system
 * Created by IntelliJ IDEA.
 */

import lk.ccns.d24.entity.Room;
import lk.ccns.d24.util.FactoryConfiguration;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.io.IOException;

public class Demo {
    public static void main(String[] args) throws IOException {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();
        session.delete(new Room("R-001","NON-AC",50,3000));
        transaction.commit();
        session.close();
    }
}
