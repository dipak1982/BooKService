package com.user.registration.service;

import com.user.registration.entity.UserEntity;
import java.util.List;

public interface UserService {
  UserEntity saveUserInfo(UserEntity userEntity);

  List<UserEntity> findAll();

  List<UserEntity> findById(long id);

  UserEntity updateUserInfo(long id, UserEntity userEntity);

  void deleteUserInfo(long id);

  List findAllUsers();
}
