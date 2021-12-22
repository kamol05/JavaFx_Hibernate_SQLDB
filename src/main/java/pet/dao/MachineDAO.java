package pet.dao;

import pet.entity.Machine;

import java.sql.SQLException;
import java.util.List;

public interface MachineDAO {
    void add (Machine machine) throws SQLException;
    List<Machine> getAll() throws SQLException;
    Machine getById(int id) throws SQLException;
    void update(Machine machine) throws SQLException;
    void remove(Machine machine) throws SQLException;
}
