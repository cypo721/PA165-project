/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dto;

import enums.PersonType;
import enums.Role;
import java.util.Date;
import java.util.Objects;

/**
 *
 * @author Václav Zouzalík
 */
public class UserDTO {
    private Long id;

    private String passwordHash;

    private String givenName;
    
    private String surname;
    
    private String email;
    
    private String phone;
    
    private PersonType personType;
    
    private Role role;
    
    private Date joinedDate;

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public String getPasswordHash() {
        return passwordHash;
    }

    public void setPasswordHash(String passwordHash) {
        this.passwordHash = passwordHash;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }


    public String getGivenName() {
        return givenName;
    }


    public void setGivenName(String givenName) {
        this.givenName = givenName;
    }


    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Date getJoinedDate() {
        return joinedDate;
    }

    public void setJoinedDate(Date joinedDate) {
        this.joinedDate = joinedDate;
    }

    public PersonType getPersonType() {
        return personType;
    }

    public void setPersonType(PersonType personType) {
        this.personType = personType;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }
    
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((email == null) ? 0 : email.hashCode());
        result = prime * result + ((personType == null) ? 0 : personType.hashCode());
        result = prime * result + ((role == null) ? 0 : role.hashCode());
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
        final UserDTO other = (UserDTO) obj;
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        if (!Objects.equals(this.passwordHash, other.passwordHash)) {
            return false;
        }
        if (!Objects.equals(this.givenName, other.givenName)) {
            return false;
        }
        if (!Objects.equals(this.surname, other.surname)) {
            return false;
        }
        if (!Objects.equals(this.email, other.email)) {
            return false;
        }
        if (this.personType != other.personType) {
            return false;
        }
        if (this.role != other.role) {
            return false;
        }
        if (!Objects.equals(this.joinedDate, other.joinedDate)) {
            return false;
        }
        return true;
    }
    
    @Override
    public String toString() {
        return "User{" +
                "givenName='" + givenName + '\'' +
                ", surname='" + surname + '\'' +
                ", email='" + email + '\'' +
                ", phone='" + phone + '\'' +
                ", personType=" + personType +
                ", role=" + role +
                ", joinedDate=" + joinedDate +
                '}';
    }
}
