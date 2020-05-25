package ro.utcn.sd.entity;


import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import ro.utcn.sd.dto.UserDto;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.*;

@Entity
public class User implements UserDetails {

    @Id
    private String id;

    @ManyToOne
    @JoinColumn(name = "room_id")
    private Room room;

    @OneToMany(mappedBy = "user")
    private Set<Announcement> announcements;

    @Column
    @NotNull
    @Size(min = 2)
    private String firstName;

    @Column
    @NotNull
    @Size(min = 2)
    private String lastName;

    @Column(unique = true)
    @NotNull
    @Size(min = 3)
    private String username;

    @Column
    @NotNull
    @Size(min=3)
    private String password;

    @Column(unique = true)
    @NotNull
    @Size(min=5)
    private String email;

    @Column
    private Boolean isAdmin;

    @Column
    private String resetPassToken;

    public User()
    {
        this.id= UUID.randomUUID().toString();

        this.room=new Room();
        this.room.setId("419id");
        this.announcements=new HashSet<>();
        this.firstName="Flaviu";
        this.lastName="Cristian";
        this.password="rectilinie";
        this.username="flaviu";
        this.email="flaviu_remus@yahoo.com";
        this.isAdmin=false;


    }

    public User update(UserDto userDto)
    {

        this.setFirstName(userDto.getFirstName());
        this.setLastName(userDto.getLastName());
        this.setEmail(userDto.getEmail());

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

    public Set<Announcement> getAnnouncements() {
        return announcements;
    }

    public void setAnnouncements(Set<Announcement> announcements) {
        this.announcements = announcements;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getUsername() {
        return username;
    }

    public String getResetPassToken() {
        return resetPassToken;
    }

    public void setResetPassToken(String resetPassToken) {
        this.resetPassToken = resetPassToken;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    public void setUsername(String username) {
        this.username = username;
    }


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {

        List<GrantedAuthority> authorities=new ArrayList<>();

        if(this.isAdmin)
            authorities.add(new SimpleGrantedAuthority("ADMIN"));
        else
            authorities.add(new SimpleGrantedAuthority("USER"));

        return authorities;
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

    public Boolean getAdmin() {
        return isAdmin;
    }

    public void setAdmin(Boolean admin) {
        isAdmin = admin;
    }
}
