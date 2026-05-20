package quack.dao;

import java.util.List;

public interface IDAO <T, K>{

	T findById(int id);
	List<T> findAll();
	boolean save(T t);
	boolean update(T t);
	boolean delete(int id);
}

