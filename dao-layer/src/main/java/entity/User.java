package entity;

import enums.PersonType;
import enums.Role;

import javax.persistence.Entity;
import javax.validation.constraints.NotNull;

import javax.persistence.*;
import javax.validation.constraints.Pattern;
import java.util.Date;

/**
 * Created by pato on 26.10.2016.
 *
 * Class used for saving informations about customers and employees.
 */
@Entity
//In Derby, its forbiden to 'USER' is reserved keyword, we need to rename table
@Table(name="Users")
public class User {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;

    private String passwordHash;

    private String givenName;
    @NotNull
    private String surname;

    @Column(nullable=false, unique=true)
    @Pattern(regexp=".+@.+\\....?")
    private String email;
    @NotNull

    @Pattern(regexp="\\+?\\d+")
    private String phone;

    /*
     * decides if user is legal or natural person
     */
    @Enumerated(EnumType.STRING)
    private PersonType personType;

    /*
     * decides if user is employee or customer
     */
    @Enumerated(EnumType.STRING)
    private Role role;

    @NotNull
    @Temporal(TemporalType.DATE)
    private Date joinedDate;

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
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (!(obj instanceof User))
            return false;
        User other = (User) obj;
        if (email == null) {
            if (other.getEmail() != null)
                return false;
        } else if (!email.equals(other.getEmail()))
            return false;
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
