package quack.dao;

import java.util.List;

public interface IDAO <T, K>{

	public T findById(K id);
	public List<T> findAll();
	public T save(T obj);
	public void delete(T obj);	
	public void deleteById(K id);
}

