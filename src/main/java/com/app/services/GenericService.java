package com.app.services;

import java.util.List;

/**
 * Created by Marcin on 22.04.2016.
 */
public interface GenericService<T> {

    T save(T t);

    T findOne(Long id);

    List<T> findAll();

    void delete(T t);
}
