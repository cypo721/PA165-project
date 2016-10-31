/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import java.util.Date;
import java.util.Objects;
import javax.persistence.*;
import javax.validation.constraints.NotNull;

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
        if (getClass() != obj.getClass()) {
            return false;
        }
        
        final Revision other = (Revision) obj;
        
        if (!Objects.equals(this.machine, other.machine)) {
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
