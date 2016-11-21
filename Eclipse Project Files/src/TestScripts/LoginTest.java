package TestScripts;

import static org.junit.Assert.*;
import org.junit.BeforeClass;
import org.junit.Test;
import org.mockito.Mockito;

public class LoginTest {

	static Controller.Login controller;
	View.User user;
	View.Mod mod;
	View.Admin admin;
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		controller = Mockito.mock(Controller.Login.class);
		
		Mockito.when(controller.authenticateUser("user1@gmail.com", "asdasd")).thenReturn(true);
		Mockito.when(controller.authenticateUser("user1@gmail.com", "xyzxyz")).thenReturn(false);
		
		Mockito.when(controller.getUserType("user1@gmail.com")).thenReturn(1);
		Mockito.when(controller.getUserType("user2@gmail.com")).thenReturn(2);
		Mockito.when(controller.getUserType("user3@gmail.com")).thenReturn(3);
		Mockito.when(controller.getUserType("user4@gmail.com")).thenReturn(4);
		Mockito.when(controller.getUserType("user5@gmail.com")).thenReturn(5);		
		
		Mockito.when(controller.getEmailAddress("user1")).thenReturn("user1@gmail.com");
		Mockito.when(controller.getEmailAddress("user2")).thenReturn("user2@gmail.com");
		
		Mockito.when(controller.getUserDetails("user1@gmail.com")).thenReturn(new View.User("user1", "asdasd", new String[]{"user1@gmail.com","user11@gmail.com"}, "User1", "Last Name", "IIIT Delhi", "About Me", new String[]{"http://www.profilephoto.com"}, 4, 1,123));
		Mockito.when(controller.getModeratorDetails("mod1@gmail.com")).thenReturn(new View.Mod("mod1", "asdasd", new String[]{"mod1@gmail.com","mod11@gmail.com"},"First Name", "Last Name", "IIIT Delhi", "About Me", new String[]{"http://www.profilephoto.com"} , 74173513));
		Mockito.when(controller.getAdminDetails("admin1@gmail.com")).thenReturn(new View.Admin("admin1", "asdasd", new String[]{"mod1@gmail.com","mod11@gmail.com"},"First Name", "Last Name", "IIIT Delhi", "About Me", new String[]{"http://www.profilephoto.com"} , 74173513));
		
		Mockito.when(controller.deactivateMyProfile("user1")).thenReturn(true);
		Mockito.when(controller.deactivateMyProfile("user2")).thenReturn(false);
		
		Mockito.when(controller.reactivateAccount("user1","asdasd")).thenReturn(true);
		Mockito.when(controller.reactivateAccount("user2","xyzxyz")).thenReturn(false);
	}

	@Test
	public void testAuthenticateUser() {
		assertTrue(controller.authenticateUser("user1@gmail.com", "asdasd")==true);
		assertTrue(controller.authenticateUser("user1@gmail.com", "xyzxyz")==false);
	}

	@Test
	public void testGetUserType() {
		assertTrue(controller.getUserType("user1@gmail.com")==1);
		assertTrue(controller.getUserType("user2@gmail.com")==2);
		assertTrue(controller.getUserType("user3@gmail.com")==3);
		assertTrue(controller.getUserType("user4@gmail.com")==4);
		assertTrue(controller.getUserType("user5@gmail.com")==5);
	}

	@Test
	public void testGetEmailAddress() {
		assertTrue(controller.getEmailAddress("user1").equals("user1@gmail.com"));
		assertTrue(controller.getEmailAddress("user2").equals("user2@gmail.com"));
	}

	@Test
	public void testGetUserDetails() {
		assertTrue(controller.getUserDetails("user1@gmail.com").getUserName().equals("user1"));
	}

	@Test
	public void testGetModeratorDetails() {
		assertTrue(controller.getModeratorDetails("mod1@gmail.com").getUserName().equals("mod1"));
	}

	@Test
	public void testGetAdminDetails() {
		assertTrue(controller.getAdminDetails("admin1@gmail.com").getUserName().equals("admin1"));
	}

	@Test
	public void testDeactivateMyProfile() {
		assertTrue(controller.deactivateMyProfile("user1")==true);
		assertTrue(controller.deactivateMyProfile("user2")==false);
	}

	@Test
	public void testReactivateAccount() {
		assertTrue(controller.reactivateAccount("user1","asdasd")==true);
		assertTrue(controller.reactivateAccount("user2","xyzxyz")==false);
	}

}
