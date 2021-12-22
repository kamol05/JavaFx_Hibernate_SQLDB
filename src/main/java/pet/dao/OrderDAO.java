package pet.dao;

import pet.entity.Orders;

import java.sql.SQLException;
import java.util.List;

public interface OrderDAO {
    void add (Orders orders) throws SQLException;
    List<Orders> getAll() throws SQLException;
    Orders getById(int id) throws SQLException;
    void update(Orders orders) throws SQLException;
    void remove(Orders orders) throws SQLException;
}
