package pet.service;

import pet.dao.QualityTypeDAO;
import pet.entity.QualityType;
import org.hibernate.Session;
import org.hibernate.query.Query;
import pet.util.SessionUtil;

import java.sql.SQLException;
import java.util.List;

public class QualityTypeService extends SessionUtil implements QualityTypeDAO {
    public void add(QualityType qualityType) throws SQLException {
        openTransactionSession();
        getSession().save(qualityType);
        closeTransaction();
    }

    public List<QualityType> getAll() throws SQLException {
        openTransactionSession();
        Session session = getSession();
        Query query = session.createQuery("from QualityType");
        List<QualityType> list = query.list();
        closeTransaction();
        return list;
    }

    public QualityType getById(int id) throws SQLException {
        openTransactionSession();
        QualityType qualityType = (QualityType) getSession().createQuery("from QualityType where id =" + id).uniqueResult();
        closeTransaction();
        return qualityType;
    }

    public void update(QualityType qualityType) throws SQLException {
        openTransactionSession();
        getSession().update(qualityType);
        closeTransaction();
    }

    public void remove(QualityType qualityType) throws SQLException {
        openTransactionSession();
        getSession().remove(qualityType);
        closeTransaction();
    }
}
