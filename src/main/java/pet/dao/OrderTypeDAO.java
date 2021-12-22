package pet.dao;

import pet.entity.OrderType;

import java.sql.SQLException;
import java.util.List;

public interface OrderTypeDAO {
    void add (OrderType orderType) throws SQLException;
    List<OrderType> getAll() throws SQLException;
    OrderType getById(int id) throws SQLException;
    void update(OrderType orderType) throws SQLException;
    void remove(OrderType orderType) throws SQLException;
}
