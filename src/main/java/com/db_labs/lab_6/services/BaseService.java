package com.db_labs.lab_6.services;

import java.util.List;

public interface BaseService <T, D, ID> {

    List<D> findAll();

    D findById(ID id);

    D create(D dto);

    D update(ID id, D dto);

    boolean deleteById(ID id);
}
