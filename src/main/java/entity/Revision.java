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
    private Date date;
    
    @NotNull
    private Long machineId;
    
    public Long getId() {
        return id;
    }
    
    public Date getDate() {
        return date;
    }
    
    public Long getMachineId() {
        return machineId;
    }
    
    @Override
    public String toString(){
        return "Revision " + id + " of " + machineId + " on " + date;
    }
    
    @Override
    public int hashCode() {
        final int prime = 13;
        int result = 1;
        result = prime * result + ((date == null) ? 0 : date.hashCode());
        result = prime * result + ((machineId == 0) ? 0 : machineId.hashCode());
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
        
        if (!Objects.equals(this.machineId, other.machineId)) {
            return false;
        }
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        if (!Objects.equals(this.date, other.date)) {
            return false;
        }
        
        return true;
    }
}
