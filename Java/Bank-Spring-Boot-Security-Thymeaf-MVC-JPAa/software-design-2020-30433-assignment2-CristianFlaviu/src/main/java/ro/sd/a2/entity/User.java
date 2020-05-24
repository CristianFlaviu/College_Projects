package ro.sd.a2.entity;

import org.springframework.security.crypto.bcrypt.BCrypt;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDate;
import java.time.Month;
import java.time.Period;
import java.util.HashSet;
import java.util.Set;

@Entity
public class User {

    @Id
    private String id;

    @Column
    @NotNull
    @Size(min=2, max=30)
    private String firstName;

    @Column
    @NotNull
    @Size(min=2, max=30)
    private String lastName;

    @OneToOne
    @JoinColumn(name = "address_id",unique = true)
    private Address address;


    @Column
    private LocalDate birthDate;

    @Column
    private int age;

    @Column
    private String resetPassCode;

    @Column
    @NotNull
    private String password;

    @Column
    @NotNull
    @Size(min=2, max=30)
    private String email;

    @Column
    @NotNull
    @Size(min=2, max=30)
    private String username;

    @Column
    private LocalDate registrationDate;

    @Column
    private boolean isAdmin;

    @OneToMany(mappedBy = "user",fetch = FetchType.EAGER)
    private Set<GeneralAccount> generalAccountSet;

    @OneToMany(mappedBy = "user",fetch = FetchType.EAGER)
    private Set<Factura> facturaSet;

    public User()
    {
        this.id="default-id";
        this.firstName="Flaviu";
        this.lastName="Cristian";

        this.birthDate=LocalDate.of(1999, Month.JANUARY,12);
        this.age=Period.between(this.birthDate,LocalDate.now()).getYears();
        this.password="yes";
        this.email="flaviu_remus@gmail.com";
        this.username="Flaviu Remus";
        this.registrationDate= LocalDate.now();
        this.isAdmin=false;
        this.generalAccountSet=new HashSet<>();
        this.facturaSet=new HashSet<>();
        this.resetPassCode="0000";

    }

    public String getResetPassCode() {
        return resetPassCode;
    }

    public void setResetPassCode(String resetPassCode) {
        this.resetPassCode = resetPassCode;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public boolean isAdmin() {
        return isAdmin;
    }

    public void setAdmin(boolean admin) {
        isAdmin = admin;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public Set<GeneralAccount> getGeneralAccountSet() {
        return generalAccountSet;
    }

    public void setGeneralAccountSet(Set<GeneralAccount> generalAccountSet) {
        this.generalAccountSet = generalAccountSet;
    }

    public Set<Factura> getFacturaSet() {
        return facturaSet;
    }

    public void setFacturaSet(Set<Factura> facturaSet) {
        this.facturaSet = facturaSet;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }



    public LocalDate getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
    }

    public void setRegistrationDate(LocalDate registrationDate) {
        this.registrationDate = registrationDate;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public LocalDate getRegistrationDate() {
        return registrationDate;
    }

    public String hashPassword(String plainTextPassword){
        return BCrypt.hashpw(plainTextPassword, BCrypt.gensalt());
    }
    public void checkPass(String plainPassword, String hashedPassword) {
        if (BCrypt.checkpw(plainPassword, hashedPassword))
            System.out.println("The password matches.");
        else
            System.out.println("The password does not match.");
    }


    @Override
    public String toString() {
        return "User{" +
                "id='" + id + '\'' +
                ", name='" + firstName + '\'' +
                '}';
    }
}
