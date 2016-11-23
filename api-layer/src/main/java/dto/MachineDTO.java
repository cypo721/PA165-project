package dto;

import enums.MachineType;

import java.math.BigDecimal;
import java.util.Date;

/**
 * Created by pato on 22.11.2016.
 */
public class MachineDTO {

    private Long id;

    private String name;

    private Date dateOfBuy;

    private Date dateOfLastRevision;

    private BigDecimal pricePerDay;

    private MachineType machineType;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getDateOfBuy() {
        return dateOfBuy;
    }

    public void setDateOfBuy(Date dateOfBuy) {
        this.dateOfBuy = dateOfBuy;
    }

    public Date getDateOfLastRevision() {
        return dateOfLastRevision;
    }

    public void setDateOfLastRevision(Date dateOfLastRevision) {
        this.dateOfLastRevision = dateOfLastRevision;
    }

    public BigDecimal getPricePerDay() {
        return pricePerDay;
    }

    public void setPricePerDay(BigDecimal pricePerDay) {
        this.pricePerDay = pricePerDay;
    }

    public MachineType getMachineType() {
        return machineType;
    }

    public void setMachineType(MachineType machineType) {
        this.machineType = machineType;
    }

    @Override
    public String toString() {
        return "MachineDTO{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", dateOfBuy=" + dateOfBuy +
                ", dateOfLastRevision=" + dateOfLastRevision +
                ", pricePerDay=" + pricePerDay +
                ", machineType=" + machineType +
                '}';
    }
}
