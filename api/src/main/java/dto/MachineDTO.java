package dto;

import enums.MachineType;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.math.BigDecimal;
import java.util.Date;

/**
 * Created by pato on 22.11.2016.
 */
public class MachineDTO {

    private Long id;

    @NotNull
    @Size(min=1)
    private String name;

    @NotNull
    @DateTimeFormat(pattern ="yyyy-MM-dd")
    private Date dateOfBuy;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date dateOfLastRevision;

    @NotNull
    @Min(value = 0)
    private BigDecimal pricePerDay;
    @NotNull
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
        if (!(obj instanceof MachineDTO)) {
            return false;
        }
        MachineDTO machine = (MachineDTO) obj;
        if (name != null ? !name.equals(machine.name) : machine.name != null) return false;
        if (dateOfLastRevision != null ? !dateOfLastRevision.equals(machine.dateOfLastRevision) : machine.dateOfLastRevision != null)
            return false;
        if (dateOfBuy != null ? !dateOfBuy.equals(machine.dateOfBuy) : machine.dateOfBuy != null) return false;
        if (pricePerDay != null ? !pricePerDay.equals(machine.pricePerDay) : machine.pricePerDay != null) return false;
        if (machineType != null ? !machineType.equals(machine.machineType) : machine.machineType != null) return false;

        return true;
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
