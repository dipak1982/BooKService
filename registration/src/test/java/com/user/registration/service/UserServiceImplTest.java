package com.user.registration.service;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

import com.user.registration.entity.AddressEntity;
import com.user.registration.entity.MemberEntity;
import com.user.registration.entity.UserEntity;
import com.user.registration.exception.ResourceNotFoundException;
import com.user.registration.repository.UserRepository;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class UserServiceImplTest {
  @InjectMocks private UserServiceImpl userServiceImpl;
  @Mock private UserRepository userRepository;

  private UserEntity user;
  private AddressEntity address;
  private MemberEntity member;

  private UserEntity updatedUserEntity;
  private UserEntity updatedUser;
  private UserEntity updatedUserAddr;

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

    updatedUserEntity = new UserEntity();
    updatedUserEntity.setName("Updated User");
    updatedUserEntity.setDob("1995-01-01");
    updatedUserEntity.setEmail("updated@example.com");

    AddressEntity updatedAddress = new AddressEntity();
    updatedAddress.setCity("Updated City");
    updatedAddress.setState("Updated State");
    updatedAddress.setCountry("Updated Country");
    updatedAddress.setZip(54321);

    updatedUserEntity.setAddress(updatedAddress);

    MemberEntity updatedMember = new MemberEntity();
    updatedMember.setType("Basic");

    updatedUserEntity.setMember(updatedMember);

    updatedUser = new UserEntity();
    updatedUser.setUserId(1l);
    updatedUser.setName("Updated User");
    updatedUser.setDob("1995-01-01");
    updatedUser.setEmail("updated@example.com");

    AddressEntity updatedAddr = new AddressEntity();
    updatedAddr.setAddressId(1l);
    updatedAddr.setCity("Updated City");
    updatedAddr.setState("Updated State");
    updatedAddr.setCountry("Updated Country");
    updatedAddr.setZip(54321);

    updatedUser.setAddress(updatedAddr);

    MemberEntity updatedMem = new MemberEntity();
    updatedMem.setMemberId(1l);
    updatedMem.setType("Basic");

    updatedUser.setMember(updatedMem);

    updatedUserAddr = new UserEntity();
    updatedUserAddr.setUserId(1l);
    updatedUserAddr.setName("Updated User");
    updatedUserAddr.setDob("1995-01-01");
    updatedUserAddr.setEmail("updated@example.com");
  }

  @Test
  void testSaveUserInfo() {
    Mockito.when(this.userRepository.save(Mockito.any(UserEntity.class))).thenReturn(user);
    UserEntity result = this.userServiceImpl.saveUserInfo(user);
    assertNotNull(result);
    assertEquals("Dipak", result.getName());
  }

  @Test
  void testFindAll() {
    List<UserEntity> list = new ArrayList<>();
    list.add(user);
    Mockito.when(this.userRepository.findAll()).thenReturn(list);
    List<UserEntity> result = this.userServiceImpl.findAll();
    assertNotNull(result);
    assertEquals("Dipak", result.get(0).getName());
  }

  @Test
  void testFindById() {
    Mockito.when(this.userRepository.findById(Mockito.anyLong())).thenReturn(Optional.of(user));
    List<UserEntity> result = this.userServiceImpl.findById(updatedUser.getUserId());
    assertNotNull(result);
    assertEquals("Dipak", result.get(0).getName());
  }

  @Test
  void testFindById_UserIdIsNull() {
    Mockito.when(this.userRepository.findById(0l)).thenReturn(Optional.ofNullable(null));
    ResourceNotFoundException exception =
        assertThrows(ResourceNotFoundException.class, () -> this.userServiceImpl.findById(0l));
    assertEquals("No Record found with Id :0", exception.getMessage());
  }

  @Test
  void testUpdateUserInfo() {
    Mockito.when(this.userRepository.findById(Mockito.anyLong())).thenReturn(Optional.of(user));
    Mockito.when(userRepository.save(Mockito.any(UserEntity.class))).thenReturn(user);
    UserEntity result = this.userServiceImpl.updateUserInfo(1l, updatedUserEntity);
    // Verify the interactions and assertions
    verify(userRepository).findById(1L);
    verify(userRepository).save(user);

    assertNotNull(result);
    assertEquals("Updated User", result.getName());
    assertEquals("1995-01-01", result.getDob());
    assertEquals("updated@example.com", result.getEmail());
    assertEquals("Updated City", result.getAddress().getCity());
    assertEquals("Basic", result.getMember().getType());
  }

  @Test
  void testUpdateUserInfoUsingUserId() {
    Mockito.when(this.userRepository.findById(Mockito.anyLong())).thenReturn(Optional.of(user));
    Mockito.when(userRepository.save(Mockito.any(UserEntity.class))).thenReturn(user);
    UserEntity result = this.userServiceImpl.updateUserInfo(1l, updatedUser);
    // Verify the interactions and assertions
    verify(userRepository).findById(1L);
    verify(userRepository).save(user);

    assertNotNull(result);
    assertEquals("Updated User", result.getName());
    assertEquals("1995-01-01", result.getDob());
    assertEquals("updated@example.com", result.getEmail());
    assertEquals("Updated City", result.getAddress().getCity());
    assertEquals("Basic", result.getMember().getType());
  }

  @Test
  void testUpdateUserInfoUsingAddressIsNull() {
    Mockito.when(this.userRepository.findById(Mockito.anyLong())).thenReturn(Optional.of(user));
    Mockito.when(userRepository.save(Mockito.any(UserEntity.class))).thenReturn(user);
    UserEntity result = this.userServiceImpl.updateUserInfo(1l, updatedUserAddr);
    // Verify the interactions and assertions
    verify(userRepository).findById(1L);
    verify(userRepository).save(user);

    assertNotNull(result);
    assertEquals("Updated User", result.getName());
    assertEquals("1995-01-01", result.getDob());
    assertEquals("updated@example.com", result.getEmail());
  }

  @Test
  public void testUpdateUserInfo_ResourceNotFound() {
    Mockito.when(userRepository.findById(2L)).thenReturn(Optional.empty());
    assertThrows(
        ResourceNotFoundException.class,
        () -> {
          this.userServiceImpl.updateUserInfo(2L, updatedUserEntity);
        });
    verify(userRepository).findById(2L);
    verify(userRepository, never()).save(Mockito.any(UserEntity.class));
  }

  @Test
  void testDeleteUserInfo() {
    this.userServiceImpl.deleteUserInfo(1l);
    verify(this.userRepository, times(1)).deleteById(1l);
  }
}
