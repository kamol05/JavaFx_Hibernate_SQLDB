package pet.dao;

import pet.entity.Client;

import java.sql.SQLException;
import java.util.List;

public interface ClientDAO {
    void add (Client client) throws SQLException;
    List<Client> getAll() throws SQLException;
    Client getById(int id) throws SQLException;
    void update(Client client) throws SQLException;
    void remove(Client client) throws SQLException;
}
