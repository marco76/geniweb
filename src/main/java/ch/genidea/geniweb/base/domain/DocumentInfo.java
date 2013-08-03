package ch.genidea.geniweb.base.domain;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity(name="documentInfo")
public class DocumentInfo implements Serializable {
    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String originalName;
    private String type;
    private String fileName;
    private String title;
    private String description;
    @ManyToOne
    private User owner;

    @Temporal(javax.persistence.TemporalType.DATE)
    private Date dateCreated;
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date dateUpdated;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getOriginalName() {
        return originalName;
    }

    public void setOriginalName(String originalName) {
        this.originalName = originalName;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public User getOwner() {
        return owner;
    }

    public void setOwner(User owner) {
        this.owner = owner;
    }


    public Date getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(Date dateCreated) {
        this.dateCreated = dateCreated;
    }

    public Date getDateUpdated() {
        return dateUpdated;
    }

    public void setDateUpdated(Date dateUpdated) {
        this.dateUpdated = dateUpdated;
    }

    @PrePersist
    protected void onCreate(){
        dateCreated = new Date();
    }

    @PreUpdate
    protected void onUpdate(){
        dateUpdated = new Date();
    }
}
