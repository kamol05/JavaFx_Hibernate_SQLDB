package pet.service;

import pet.dao.QualityDAO;
import pet.entity.Quality;
import org.hibernate.Session;
import org.hibernate.query.Query;
import pet.util.SessionUtil;

import java.sql.SQLException;
import java.util.List;

public class QualityService extends SessionUtil implements QualityDAO {
    public void add(Quality quality) throws SQLException {
        openTransactionSession();
        getSession().save(quality);
        closeTransaction();
    }

    public List<Quality> getAll() throws SQLException {
        openTransactionSession();
        Session session = getSession();
        Query query = session.createQuery("from Quality");
        List<Quality> list = query.list();
        closeTransaction();
        return list;
    }

    public Quality getById(int id) throws SQLException {
        openTransactionSession();
        Quality quality = (Quality) getSession().createQuery("from Quality where id =" + id).uniqueResult();
        closeTransaction();
        return quality;
    }

    public void update(Quality quality) throws SQLException {
        openTransactionSession();
        getSession().update(quality);
        closeTransaction();
    }

    public void remove(Quality quality) throws SQLException {
        openTransactionSession();
        getSession().remove(quality);
        closeTransaction();
    }
}
