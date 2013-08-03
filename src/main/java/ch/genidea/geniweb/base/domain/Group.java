package ch.genidea.geniweb.base.domain;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Set;

@Entity(name="group")
public class Group implements Serializable {
    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String name;

    @OneToMany
    private Set<GroupParticipant> participants;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<GroupParticipant> getParticipants() {
        return participants;
    }

    public void setParticipants(Set<GroupParticipant> participants) {
        this.participants = participants;
    }
}
