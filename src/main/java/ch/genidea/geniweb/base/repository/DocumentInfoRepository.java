package ch.genidea.geniweb.base.repository;

import ch.genidea.geniweb.base.domain.DocumentInfo;

public interface DocumentInfoRepository {
    void persist(DocumentInfo documentInfo);
}
