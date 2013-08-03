package ch.genidea.geniweb.base.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class AbstractDocumentHolder {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    protected Long Id;
}
