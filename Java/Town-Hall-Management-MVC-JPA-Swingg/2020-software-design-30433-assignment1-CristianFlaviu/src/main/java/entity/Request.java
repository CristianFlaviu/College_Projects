package entity;

import Utils.RequestState;

import javax.persistence.*;


import java.util.Date;
import java.util.UUID;


@Entity
@Table(name = "Request")
public class Request {

    @Id
    private String id;

    @Column
    private Date date;

    @Column
    private String title;

    @ManyToOne
    @JoinColumn(name="house_id")
    private  House house;

    @Column
    private RequestState requestState;

    @ManyToOne
    @JoinColumn(name="document_id")
    private Document document;

    @ManyToOne
    @JoinColumn(name="user_id")
    private User user;

    public Request()
    {
        this.date= new Date();
        this.requestState=RequestState.NotChecked;
        this.title="default-title";
        this.id= UUID.randomUUID().toString();
        this.document=new Document();
        this.user=new User();
        this.house=new House();
    }
    public Request(String title,User user,Document document,House house)
    {

        this.date= new Date();
        this.requestState=RequestState.NotChecked;
        this.title=title;
        this.house=house;
        this.id=UUID.randomUUID().toString();
        this.document=document;
        this.user=user;

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

    public RequestState getRequestState() {
        return requestState;
    }

    public void setRequestState(RequestState requestState) {
        this.requestState = requestState;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Document getDocument() {
        return document;
    }

    public void setDocument(Document document) {
        this.document = document;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public House getHouse() {
        return house;
    }

    public void setHouse(House house) {
        this.house = house;
    }
}
