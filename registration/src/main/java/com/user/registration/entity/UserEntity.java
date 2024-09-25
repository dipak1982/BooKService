package com.user.registration.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import java.io.Serializable;
import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name = "users")
public class UserEntity implements Serializable {
  private static final long serialVersionUID = -2731425678149216053L;

  @Id
  @GeneratedValue
  @Column(name = "Id", length = 20)
  private Long userId;

  @NotEmpty
  @Column(name = "name", length = 20)
  private String name;

  @Email
  @Column(name = "email", length = 50)
  private String email;

  @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
  private String dob;

  @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
  @JoinColumn(name = "addressId", referencedColumnName = "id")
  private AddressEntity address;

  @OneToOne(cascade = CascadeType.ALL)
  @JoinColumn(name = "memberId", referencedColumnName = "id")
  private MemberEntity member;

  public Long getUserId() {
    return userId;
  }

  public void setUserId(Long userId) {
    this.userId = userId;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public String getDob() {
    return dob;
  }

  public void setDob(String dob) {
    this.dob = dob;
  }

  public AddressEntity getAddress() {
    return address;
  }

  public void setAddress(AddressEntity address) {
    this.address = address;
  }

  public MemberEntity getMember() {
    return member;
  }

  public void setMember(MemberEntity member) {
    this.member = member;
  }
}
