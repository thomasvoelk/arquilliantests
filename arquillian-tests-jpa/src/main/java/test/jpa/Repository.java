package test.jpa;

import java.util.*;

public interface Repository<E, ID> {

    E find(ID entityId);

    List<E> findAll();

    void save(E entity);

    void delete(E entity);

}