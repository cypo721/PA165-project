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
public class RentalDTO {
    
    private Long id;
    private Date dateFrom;
    private Date dateTo;
    private Integer price;
    private UserDTO user;
    private MachineDTO machine;

    public Long getId() {
        return id;
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

    public void setId(Long id) {
        this.id = id;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null) return false;
        if (!(o instanceof RentalDTO))
            return false;
        RentalDTO rental = (RentalDTO) o;

        if (dateFrom != null ? !dateFrom.equals(rental.dateFrom) : rental.dateFrom != null) return false;
        if (dateTo != null ? !dateTo.equals(rental.dateTo) : rental.dateTo != null) return false;
        if (price != null ? !price.equals(rental.price) : rental.price != null) return false;
        if (user != null ? !user.equals(rental.user) : rental.user != null) return false;
        return machine != null ? machine.equals(rental.machine) : rental.machine == null;

    }

    @Override
    public int hashCode() {
        int result = dateFrom != null ? dateFrom.hashCode() : 0;
        result = 31 * result + (dateTo != null ? dateTo.hashCode() : 0);
        result = 31 * result + (price != null ? price.hashCode() : 0);
        result = 31 * result + (user != null ? user.hashCode() : 0);
        result = 31 * result + (machine != null ? machine.hashCode() : 0);
        return result;
    }
    

    @Override
    public String toString() {
        return "RentalDTO{" +
                "id=" + id +
                ", dateFrom=" + dateFrom +
                ", dateTo=" + dateTo +
                ", price=" + price +
                ", user=" + user +
                ", machine=" + machine +
                '}';
    }
    
    @Override
    public int hashCode() {
        int result = dateFrom != null ? dateFrom.hashCode() : 0;
        result = 31 * result + (dateTo != null ? dateTo.hashCode() : 0);
        result = 31 * result + (price != null ? price.hashCode() : 0);
        result = 31 * result + (user != null ? user.hashCode() : 0);
        result = 31 * result + (machine != null ? machine.hashCode() : 0);
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
        final RentalDTO other = (RentalDTO) obj;
        if (!Objects.equals(this.dateFrom, other.dateFrom)) {
            return false;
        }
        if (!Objects.equals(this.dateTo, other.dateTo)) {
            return false;
        }
        if (!Objects.equals(this.price, other.price)) {
            return false;
        }
        if (!Objects.equals(this.user, other.user)) {
            return false;
        }
        if (!Objects.equals(this.machine, other.machine)) {
            return false;
        }
        return true;
    }
}
