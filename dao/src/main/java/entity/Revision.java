/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.Objects;

/**
 *
 * @author Václav Zouzalík
 */
@Entity
@Table(name="Revisions")
public class Revision {
    
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;
    
    @NotNull
    @Temporal(TemporalType.DATE)
    private Date dateOfRevision;

    @ManyToOne(optional=false)
    @NotNull
    private User user;

    @ManyToOne(optional=false)
    @NotNull
    private Machine machine;
    
    private String info;
    
    private boolean isFunctionable;

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public Date getDateOfRevision() {
        return dateOfRevision;
    }

    public void setDateOfRevision(Date dateOfRevision) {
        this.dateOfRevision = dateOfRevision;
    }

    public User getUser() {
        return user;
    }
    
    public String getInfo()
    {
        return info;
    }
    
    public boolean getIsFunctionable()
    {
        return isFunctionable;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Machine getMachine() {
        return machine;
    }

    public void setMachine(Machine machine) {
        this.machine = machine;
    }
    
    public void setInfo(String info)
    {
        this.info = info;
    }
    
    public void setIsFunctionable(boolean isFunctionable)
    {
        this.isFunctionable = isFunctionable;
    }

    @Override
    public int hashCode() {
        final int prime = 13;
        int result = 1;
        result = prime * result + ((dateOfRevision == null) ? 0 : dateOfRevision.hashCode());
        result = prime * result + ((user == null) ? 0 : user.hashCode());
        result = prime * result + ((machine == null) ? 0 : machine.hashCode());
        result = prime * result + ((info == null) ? 0 : info.hashCode());
        return result;
    }
    
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (!(obj instanceof Revision)) {
            return false;
        }
        
        final Revision other = (Revision) obj;
        
        if(this.isFunctionable != other.isFunctionable) {
            return false;
        }
        if (!Objects.equals(this.machine, other.machine)) {
            return false;
        }
        if (!Objects.equals(this.user, other.user)) {
            return false;
        }
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        if (!Objects.equals(this.dateOfRevision, other.dateOfRevision)) {
            return false;
        }
        
        return true;
    }
}
