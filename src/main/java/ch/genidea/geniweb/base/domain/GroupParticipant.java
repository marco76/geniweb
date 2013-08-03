package ch.genidea.geniweb.base.domain;


import ch.genidea.geniweb.base.utility.ParticipantRole;
import java.io.Serializable;

import javax.persistence.*;

@Entity(name = "groupParticipant")
public class GroupParticipant implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne
    private Group group;
    @ManyToOne
    private User user;
    private ParticipantRole participantRole;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Group getGroup() {
        return group;
    }

    public void setGroup(Group group) {
        this.group = group;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public ParticipantRole getParticipantRole() {
        return participantRole;
    }

    public void setParticipantRole(ParticipantRole participantRole) {
        this.participantRole = participantRole;
    }
}
