package ch.genidea.geniweb.base.domain;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Set;

@Entity(name = "message")
public class Message implements Serializable {
    @Id@GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(length = 100)
    private String title;
    @Column(length = 500)
    private String message;

    @ManyToOne
    private User sender;

    @OneToMany
    private Set<User> receiver;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public User getSender() {
        return sender;
    }

    public void setSender(User sender) {
        this.sender = sender;
    }

    public Set<User> getReceiver() {
        return receiver;
    }

    public void setReceiver(Set<User> receiver) {
        this.receiver = receiver;
    }
}
