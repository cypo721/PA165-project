/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import enums.MachineType;
import java.math.BigDecimal;
import javax.persistence.*;
import java.util.Date;
import java.util.Objects;
import javax.validation.constraints.NotNull;

/**
 *
 * @author eduard
 */
@Entity
public class Machine {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id; 
    
    @Column(nullable = false)
    private String name;
    
    @NotNull
    @Temporal(TemporalType.DATE)
    private Date dateOfBuy;
    
    @Temporal(TemporalType.DATE)
    private Date dateOfLastRevision;

    @Column(nullable = false)
    private BigDecimal pricePerDay;
    
    @Enumerated(EnumType.STRING)
    private MachineType machineType;
    
    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Date getDateOfLastRevision() {
        return dateOfLastRevision;
    }

    public Date getDateOfBuy() {
        return dateOfBuy;
    }

    public BigDecimal getPricePerDay() {
        return pricePerDay;
    }

    public MachineType getMachineType() {
        return machineType;
    }
    
    public void setId(Long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDateOfLastRevision(Date dateOfLastRevision) {
        this.dateOfLastRevision = dateOfLastRevision;
    }

    public void setDateOfBuy(Date dateOfBuy) {
        this.dateOfBuy = dateOfBuy;
    }

    public void setPricePerDay(BigDecimal pricePerDay) {
        this.pricePerDay = pricePerDay;
    }

    public void setMachineType(MachineType machineType) {
        this.machineType = machineType;
    }

    
    
    @Override
    public int hashCode() {
        final int prime = 45;
        int result = 1;
        result = prime * result + ((name == null) ? 0 : name.hashCode());
        result = prime * result + ((dateOfLastRevision == null) ? 0 : dateOfLastRevision.hashCode());
//        result = prime * result + ((lastRevisionInfo == null) ? 0 : lastRevisionInfo.hashCode());
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
        final Machine other = (Machine) obj;
        if (!Objects.equals(this.name, other.name)) {
            return false;
        }
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        if (!Objects.equals(this.dateOfLastRevision, other.dateOfLastRevision)) {
            return false;
        }
        if (!Objects.equals(this.dateOfBuy, other.dateOfBuy)) {
            return false;
        }
        if (!Objects.equals(this.pricePerDay, other.pricePerDay)) {
            return false;
        }
        if (this.machineType != other.machineType) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Machine {" +
                "name='" + name + '\'' +
                ", dateOfBuy='" + dateOfBuy + '\'' +
                ", dateOfLastRevision='" + dateOfLastRevision + '\'' +
                ", pricePerDay='" + pricePerDay + '\'' +
                ", machienType=" + machineType +
                '}';
    }
}
    
    

