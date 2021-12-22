package pet.service;

import org.hibernate.Session;
import org.hibernate.query.Query;
import pet.dao.DailyProductionDao;
import pet.entity.DailyProduction;
import java.sql.SQLException;
import java.util.List;
import pet.util.SessionUtil;

public class DailyProductionService extends SessionUtil implements DailyProductionDao {
    
    public void add(DailyProduction dailyProduction) throws SQLException {
        openTransactionSession();
        getSession().save(dailyProduction);
        closeTransaction();
    }

    public List<DailyProduction> getAll() throws SQLException {
        openTransactionSession();
        Session session = getSession();
        Query query = session.createQuery("from DailyProduction");
        List<DailyProduction> list = query.list();
        closeTransaction();
        return list;
    }

    public DailyProduction getById(int id) throws SQLException {
        openTransactionSession();
        DailyProduction dailyProduction = (DailyProduction) getSession().createQuery("from DailyProduction where id =" + id).uniqueResult();
        closeTransaction();
        return dailyProduction;
    }

    public void update(DailyProduction dailyProduction) throws SQLException {
        openTransactionSession();
        getSession().update(dailyProduction);
        closeTransaction();
    }

    public void remove(DailyProduction dailyProduction) throws SQLException {
        openTransactionSession();
        getSession().remove(dailyProduction);
        closeTransaction();
    }

}
