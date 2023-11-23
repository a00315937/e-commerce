package com.teamtechno.ecomm;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.ui.Model;

import com.teamtechno.ecomm.controller.AdminController;

public class AdminControllerTest {

	@Mock
	private Connection mockConnection;

	@Mock
	private Statement mockStatement;

	@Mock
	private ResultSet mockResultSet;

	@Mock
	private Model mockModel;

	@InjectMocks
	private AdminController adminController;

	@BeforeEach
	void setUp() throws Exception {
		MockitoAnnotations.openMocks(this);
	}

	@Test
    void testUserLoginValidCredentials() throws Exception {
        // Arrange
        when(mockConnection.createStatement()).thenReturn(mockStatement);
        when(mockStatement.executeQuery(any())).thenReturn(mockResultSet);
        when(mockResultSet.next()).thenReturn(true);
        when(mockResultSet.getString(2)).thenReturn("mockUsername");

        // Act
        String result = adminController.userlogin("mockUsername", "mockPassword", mockModel);

        // Assert
        assertEquals("userLogin", result);
        assertEquals("", adminController.usernameforclass);
    }

	@Test
    void testUserLoginInvalidCredentials() throws Exception {
        // Arrange
        when(mockConnection.createStatement()).thenReturn(mockStatement);
        when(mockStatement.executeQuery(any())).thenReturn(mockResultSet);
        when(mockResultSet.next()).thenReturn(false);

        // Act
        String result = adminController.userlogin("invalidUsername", "invalidPassword", mockModel);

        // Assert
        assertEquals("userLogin", result);
        assertEquals("", adminController.usernameforclass);
        verify(mockModel).addAttribute(eq("message"), eq("Invalid Username or Password"));
    }
}
