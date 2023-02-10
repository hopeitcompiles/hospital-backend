package com.utpl.hospital.service;

import java.io.Serializable;
import java.util.List;


public interface IGenericService<T, ID extends Serializable>{
	public T save(T entity);
	public void delete(ID id);
	public T findById(ID id);
	public List<T> getAll();
}
