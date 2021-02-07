package com.kodilla.librarybackend.domain;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.*;

@Entity
public class Reader{

    @Id
    @GeneratedValue
    @NotNull
    @Column(name = "READER_ID" ,unique = true)
    private Long id;

    @GeneratedValue
    private String uuid;
    private String name;
    private String phoneNumber;
    private String emailAddress;
    private boolean status;
    private String password;
    private String roles = "";
    private String permissions = "";


    @OneToMany(targetEntity = Reservation.class,
            mappedBy = "reader",
            cascade = CascadeType.ALL,
            fetch = FetchType.LAZY)
    private List<Reservation> reservations = new ArrayList<>();

    @OneToMany(targetEntity = Volume.class,
            mappedBy = "reader",
            cascade = CascadeType.ALL,
            fetch = FetchType.LAZY)
    private List<Volume> volumeList = new ArrayList<>();

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "CART_ID")
    private Cart cart;

    public Reader() {
    }

    public Reader(@NotNull Long id, String uuid, String name, String phoneNumber, String emailAddress, boolean status, String password, List<Reservation> reservations, List<Volume> volumeList) {
        this.id = id;
        this.uuid = uuid;
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.emailAddress = emailAddress;
        this.status = status;
        this.password = password;
        this.reservations = reservations;
        this.volumeList = volumeList;
    }

    public Reader(Long id, String name, String phoneNumber, String emailAddress, boolean status, String password, String uuid) {
        this.id = id;
        this.uuid = uuid;
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.emailAddress = emailAddress;
        this.status = status;
        this.password = password;
    }

    public Reader(String name, String phoneNumber, String emailAddress, boolean status, String password, String roles, String permissions) {
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.emailAddress = emailAddress;
        this.status = true;
        this.password = password;
        this.roles = roles;
        this.permissions = permissions;
    }

    public Reader(String name, String phoneNumber, String emailAddress, boolean status) {
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.emailAddress = emailAddress;
        this.status = status;
    }

    public Reader(String name, boolean status) {
        this.name = name;
        this.status = status;
    }

    public Reader(String emailAddress, String password) {
        this.emailAddress = emailAddress;
        this.password = password;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }


    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRoles() {
        return roles;
    }

    public void setRoles(String roles) {
        this.roles = roles;
    }

    public String getPermissions() {
        return permissions;
    }

    public void setPermissions(String permissions) {
        this.permissions = permissions;
    }

    public List<Reservation> getReservations() {
        return reservations;
    }

    public void setReservations(List<Reservation> reservations) {
        this.reservations = reservations;
    }

    public List<Volume> getBookList() {
        return volumeList;
    }

    public void setBookList(List<Volume> volumeList) {
        this.volumeList = volumeList;
    }


    public Cart getCart() {
        return cart;
    }

    public void setCart(Cart cart) {
        this.cart = cart;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Reader reader = (Reader) o;
        return status == reader.status &&
                Objects.equals(id, reader.id) &&
                Objects.equals(uuid, reader.uuid) &&
                Objects.equals(name, reader.name) &&
                Objects.equals(phoneNumber, reader.phoneNumber) &&
                Objects.equals(emailAddress, reader.emailAddress) &&
                Objects.equals(password, reader.password) &&
                Objects.equals(reservations, reader.reservations) &&
                Objects.equals(volumeList, reader.volumeList);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, uuid, name, phoneNumber, emailAddress, status, password, reservations, volumeList);
    }

    @Override
    public String toString() {
        return "Reader{" +
                "id=" + id +
                ", uuid='" + uuid + '\'' +
                ", name='" + name + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", emailAddress='" + emailAddress + '\'' +
                ", status=" + status +
                ", password='" + password + '\'' +
                ", reservations=" + reservations +
                ", volumeList=" + volumeList +
                '}';
    }

    public List<String> getRolesList(){
        if(this.roles.length() > 0){
            return Arrays.asList(this.roles.split(","));
        }
        return new ArrayList<>();
    }

    public List<String> getPermissionsList(){
        if(this.permissions.length() > 0){
            return Arrays.asList(this.permissions.split(","));
        }
        return new ArrayList<>();
    }
}
