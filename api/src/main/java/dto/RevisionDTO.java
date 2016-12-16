package dto;

import java.util.Date;
import java.util.Objects;

/**
 * Created by Marek Bohm on 23.11.2016.
 */
public class RevisionDTO {
    private Long id;

    private Date dateOfRevision;

    private UserDTO user;

    private MachineDTO machine;

    private String info;

    private boolean isFunctionable;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getDateOfRevision() {
        return dateOfRevision;
    }

    public void setDateOfRevision(Date dateOfRevision) {
        this.dateOfRevision = dateOfRevision;
    }

    public UserDTO getUser() {
        return user;
    }

    public void setUser(UserDTO user) {
        this.user = user;
    }

    public MachineDTO getMachine() {
        return machine;
    }

    public void setMachine(MachineDTO machine) {
        this.machine = machine;
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
                ", user=" + user +
                ", machine=" + machine +
                ", info='" + info + '\'' +
                ", isFunctionable=" + isFunctionable +
                '}';
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
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final RevisionDTO other = (RevisionDTO) obj;
        if (!Objects.equals(this.dateOfRevision, other.dateOfRevision)) {
            return false;
        }
        if (!Objects.equals(this.user, other.user)) {
            return false;
        }
        if (!Objects.equals(this.machine, other.machine)) {
            return false;
        }
        if (!Objects.equals(this.info, other.info)) {
            return false;
        }
        return true;
    }
}
