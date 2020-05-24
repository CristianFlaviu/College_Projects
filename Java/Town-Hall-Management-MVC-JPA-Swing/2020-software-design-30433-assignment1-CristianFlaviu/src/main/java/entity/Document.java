package entity;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "documents")
public class Document {

    @Id
    private String id;

    @Column
    private String nume;

    @Column
    private String type;

    @OneToMany(mappedBy = "document")
    private Set<Request> requestSet;

    public Document()
    {
        id="default-id";
        nume="default-nume";
        type="default-type";
    }
    public Document(String id,String nume,String type)
    {
        this.id=id;
        this.nume=nume;
        this.type=type;
    }

    public String getId() {
        return id;
    }

    public Set<Request> getRequestSet() {
        return requestSet;
    }

    public void setRequestSet(Set<Request> requestSet) {
        this.requestSet = requestSet;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNume() {
        return nume;
    }

    public void setNume(String nume) {
        this.nume = nume;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
