package test;

import static org.junit.jupiter.api.Assertions.*;

import static org.junit.Assert.assertEquals;

import org.junit.jupiter.api.Test;

import com.itested.user.User;

class UserTest {

	@Test
	void testGetUname() {
		User user = new User();
		user.setUname("oussama");
		assertEquals("Erreur", user.getUname(), "oussama");
	}

	@Test
	void testGetUemail() {
		User user = new User();
		user.setUemail("oussama_bakri@um5.ac.ma");
		assertEquals("Erreur", user.getUemail(), "oussama_bakri@um5.ac.ma");
	}
	
	@Test
	void testGetUemailNull() {
		User user = new User();
		user.setUemail("oussama_bakrium5.ac.ma");
		assertEquals("Erreur", user.getUemail(), null);
	}
}
