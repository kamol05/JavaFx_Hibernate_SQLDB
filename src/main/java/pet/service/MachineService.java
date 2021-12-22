package pet.service;


import pet.dao.MachineDAO;
import pet.entity.Machine;
import org.hibernate.Session;
import org.hibernate.query.Query;
import pet.util.SessionUtil;
import java.sql.SQLException;
import java.util.List;

public class MachineService extends SessionUtil implements MachineDAO {

    public void add(Machine machine) throws SQLException {
        openTransactionSession();
        getSession().save(machine);
        closeTransaction();
    }

    public List<Machine> getAll() throws SQLException {
        openTransactionSession();
        Session session = getSession();
        Query query = session.createQuery("from Machine");
        List<Machine> list = query.list();
        closeTransaction();
        return list;
    }

    public Machine getById(int id) throws SQLException {
        openTransactionSession();
        Machine machine = (Machine) getSession().createQuery("from Machine where id =" + id).uniqueResult();
        closeTransaction();
        return machine;
    }

    public void update(Machine machine) throws SQLException {
        openTransactionSession();
        getSession().update(machine);
        closeTransaction();
    }

    public void remove(Machine machine) throws SQLException {
        openTransactionSession();
        getSession().remove(machine);
        closeTransaction();
    }


}
