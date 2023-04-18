/*
 * @author : Supun Jaysinghe
 * Date    : 19 April 2023
 * Time    : 12:38 AM
 * Project : d24_hotel_manage_system
 * Created by IntelliJ IDEA.
 */

package lk.ccns.d24.dao.custom;

import lk.ccns.d24.dao.CrudDAO;
import lk.ccns.d24.dao.SuperDAO;
import lk.ccns.d24.entity.CustomEntity;

import java.io.IOException;
import java.util.List;

public interface QueryDAO extends SuperDAO {
    List<CustomEntity> getStudentDetail() throws IOException;
}
