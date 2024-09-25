package com.user.registration.controller;

import com.user.registration.entity.UserEntity;
import com.user.registration.exception.ResourceNotFoundException;
import com.user.registration.service.UserService;
import io.micrometer.common.util.StringUtils;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v1")
@CrossOrigin(origins = "http://localhost:4200") // Allow requests from this origin
public class UserController {
  @Autowired private UserService userService;

  @PostMapping("/save-userinfo")
  public ResponseEntity<UserEntity> saveUsersInfo(@RequestBody UserEntity userEntity) {
    UserEntity result = this.userService.saveUserInfo(userEntity);
    if (StringUtils.isBlank(result.getName())) {
      throw new ResourceNotFoundException("User can't blank");
    }
    return ResponseEntity.status(HttpStatus.CREATED).body(result);
  }

  @GetMapping("/get-all-users")
  public ResponseEntity<List<UserEntity>> findUserInfo() {
    List<UserEntity> userList = this.userService.findAll();
    if (userList.isEmpty()) {
      throw new ResourceNotFoundException("No Data Available");
    }
    return ResponseEntity.status(HttpStatus.ACCEPTED).body(userList);
  }

  @GetMapping("/findById/{id}")
  public ResponseEntity<List<UserEntity>> findById(@PathVariable long id) {
    List<UserEntity> userList = null;
    if (id == 0) {
      throw new ResourceNotFoundException("Id can't be zero");
    } else {
      userList = this.userService.findById(id);
    }
    return ResponseEntity.status(HttpStatus.ACCEPTED).body(userList);
  }

  @PutMapping("/update/{id}")
  public ResponseEntity<UserEntity> updateUserInfo(
      @RequestBody UserEntity userEntity, @PathVariable long id) {
    UserEntity userList = this.userService.updateUserInfo(id, userEntity);
    return ResponseEntity.status(HttpStatus.ACCEPTED).body(userList);
  }

  @PatchMapping("/update-user-info/{id}")
  public ResponseEntity<UserEntity> updateUserDetails(
      @RequestBody UserEntity userEntity, @PathVariable long id) {
    UserEntity userList = this.userService.updateUserInfo(id, userEntity);
    return ResponseEntity.status(HttpStatus.ACCEPTED).body(userList);
  }

  @DeleteMapping("/delete-User-Info/{id}")
  public void deleteUserInfo(@PathVariable long id) {
    this.userService.deleteUserInfo(id);
  }

  @GetMapping("/findallusers")
  public ResponseEntity<List> findAllUsers() {
    List result = this.userService.findAllUsers();
    return ResponseEntity.status(HttpStatus.ACCEPTED).body(result);
  }
}
