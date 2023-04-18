/*
 * @author : Supun Jaysinghe
 * Date    : 14 April 2023
 * Time    : 1:59 PM
 * Project : d24_hotel_manage_system
 * Created by IntelliJ IDEA.
 */
package lk.ccns.d24.bo;

import lk.ccns.d24.bo.Custom.impl.LoginBOImpl;
import lk.ccns.d24.bo.Custom.impl.ManageReserveBOImpl;
import lk.ccns.d24.bo.Custom.impl.ManageRoomBOImpl;
import lk.ccns.d24.bo.Custom.impl.ManageStudentBOImpl;

public class BOFactory {
    private static BOFactory boFactory;

    private BOFactory() {

    }
    public static BOFactory getBoFactory() {
        if (boFactory == null) {
            boFactory = new BOFactory();
        }
        return boFactory;
    }
    public enum BOTypes {
        LOGIN_USER, MANAGE_STUDENT, MANAGE_ROOM, MANAGE_RESERVE
    }
    public SuperBO getBO(BOTypes types) {
        switch (types) {
            case LOGIN_USER:
                return new LoginBOImpl();
            case MANAGE_ROOM:
                return new ManageRoomBOImpl();
            case MANAGE_STUDENT:
                return new ManageStudentBOImpl();
            case MANAGE_RESERVE:
                return new ManageReserveBOImpl();

            default:
                return null;
        }
    }

}
