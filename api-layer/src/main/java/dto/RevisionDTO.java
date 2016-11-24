package dto;

import java.util.Date;

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
}
