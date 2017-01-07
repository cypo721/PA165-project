/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dto;

import java.util.Date;
import java.util.Objects;

/**
 *
 * @author eduard
 */
public class RevisionCreateDTO {
    private Long id;

    private String dateOfRevision;

    private String userId;

    private String machineId;

    private String info;

    private boolean isFunctionable;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDateOfRevision() {
        return dateOfRevision;
    }

    public void setDateOfRevision(String dateOfRevision) {
        this.dateOfRevision = dateOfRevision;
    }

    public String getUser() {
        return userId;
    }

    public void setUser(String user) {
        this.userId = user;
    }

    public String getMachine() {
        return machineId;
    }

    public void setMachine(String machine) {
        this.machineId = machine;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public boolean isFunctionable() {
        return isFunctionable;
    }

    public void setFunctionable(boolean functionable) {
        isFunctionable = functionable;
    }

    @Override
    public String toString() {
        return "RevisionDTO{" +
                "id=" + id +
                ", dateOfRevision=" + dateOfRevision +
                ", user=" + userId +
                ", machine=" + machineId +
                ", info='" + info + '\'' +
                ", isFunctionable=" + isFunctionable +
                '}';
    }
    
    @Override
    public int hashCode() {
        final int prime = 13;
        int result = 1;
        result = prime * result + ((dateOfRevision == null) ? 0 : dateOfRevision.hashCode());
        result = prime * result + ((userId == null) ? 0 : userId.hashCode());
        result = prime * result + ((machineId == null) ? 0 : machineId.hashCode());
        result = prime * result + ((info == null) ? 0 : info.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final RevisionCreateDTO other = (RevisionCreateDTO) obj;
        if (!Objects.equals(this.dateOfRevision, other.dateOfRevision)) {
            return false;
        }
        if (!Objects.equals(this.userId, other.userId)) {
            return false;
        }
        if (!Objects.equals(this.machineId, other.machineId)) {
            return false;
        }
        if (!Objects.equals(this.info, other.info)) {
            return false;
        }
        return true;
    }
}
