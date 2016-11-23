/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dto;

import java.util.Date;

/**
 *
 * @author eduard
 */
public class RentalDTO {
    
    private Long id;
    private Date dateFrom;
    private Date dateTo;
    private Integer price;
  //  private UserDTO user;
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

//    public UserDTO getUser() {
//        return user;
//    }
//
//    public void setUser(UserDTO user) {
//        this.user = user;
//    }

    public MachineDTO getMachine() {
        return machine;
    }

    public void setMachine(MachineDTO machine) {
        this.machine = machine;
    }

    @Override
    public String toString() {
        return "RentalDTO{" +
                ", id=" + id +
                ", dateFrom=" + dateFrom +
                ", dateTo=" + dateTo +
                ", price=" + price +
              //  ", user=" + user +
                ", machine=" + machine +
                '}';
    }
}
