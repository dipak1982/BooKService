package com.user.registration.controller;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import com.user.registration.entity.AddressEntity;
import com.user.registration.entity.MemberEntity;
import com.user.registration.entity.UserEntity;
import com.user.registration.exception.ResourceNotFoundException;
import com.user.registration.service.UserService;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;

@SpringBootTest
public class UserControllerTest {

  @InjectMocks private UserController userController;
  @Mock private UserService userService;

  private UserEntity user;
  private AddressEntity address;
  private MemberEntity member;

  @BeforeEach
  void setUp() {
    user = new UserEntity();
    user.setName("Dipak");
    user.setEmail("dipak@dxc.com");
    user.setDob("2024-12-12");

    address = new AddressEntity();
    address.setState("Assam");
    address.setCity("Lumding");
    address.setCountry("India");
    address.setZip(782447);

    member = new MemberEntity();
    member.setType("Yes");

    user.setAddress(address);
    user.setMember(member);
  }

  @Test
  void testsaveUsersInfo() {
    Mockito.when(this.userService.saveUserInfo(Mockito.any(UserEntity.class))).thenReturn(user);
    UserEntity result = this.userController.saveUsersInfo(user).getBody();
    assertNotNull(result);
    assertEquals("Dipak", result.getName());
  }

  @Test
  void testSaveUser_UserNameIsNull() {
    user.setName("");
    Mockito.when(this.userService.saveUserInfo(Mockito.any(UserEntity.class))).thenReturn(user);
    ResourceNotFoundException exception =
        assertThrows(
            ResourceNotFoundException.class, () -> this.userController.saveUsersInfo(user));
    assertEquals("User can't blank", exception.getMessage());
  }

  @Test
  void testfindUserInfo() {
    List<UserEntity> userList = new ArrayList<>();
    userList.add(user);
    Mockito.when(this.userService.findAll()).thenReturn(userList);
    List<UserEntity> result = this.userController.findUserInfo().getBody();
    assertNotNull(result);
    assertEquals("Dipak", result.get(0).getName());
  }

  @Test
  void testfindUserInfo_UserInfoIsNull() {
    List<UserEntity> userList = new ArrayList<>();
    Mockito.when(this.userService.findAll()).thenReturn(userList);
    ResourceNotFoundException exception =
        assertThrows(ResourceNotFoundException.class, () -> this.userController.findUserInfo());
    assertEquals("No Data Available", exception.getMessage());
  }

  @Test
  void testfindById() {
    List<UserEntity> userList = new ArrayList<>();
    userList.add(user);
    Mockito.when(this.userService.findById(Mockito.anyLong())).thenReturn(userList);
    ResponseEntity<List<UserEntity>> result = this.userController.findById(1l);
    assertNotNull(result);
    assertEquals("Dipak", result.getBody().get(0).getName());
  }

  @Test
  void testfindById_UserIdIsNull() {
    ResourceNotFoundException exception =
        assertThrows(ResourceNotFoundException.class, () -> this.userController.findById(0l));
    assertEquals("Id can't be zero", exception.getMessage());
  }

  @Test
  void testUpdateUserInfo() {
    Mockito.when(this.userService.updateUserInfo(Mockito.anyLong(), Mockito.any(UserEntity.class)))
        .thenReturn(user);
    UserEntity result = this.userController.updateUserInfo(user, 1l).getBody();
    assertNotNull(result);
    assertEquals("Dipak", result.getName());
  }

  @Test
  void testUpdateUserDetails() {
    Mockito.when(this.userService.updateUserInfo(Mockito.anyLong(), Mockito.any(UserEntity.class)))
        .thenReturn(user);
    UserEntity result = this.userController.updateUserDetails(user, 1l).getBody();
    assertNotNull(result);
    assertEquals("Dipak", result.getName());
  }

  @Test
  void testDeleteUserInfo() {
    this.userController.deleteUserInfo(1l);
    verify(this.userService, times(1)).deleteUserInfo(1l);
  }
}
