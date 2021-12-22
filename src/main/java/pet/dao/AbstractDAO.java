package pet.dao;

import java.sql.SQLException;
import java.util.List;

public interface AbstractDAO<T>  {

    void add (T t) throws SQLException;
    List<T> getAll() throws SQLException;
    T getById(int id,T t) throws SQLException;
    void removeById() throws SQLException;
    void update(T t) throws SQLException;
    void remove(T t) throws SQLException;
}
