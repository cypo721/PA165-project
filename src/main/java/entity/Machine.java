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
@Table(name="Machines")
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
        result = prime * result + ((dateOfBuy == null) ? 0 : dateOfBuy.hashCode());
        result = prime * result + ((pricePerDay == null) ? 0 : pricePerDay.hashCode());
        result = prime * result + ((machineType == null) ? 0 : machineType.hashCode());

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
        Machine machine = (Machine) obj;
        if (name != null ? !name.equals(machine.name) : machine.name != null) return false;
        if (dateOfLastRevision != null ? !dateOfLastRevision.equals(machine.dateOfLastRevision) : machine.dateOfLastRevision != null) return false;
        if (dateOfBuy != null ? !dateOfBuy.equals(machine.dateOfBuy) : machine.dateOfBuy != null) return false;
        if (pricePerDay != null ? !pricePerDay.equals(machine.pricePerDay) : machine.pricePerDay != null) return false;
        if (machineType != null ? !machineType.equals(machine.machineType) : machine.machineType != null) return false;

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
    
    

