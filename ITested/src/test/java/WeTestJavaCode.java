import com.itested.login.login;
import org.junit.Test;


public class WeTestJavaCode {

	@Test
	public void createNewUser() {
		login person = new login();
		person.setUemail("oussama_bakri@um5.ac.ma");
		
		System.out.println(person.getUemail());
	}
}
