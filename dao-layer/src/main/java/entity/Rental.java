package entity;

import javax.validation.constraints.NotNull;

import javax.persistence.*;
import java.util.Date;

/**
 * @Author Marek Bohm
 */
@Entity
@Table(name="Rentals")
public class Rental {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Temporal(TemporalType.DATE)
    private Date dateFrom;

    @Temporal(TemporalType.DATE)
    private Date dateTo;
    
    private Integer price;

    @ManyToOne(optional=false)
    @NotNull
    private User user;

    @ManyToOne(optional=false)
    @NotNull
    private Machine machine;

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

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Machine getMachine() {
        return machine;
    }

    public void setMachine(Machine machine) {
        this.machine = machine;
    }

    @Override
    public String toString() {
        return "Rental{" +
                "id=" + id +
                ", dateFrom=" + dateFrom +
                ", dateTo=" + dateTo +
                ", price=" + price +
                ", user=" + user +
                ", machine=" + machine +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null) return false;
        if (!(o instanceof Rental))
            return false;
        Rental rental = (Rental) o;

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
}
