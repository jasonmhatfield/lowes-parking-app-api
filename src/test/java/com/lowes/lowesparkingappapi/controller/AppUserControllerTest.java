package com.lowes.lowesparkingappapi.controller;

import com.lowes.lowesparkingappapi.model.AppUser;
import com.lowes.lowesparkingappapi.service.AppUserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Collections;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(AppUserController.class)
public class AppUserControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private AppUserService userService;

    private AppUser user;

    @BeforeEach
    public void setUp() {
        user = new AppUser();
        user.setId(1L);
        user.setFirstName("Test");
        user.setLastName("User");
        user.setEmail("testuser@example.com");
        user.setHasHandicapPlacard(true);
        user.setHasEv(false);
        user.setRole("USER");
    }

    @Test
    public void testGetAllUsers() throws Exception {
        Mockito.when(userService.getAllUsers()).thenReturn(Collections.singletonList(user));

        mockMvc.perform(get("/api/users"))
                .andExpect(status().isOk())
                .andExpect(content().json("[{'id':1,'firstName':'Test','lastName':'User'," +
                        "'email':'testuser@example.com','hasHandicapPlacard':true,'hasEv':false,'role':'USER'}]"));
    }

    @Test
    public void testGetUserById() throws Exception {
        Mockito.when(userService.getUserById(anyLong())).thenReturn(Optional.of(user));

        mockMvc.perform(get("/api/users/1"))
                .andExpect(status().isOk())
                .andExpect(content().json("{'id':1,'firstName':'Test','lastName':'User'," +
                        "'email':'testuser@example.com','hasHandicapPlacard':true,'hasEv':false,'role':'USER'}"));
    }

    @Test
    public void testGetUserByIdNotFound() throws Exception {
        Mockito.when(userService.getUserById(anyLong())).thenReturn(Optional.empty());

        mockMvc.perform(get("/api/users/999"))
                .andExpect(status().isNotFound());
    }

    @Test
    public void testCreateUser() throws Exception {
        Mockito.when(userService.saveUser(any(AppUser.class))).thenReturn(user);

        mockMvc.perform(post("/api/users")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"firstName\":\"Test\",\"lastName\":\"User\",\"email\":\"testuser@example.com\"," +
                                "\"hasHandicapPlacard\":true,\"hasEv\":false,\"role\":\"USER\"}"))
                .andExpect(status().isOk())
                .andExpect(content().json("{'id':1,'firstName':'Test','lastName':'User'," +
                        "'email':'testuser@example.com','hasHandicapPlacard':true,'hasEv':false,'role':'USER'}"));
    }

    @Test
    public void testUpdateUser() throws Exception {
        Mockito.when(userService.saveUser(any(AppUser.class))).thenReturn(user);

        mockMvc.perform(put("/api/users/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"firstName\":\"Test\",\"lastName\":\"User\",\"email\":\"testuser@example.com\"," +
                                "\"hasHandicapPlacard\":true,\"hasEv\":false,\"role\":\"USER\"}"))
                .andExpect(status().isOk())
                .andExpect(content().json("{'id':1,'firstName':'Test','lastName':'User'," +
                        "'email':'testuser@example.com','hasHandicapPlacard':true,'hasEv':false,'role':'USER'}"));
    }

    @Test
    public void testDeleteUser() throws Exception {
        Mockito.doNothing().when(userService).deleteUser(anyLong());

        mockMvc.perform(delete("/api/users/1"))
                .andExpect(status().isNoContent());
    }
}
