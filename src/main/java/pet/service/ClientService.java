package pet.service;

import pet.dao.ClientDAO;
import pet.entity.Client;
import org.hibernate.Session;
import org.hibernate.query.Query;
import pet.util.SessionUtil;

import java.sql.SQLException;
import java.util.List;

public class ClientService extends SessionUtil implements ClientDAO {
    
    public void add(Client client) throws SQLException {
        openTransactionSession();
        getSession().save(client);
        closeTransaction();
    }

    public List<Client> getAll() throws SQLException {
        openTransactionSession();
        Session session = getSession();
        Query query = session.createQuery("from Client");
        List<Client> list = query.list();
        closeTransaction();
        return list;
    }

    public Client getById(int id) throws SQLException {
        openTransactionSession();
        Client client =  (Client) getSession().createQuery("from Client where id =" + id).uniqueResult();
        closeTransaction();
        return client;
    }

    public void update(Client client) throws SQLException {
        openTransactionSession();
        getSession().update(client);
        closeTransaction();
    }

    public void remove(Client client) throws SQLException {
        openTransactionSession();
        getSession().remove(client);
        closeTransaction();
    }
    
}
