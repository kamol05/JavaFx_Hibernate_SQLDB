package pet.dao;

import pet.entity.DailyProduction;

import java.sql.SQLException;
import java.util.List;

public interface DailyProductionDao {
    void add (DailyProduction dailyProduction) throws SQLException;
    List<DailyProduction> getAll() throws SQLException;
    DailyProduction getById(int id) throws SQLException;
    void update(DailyProduction dailyProduction) throws SQLException;
    void remove(DailyProduction dailyProduction) throws SQLException;
}
