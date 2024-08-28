package model;

import java.sql.Date;

import javax.persistence.*;

@Entity
@Table(name = "profile")
public class Profile extends User {

    @Id
    private int id;

    @Column(name = "phone")
    private String phone;

    @Column(name = "date_of_birth")
    private Date dateOfBirth;

    @Column(name = "gender")
    private Gender gender;

    @OneToOne
    @JoinColumn(name = "id", referencedColumnName = "id", insertable = false, updatable = false)
    private User user;

    public Profile() {}

    public Profile(String username, String password, String email, String phone, Date dateOfBirth, Gender gender) {
        super(username, password, email);
        this.phone = phone;
        this.dateOfBirth = dateOfBirth;
        this.gender = gender;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Date getDate_of_birth() {
        return dateOfBirth;
    }

    public void setDate_of_birth(Date date_of_birth) {
        this.dateOfBirth = date_of_birth;
    }

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }
}
