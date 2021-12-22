package pet.dao;

import pet.entity.Quality;

import java.sql.SQLException;
import java.util.List;

public interface QualityDAO {
    void add (Quality quality) throws SQLException;
    List<Quality> getAll() throws SQLException;
    Quality getById(int id) throws SQLException;
    void update(Quality quality) throws SQLException;
    void remove(Quality quality) throws SQLException;
}
