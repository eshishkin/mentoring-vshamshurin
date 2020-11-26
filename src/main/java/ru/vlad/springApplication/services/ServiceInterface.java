package ru.vlad.springApplication.services;

import java.util.List;

public interface ServiceInterface<E, K> {
    void create(E model);

    List<E> readAll();

    E read(K id);

    boolean update(E model, K id);

    boolean delete(K id);
}
