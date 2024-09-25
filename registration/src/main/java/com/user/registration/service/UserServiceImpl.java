package com.user.registration.service;

import com.user.registration.entity.AddressEntity;
import com.user.registration.entity.MemberEntity;
import com.user.registration.entity.UserEntity;
import com.user.registration.exception.ResourceNotFoundException;
import com.user.registration.repository.UserRepository;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

  @Autowired private UserRepository userRepository;

  public UserEntity saveUserInfo(UserEntity userEntity) {
    UserEntity result = this.userRepository.save(userEntity);
    return result;
  }

  @Override
  public List<UserEntity> findAll() {
    List<UserEntity> list = this.userRepository.findAll();
    return list;
  }

  @Override
  public List<UserEntity> findById(long id) {
    Optional<UserEntity> userList =
        Optional.of(
            this.userRepository
                .findById(id)
                .orElseThrow(
                    () -> new ResourceNotFoundException("No Record found with Id :" + id)));
    List<UserEntity> result = userList.stream().collect(Collectors.toList());
    return result;
  }

  @Override
  public UserEntity updateUserInfo(long id, UserEntity userEntity) {
    Optional<UserEntity> userList = this.userRepository.findById(id);
    UserEntity result;
    if (userList.isPresent()) {
      UserEntity list = userList.get();
      list.setName(userEntity.getName());
      list.setDob(userEntity.getDob());
      list.setEmail(userEntity.getEmail());
      if (userEntity.getAddress() != null) {
        if (userEntity.getAddress().getAddressId() == null) {
          AddressEntity addressEntity = new AddressEntity();
          addressEntity.setAddressId(userList.get().getAddress().getAddressId());
          addressEntity.setCity(userEntity.getAddress().getCity());
          addressEntity.setState(userEntity.getAddress().getState());
          addressEntity.setCountry(userEntity.getAddress().getCountry());
          addressEntity.setZip(userEntity.getAddress().getZip());
          list.setAddress(addressEntity);
        } else {
          list.setAddress(userEntity.getAddress());
        }
      }
      if (userEntity.getMember() != null) {
        if (userEntity.getMember().getMemberId() == null) {
          MemberEntity memberEntity = new MemberEntity();
          memberEntity.setMemberId(userList.get().getMember().getMemberId());
          memberEntity.setType(userEntity.getMember().getType());
          list.setMember(memberEntity);
        } else {
          list.setMember(userEntity.getMember());
        }
      }
      result = this.userRepository.save(list);
    } else {
      throw new ResourceNotFoundException("No Record found with Id : " + id);
    }
    return result;
  }

  @Override
  public void deleteUserInfo(long id) {
    this.userRepository.deleteById(id);
  }

  @Override
  public List findAllUsers() {
    return this.userRepository.findAll();
  }
}
