package mavenCourseWork.Dao;

import java.util.List;

public interface Dao<T,PK> {
    void add(T entity);
    T getByPK(PK pk);
    List<T> getAll();
    void update(T entity);
    void deleteByPK(PK pk);
    }

