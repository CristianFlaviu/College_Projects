package entity;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;
@Entity
@Table(name = "house")
public class House {

    @Id
    private String id;

    @Column
    private String adress;

    @OneToMany(mappedBy = "house", fetch = FetchType.EAGER)
    private Set<Request> requestSet;

    @ManyToOne
    @JoinColumn(name="user_id")
    private User user;

    public House()
    {
        this.id=UUID.randomUUID().toString();
        this.adress="Lucacesti";
        this.user=new User();
        this.requestSet=new HashSet<>();
    }
    public House(String id,String adress,User user)
    {
        this.id=id;
        this.adress=adress;
        this.user=user;
        this.requestSet=new HashSet<>();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getAdress() {
        return adress;
    }

    public void setAdress(String adress) {
        this.adress = adress;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Set<Request> getRequestSet() {
        return requestSet;
    }

    public void setRequestSet(Set<Request> requestSet) {
        this.requestSet = requestSet;
    }
}
