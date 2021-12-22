package pet.service;

import pet.dao.OrderDAO;
import pet.entity.Orders;
import org.hibernate.Session;
import pet.util.SessionUtil;

import org.hibernate.query.*;
import java.sql.SQLException;
import java.util.List;

public class OrderService extends SessionUtil implements OrderDAO {
    public void add(Orders orders) throws SQLException {
        openTransactionSession();
        getSession().save(orders);
        closeTransaction();
    }

    public List<Orders> getAll() throws SQLException {
        openTransactionSession();
        Session session = getSession();
        Query query = session.createQuery("from Orders");
        List<Orders> list = query.list();
        closeTransaction();
        return list;
    }

    public Orders getById(int id) throws SQLException {
        openTransactionSession();
        Orders orders = (Orders) getSession().createQuery("from Orders where id =" + id).uniqueResult();
        closeTransaction();
        return orders;
    }

    public void update(Orders orders) throws SQLException {
        openTransactionSession();
        getSession().update(orders);
        closeTransaction();
    }

    public void remove(Orders orders) throws SQLException {
        openTransactionSession();
        getSession().remove(orders);
        closeTransaction();
    }
}
