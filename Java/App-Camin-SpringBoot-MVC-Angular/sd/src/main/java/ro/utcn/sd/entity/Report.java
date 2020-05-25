package ro.utcn.sd.entity;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Date;
import java.util.UUID;

@Entity
public class Report {

    @Id
    private String id;

    @ManyToOne
    @JoinColumn(name = "room_id")
    private Room room;

    @Column
    @NotNull
    private Date date;

    @Column
    @NotNull
    @Size(min=2 )
    private String title;

    @Column
    @NotNull
    @Size(min=10)
    private String description;

    public Report()
    {
        this.id= UUID.randomUUID().toString();
        this.room=new Room();
        this.date=new Date();
        this.title="Report Default-Title";
        this.description="default description";

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

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
