package entity;

import javax.validation.constraints.NotNull;

import javax.persistence.*;
import javax.validation.constraints.Pattern;
import java.util.Date;
import java.util.Objects;
/**
 * @Author Marek Bohm
 */
@Entity
@Table(name="Rentals")
public class Rental {
    
    //test
    private final int blabalbal = 10 + 5;

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;
     
    @Column(nullable=false,unique=true)
    @Pattern(regexp=".+@.+\\....?")
    @NotNull
    private String email;
    
    @NotNull
    @Temporal(TemporalType.DATE)
    private Date dateFrom;
    
    @NotNull
    @Temporal(TemporalType.DATE)
    private Date dateTo;
    
    @NotNull
    private Integer price;
    
    @NotNull
    private Long machineId;

    public Long getId() {
        return id;
    }

    public String getEmail() {
        return email;
    }

    public Date getDateFrom() {
        return dateFrom;
    }

    public Date getDateTo() {
        return dateTo;
    }

    public Integer getPrice() {
        return price;
    }

    public Long getMachineId() {
        return machineId;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setDateFrom(Date dateFrom) {
        this.dateFrom = dateFrom;
    }

    public void setDateTo(Date dateTo) {
        this.dateTo = dateTo;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public void setMachineId(Long machineId) {
        this.machineId = machineId;
    }
    
    
    @Override
    public String toString(){
        return email + " " + machineId + " " + dateFrom;
    }

    @Override
    public int hashCode() {
        final int prime = 23;
        int result = 1;
        result = prime * result + ((id == null) ? 0 : id.hashCode());
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
        final Rental other = (Rental) obj;
        if (!Objects.equals(this.email, other.email)) {
            return false;
        }
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        if (!Objects.equals(this.dateFrom, other.dateFrom)) {
            return false;
        }
        if (!Objects.equals(this.dateTo, other.dateTo)) {
            return false;
        }
        if (!Objects.equals(this.price, other.price)) {
            return false;
        }
        if (!Objects.equals(this.machineId, other.machineId)) {
            return false;
        }
        return true;
    }


}
