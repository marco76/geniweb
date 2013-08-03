package ch.genidea.geniweb.base.repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

public class RoleRepositoryImpl implements RoleRepository {

    @PersistenceContext
    EntityManager entityManager;


}
