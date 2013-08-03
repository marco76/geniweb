package ch.genidea.geniweb.base.repository;

import ch.genidea.geniweb.base.domain.DocumentInfo;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Repository
public class DocumentInfoRepositoryImpl  implements DocumentInfoRepository{

    @PersistenceContext
    EntityManager entityManager;

    @Override
    @Transactional
    public void persist(DocumentInfo documentInfo){
        entityManager.persist(documentInfo);
  }
}
