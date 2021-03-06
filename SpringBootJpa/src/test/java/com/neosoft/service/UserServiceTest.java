package com.neosoft.service;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import com.neosoft.controller.UserController;
import com.neosoft.entities.User;
import com.neosoft.exception.UserNotFoundException;
import com.neosoft.repository.UserRepository;

@RunWith(MockitoJUnitRunner.class)
public class UserServiceTest {

	@InjectMocks
	UserService userService;

	@Mock
	UserRepository userRepository;

	@Test
	public void registerUser() {
		User user = new User(5L, "Pranav", "Choudhary", "Pranav@Gmail.com", "442905", "2345432345", "active",
				new Date(), new Date());
		when(userRepository.save(user)).thenReturn(user);
		assertEquals(user, userService.registerUser(user));
	}

	@Test
	public void updateUserTest() {
		User user = new User(5L, "Pranav", "Choudhary", "Pranav@Gmail.com", "442905", "2345432345", "active",
				new Date(), new Date());
		long id = user.getUid();
		when(userRepository.save(user)).thenReturn(user);
		User result = userService.updateUser(id, user);
		Assert.assertNotNull(result);
		//assertEquals(user, result);
	}

	@Test(expected = UserNotFoundException.class)
	public void updateUserTest2() {
		User user = new User(5L, "Pranav", "Choudhary", "Pranav@Gmail.com", "442905", "2345432345", "active",
				new Date(), new Date());
		long id = user.getUid();
		when(userRepository.save(user)).thenReturn(user);
		User result = userService.updateUser2(id, user);
		Assert.assertNotNull(result);
		//assertEquals(user, result);
	}

	@Test(expected = UserNotFoundException.class)
	public void deleteUserbyid2() {
		User user = new User(5L, "Pranav", "Choudhary", "Pranav@Gmail.com", "442905", "2345432345", "active",
				new Date(), new Date());
		long id = user.getUid();
		userService.deleteUserbyid2(id);
		verify(userRepository, times(1)).deleteById(id);
	}

	@Test
	public void showAllUserTest() {
		when(userRepository.findAll()).thenReturn(Stream.of(new User(3l, "Pranav", "Choudhary", "Pranav@Gmail.com",
				"442905", "2345432345", "active", new Date(), new Date())).collect(Collectors.toList()));
		assertEquals(1, userService.showAllUser().size());
	}

	@Test
	public void getUserbyfirstameTest() {
		String firstname = "Mayank";
		when(userRepository.findByFirstname(firstname))
				.thenReturn(Stream.of(new User(3l, "Pranav", "Choudhary", "Pranav@Gmail.com", "442905", "2345432345",
						"active", new Date(), new Date())).collect(Collectors.toList()));
		Assert.assertEquals(1, userService.getUserbyfirstame(firstname).size());
	}

	@Test
	public void searchUserbylastNameTest() {
		String lastname = "Bhandakkar";
		when(userRepository.findByLastname(lastname))
				.thenReturn(Stream.of(new User(3l, "Pranav", "Choudhary", "Pranav@Gmail.com", "442905", "2345432345",
						"active", new Date(), new Date())).collect(Collectors.toList()));
		Assert.assertEquals(1, userService.searchUserbylastName(lastname).size());
	}

	@Test
	public void searchUserbyPinCodeTest() {
		String pincode = "442905";
		when(userRepository.findByPincode(pincode))
				.thenReturn(Stream.of(new User(3l, "Pranav", "Choudhary", "Pranav@Gmail.com", "442905", "2345432345",
						"active", new Date(), new Date())).collect(Collectors.toList()));
		Assert.assertEquals(1, userService.searchUserbyPinCode(pincode).size());
	}

	@Test
	public void sortUserbydob() {
		List<User> listuser = new ArrayList<>();
		String dob = "2021";
		when(userRepository.findByOrderByDobAsc()).thenReturn(listuser);
		List<User> result = userService.sortUserbydob(dob);
		Assert.assertNotNull(result);
	}

	@Test
	public void sortUserbyjoiningdate() {
		List<User> listuser = new ArrayList<>();
		String joiningdate = "2021";
		when(userRepository.findAllByOrderByJoiningdateAsc()).thenReturn(listuser);
		List<User> result = userService.sortUserbyjoiningdate(joiningdate);
		Assert.assertNotNull(result);
	}

}
