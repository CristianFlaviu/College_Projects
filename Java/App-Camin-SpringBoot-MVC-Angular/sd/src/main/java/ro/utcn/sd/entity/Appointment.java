package ro.utcn.sd.entity;

import lombok.Data;
import ro.utcn.sd.dto.AppointmentDto;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.UUID;

@Entity

public class Appointment {


    @Id
    private String id;

    @ManyToOne
    @JoinColumn(name="room_id")
    private Room room;

    @Column
    @NotNull
    private String type;

    @Column
    @NotNull
    private String startHour;

    @Column
    @NotNull
    private String day;

    public Appointment()
    {
        this.id= UUID.randomUUID().toString();

        this.room=new Room();
        this.type="football";
        this.startHour ="12";
        this.day ="Friday";
    }
    public Appointment(Room room)
    {
        this.id= UUID.randomUUID().toString();
        this.room=room;
    }

    public Appointment update(AppointmentDto appointmentDto)
    {
        this.startHour=appointmentDto.getStartHour();
        this.day=appointmentDto.getDay();

        return this;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Room getRoom() {
        return room;
    }

    public void setRoom(Room room) {
        this.room = room;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getStartHour() {
        return startHour;
    }

    public void setStartHour(String startHour) {
        this.startHour = startHour;
    }

    public String getDay() {
        return day;
    }

    public void setDay(String day) {
        this.day = day;
    }
}
