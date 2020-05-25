package ro.utcn.sd.entity;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Date;
import java.util.UUID;

@Entity

public class Announcement {
    @Id
    private String id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private  User user;

    @Column
    @NotNull
    private Date date;

    @Column
    @NotNull
    @Size(min = 4)
    private String title;

    @Column
    @NotNull
    @Size(min =10)
    private String description;



    public Announcement()
    {
        this.id= UUID.randomUUID().toString();
        this.date=new Date();
        this.title="DefaultTitle-Announcement";
        this.description="long description-announcement";
        this.user=new User();

    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
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
