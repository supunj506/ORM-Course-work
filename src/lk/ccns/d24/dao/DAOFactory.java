/*
 * @author : Supun Jaysinghe
 * Date    : 14 April 2023
 * Time    : 2:10 PM
 * Project : d24_hotel_manage_system
 * Created by IntelliJ IDEA.
 */
package lk.ccns.d24.dao;

import lk.ccns.d24.dao.custom.impl.ReserveDAOImpl;
import lk.ccns.d24.dao.custom.impl.RoomDAOImpl;
import lk.ccns.d24.dao.custom.impl.StudentDAOImpl;
import lk.ccns.d24.dao.custom.impl.UserDAOImpl;

public class DAOFactory {
    private static DAOFactory daoFactory;

    private DAOFactory() {

    }

    public static DAOFactory getDaoFactory() {
        if (daoFactory == null) {
            daoFactory = new DAOFactory();
        }
        return daoFactory;
    }

    public enum DAOTypes {
        USER, ROOM, STUDENT, QUERY_DAO,RESERVE
    }

    public SuperDAO getDAO(DAOTypes types) {
        switch (types) {
            case USER:
                return new UserDAOImpl();
            case ROOM:
                return new RoomDAOImpl();
            case STUDENT:
                return new StudentDAOImpl();
            case RESERVE:
                return new ReserveDAOImpl();

            default:
                return null;
        }
    }
}
