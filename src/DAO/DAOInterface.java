package DAO;

import java.util.ArrayList;

public interface DAOInterface<T> {
	
	public boolean insert(T t);
	
	public int update(T t);
	
	public int delete(T t);
	
	public ArrayList<T> selectAll();
	
	public ArrayList<T> selectByCondition(String condition);
	
}
