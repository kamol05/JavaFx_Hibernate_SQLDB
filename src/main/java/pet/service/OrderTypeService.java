package pet.service;

import pet.dao.OrderTypeDAO;
import pet.entity.OrderType;
import org.hibernate.Session;
import org.hibernate.query.Query;
import pet.util.SessionUtil;

import java.sql.SQLException;
import java.util.List;

public class OrderTypeService extends SessionUtil implements OrderTypeDAO {
    public void add(OrderType orderType) throws SQLException {
        openTransactionSession();
        getSession().save(orderType);
        closeTransaction();
    }

    public List<OrderType> getAll() throws SQLException {
        openTransactionSession();
        Session session = getSession();
        Query query = session.createQuery("from OrderType");
        List<OrderType> list = query.list();
        return list;
    }

    public OrderType getById(int id) throws SQLException {
        openTransactionSession();
        return (OrderType) getSession().createQuery("from OrderType where id =" + id).uniqueResult();
    }

    public void update(OrderType orderType) throws SQLException {
        openTransactionSession();
        getSession().update(orderType);
    }

    public void remove(OrderType orderType) throws SQLException {
        getSession().remove(orderType);
    }
}
