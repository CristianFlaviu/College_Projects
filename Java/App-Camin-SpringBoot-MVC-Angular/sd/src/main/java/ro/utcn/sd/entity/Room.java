package ro.utcn.sd.entity;


import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import org.springframework.data.repository.cdi.Eager;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;


@Entity
public class Room {

    @Id
    private String id;

    @Column
    private String roomNumber;

    @JsonIgnore
    @OneToMany(mappedBy = "room",fetch = FetchType.EAGER)
    private Set<User> users;

    @JsonIgnore
    @OneToMany(mappedBy = "room")
    private Set<Report> reports;

    @JsonIgnore
    @OneToMany(mappedBy = "room")
    private Set<Appointment> appointments;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getRoomNumber() {
        return roomNumber;
    }

    public void setRoomNumber(String roomNumber) {
        this.roomNumber = roomNumber;
    }

    public Set<User> getUsers() {
        return users;
    }

    public void setUsers(Set<User> users) {
        this.users = users;
    }

    public Set<Report> getReports() {
        return reports;
    }

    public void setReports(Set<Report> reports) {
        this.reports = reports;
    }

    public Set<Appointment> getAppointments() {
        return appointments;
    }

    public void setAppointments(Set<Appointment> appointments) {
        this.appointments = appointments;
    }

    public Room()
    {
        this.id= UUID.randomUUID().toString();

        this.roomNumber="419";
        this.users=new HashSet<>();
        this.reports=new HashSet<>();
        this.appointments=new HashSet<>();

    }



}
