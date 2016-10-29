/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.Date;
import java.util.Collections;
import java.util.Objects;

/**
 *
 * @author eduard
 */
public class Machine {

    @Id
    private Long id; 
    
    @Column(nullable = false)
    private String name;
    
    @Column(nullable = false)
    private Date lastRevision;

    @Column(nullable = false)
    private String lastRevisionInfo;
    
    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Date getLastRevision() {
        return lastRevision;
    }
    
    public String getLastRevisionInfo() {
        return lastRevisionInfo;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setLastRevision(Date lastRevision) {
        this.lastRevision = lastRevision;
    }
    
    public void setLastRevisionInfo(String lastRevisionInfo) {
        this.lastRevisionInfo = lastRevisionInfo;
    }

    @Override
    public int hashCode() {
        final int prime = 45;
        int result = 1;
        result = prime * result + ((name == null) ? 0 : name.hashCode());
        result = prime * result + ((lastRevision == null) ? 0 : lastRevision.hashCode());
        result = prime * result + ((lastRevisionInfo == null) ? 0 : lastRevisionInfo.hashCode());
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
        if (!(obj instanceof Machine)) {
            return false;
        }
        Machine other = (Machine) obj;
        if (name == null) {
            if (other.name != null) {
                return false;
            }
        } else if (!name.equals(other.name)) {
            return false;
        }
        if (lastRevision == null) {
            if (other.lastRevision != null) {
                return false;
            }
        } else if (!lastRevision.equals(other.lastRevision)) {
            return false;
        }
        if (lastRevisionInfo == null) {
            if (other.lastRevisionInfo != null) {
                return false;
            }
        } else if (!lastRevisionInfo.equals(other.lastRevisionInfo)) {
            return false;
        }
        return true;
    }
}
    
    

