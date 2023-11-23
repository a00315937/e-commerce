package com.teamtechno.ecomm;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.ui.Model;

import com.teamtechno.ecomm.controller.UserController;

@ExtendWith(MockitoExtension.class)
class UserControllerTest {

	@Mock
	private Connection mockConnection;

	@Mock
	private PreparedStatement mockPreparedStatement;

	@Mock
	private Statement mockStatement;

	@Mock
	private ResultSet mockResultSet;

	@Mock
	private Model mockModel;

	@InjectMocks
	private UserController userController;

	@Test
	void testNewUseRegister() throws Exception {
		String result = userController.newUseRegister("Ashwini", "123", "a@gmail.com");
		assertEquals("redirect:/", result);
	}
}