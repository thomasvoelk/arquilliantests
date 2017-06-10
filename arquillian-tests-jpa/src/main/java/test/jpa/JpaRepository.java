package test.jpa;

import javax.persistence.*;
import java.lang.reflect.*;
import java.util.*;

import static java.util.Objects.*;

public abstract class JpaRepository<E, ID> implements Repository<E, ID> {

    private EntityManager entityManager;

    private Class<E> entityClass;

    public JpaRepository() {
        this.entityClass = ((Class<E>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0]);
    }

    @Override
    public E find(ID id) {
        requireNonNull(id);

        E entity = entityManager.find(entityClass, id, LockModeType.OPTIMISTIC);

        if (entity == null) {
            throw new RuntimeException(String.format("Not found %s %s", entityClass, id));
        }

        return entity;
    }

    @Override
    public List<E> findAll() {
        return entityManager.createQuery("Select e from " + getEntityClass().getSimpleName() + " e", getEntityClass()).getResultList();

        /*Criteria API Version*/
//        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
//        CriteriaQuery<E> cq = cb.createQuery(getEntityClass());
//        Root<E> rootEntry = cq.from(getEntityClass());
//        CriteriaQuery<E> all = cq.select(rootEntry);
//        TypedQuery<E> allQuery = entityManager.createQuery(all);
//        return allQuery.getResultList();
    }

    @Override
    public void save(E entity) {
        requireNonNull(entity);

        if (entityManager.contains(entity)) {
            entityManager.lock(entity, LockModeType.OPTIMISTIC_FORCE_INCREMENT);
        } else {
            entityManager.persist(entity);
        }

        entityManager.flush();
    }

    @Override
    public void delete(E entity) {
        requireNonNull(entity);

        entityManager.remove(entity);
        entityManager.flush();
    }

    protected Class<E> getEntityClass() {
        return entityClass;
    }

    protected EntityManager getEntityManager() {
        return entityManager;
    }

    @PersistenceContext
    public void setEntityManager(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

}
