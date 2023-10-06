package algebra.hr.bll.service;

import java.util.List;

//This could come in handy later
public interface GenericService<T> {
    List<T> findAll();
    T findById(int id);
    T save(T obj);
    void deleteById(int id);
}
