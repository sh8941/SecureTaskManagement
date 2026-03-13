package com.haider.SecureTaskManagement.service;

import com.haider.SecureTaskManagement.entity.UserEntity;
import com.haider.SecureTaskManagement.repo.UserRepo;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;


@ExtendWith(MockitoExtension.class)
class UserServiceTest {

    @Mock
    UserRepo userRepo;
    @InjectMocks
    UserService userService;

    @BeforeAll
    public static void init() {
//        get executed only once
        System.out.println("before all executed");
    }

    @BeforeEach
    public void beforeEach() {
        System.out.println("before each executed");
    }

    @AfterEach
    public void afterEach() {
        // executed on every testcase
        System.out.println("after each executed");
    }

    @AfterAll
    public static void afterAll() {
        System.out.println("after all executed");
    }

    @Test
    void addUser() {
        System.out.println("addUser() unit test started");

        UserEntity  user = new UserEntity();
        user.setUsername("admin");
        user.setPassword("admin");
        user.setEmail("admin@test.com");
        user.setRoleId(1L);

        // telling to Mockito what should happen when this method call
        // return user instead of calling database
        // just a mocking def
        // we are testing here service layer not repo
        Mockito.when(userRepo.save(user)).thenReturn(user);
        UserEntity result = userService.addUser(user);

        // test cases
        Assertions.assertNotNull(result);
        Assertions.assertEquals(user.getUsername(), result.getUsername());
        Assertions.assertEquals(user.getEmail(), result.getEmail());
        Assertions.assertEquals(user.getRoleId(), result.getRoleId());
        Assertions.assertEquals(user.getPassword(), result.getPassword());

        System.out.println("addUser() unit test returning");

    }

    @Test
    void getUser() {
        System.out.println("getUser() unit test started");
    }
}