package com.user.registration.entity;

import jakarta.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "address")
public class AddressEntity implements Serializable {
  private static final long serialVersionUID = -2731425678149216053L;

  @Id
  @GeneratedValue
  @Column(name = "Id", length = 20)
  private Long addressId;

  @Column(name = "city", length = 20)
  private String city;

  @Column(name = "state", length = 20)
  private String state;

  @Column(name = "country", length = 20)
  private String country;

  @Column(name = "zip", length = 20)
  private int zip;

  @OneToOne(cascade = CascadeType.ALL, mappedBy = "address")
  private UserEntity user;

  public Long getAddressId() {
    return addressId;
  }

  public void setAddressId(Long addressId) {
    this.addressId = addressId;
  }

  public String getCity() {
    return city;
  }

  public void setCity(String city) {
    this.city = city;
  }

  public String getState() {
    return state;
  }

  public void setState(String state) {
    this.state = state;
  }

  public String getCountry() {
    return country;
  }

  public void setCountry(String country) {
    this.country = country;
  }

  public int getZip() {
    return zip;
  }

  public void setZip(int zip) {
    this.zip = zip;
  }
}
