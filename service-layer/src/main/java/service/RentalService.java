/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import entity.Rental;
import java.util.List;

/**
 *
 * @author eduard
 */
public interface RentalService {
    public Rental create(Rental rental);
    public Rental update(Rental rental);
    public Rental findRentalById(Long id);
    public List<Rental> findAllRentals();
}
