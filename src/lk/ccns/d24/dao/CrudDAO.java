/*
 * @author : Supun Jaysinghe
 * Date    : 14 April 2023
 * Time    : 2:10 PM
 * Project : d24_hotel_manage_system
 * Created by IntelliJ IDEA.
 */

package lk.ccns.d24.dao;

import org.hibernate.Session;

import java.io.IOException;
import java.util.List;

public interface CrudDAO <Entity,ID>extends SuperDAO{
    boolean add(Entity entity) throws IOException;
    boolean update(Entity entity) throws IOException;
    boolean delete(Entity entity) throws IOException;
    Entity find(ID id) throws IOException;
    List<Entity> findAll() throws IOException;;
    Session getSession() throws IOException;;

}
