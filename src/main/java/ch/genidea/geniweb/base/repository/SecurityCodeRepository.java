package ch.genidea.geniweb.base.repository;

import ch.genidea.geniweb.base.domain.SecurityCode;

public interface SecurityCodeRepository {
    void persist(SecurityCode securityCode);

    void deleteSecurityCode(SecurityCode securityCode);
}
