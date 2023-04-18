/*
 * @author : Supun Jaysinghe
 * Date    : 19 April 2023
 * Time    : 12:39 AM
 * Project : d24_hotel_manage_system
 * Created by IntelliJ IDEA.
 */
package lk.ccns.d24.dao.custom.impl;

import lk.ccns.d24.dao.custom.QueryDAO;
import lk.ccns.d24.entity.CustomEntity;
import lk.ccns.d24.util.FactoryConfiguration;
import org.hibernate.Session;
import org.hibernate.query.Query;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class QueryDAOImpl implements QueryDAO {

    @Override
    public List<CustomEntity> getStudentDetail() throws IOException {
        Session session = FactoryConfiguration.getInstance().getSession();
        String sql="SELECT s.student_id ,s.name ,r.date FROM Student s INNER JOIN Reservation r ON s.student_id=r.student.id WHERE r.status='Later'";
        Query query = session.createQuery(sql);
        List<Object[]> list = query.list();
        List<CustomEntity> all=new ArrayList<>();
        for(Object[] objects:list){
            all.add(new CustomEntity(
                    objects[0].toString(),
                    objects[1].toString(),
                    LocalDate.parse(objects[2].toString()))

            );
        }
        return all;

    }
}
