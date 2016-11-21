package TestScripts;

import static org.junit.Assert.*;
import org.mockito.*;
import org.junit.BeforeClass;
import org.junit.Test;

public class RegisterTest {

	static Controller.Register controller;
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		controller = Mockito.mock(Controller.Register.class);
		Mockito.when(controller.isUsernameUnique("user1")).thenReturn(true);
		Mockito.when(controller.isUsernameUnique("user2")).thenReturn(false);
		
		Mockito.when(controller.isEmailUnique("user1@gmail.com")).thenReturn(true);
		Mockito.when(controller.isEmailUnique("user2@gmail.com")).thenReturn(false);
	}

	@Test
	public void testIsUsernameUnique() {
		assertTrue(controller.isUsernameUnique("user1")==true);
		assertTrue(controller.isUsernameUnique("user2")==false);
	}

	@Test
	public void testIsEmailUnique() {
		assertTrue(controller.isEmailUnique("user1@gmail.com")==true);
		assertTrue(controller.isEmailUnique("user2@gmail.com")==false);
	}
}
