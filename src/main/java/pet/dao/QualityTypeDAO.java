package pet.dao;

import pet.entity.QualityType;

import java.sql.SQLException;
import java.util.List;

public interface QualityTypeDAO {
    void add (QualityType qualityType) throws SQLException;
    List<QualityType> getAll() throws SQLException;
    QualityType getById(int id) throws SQLException;
    void update(QualityType qualityType) throws SQLException;
    void remove(QualityType qualityType) throws SQLException;
}
