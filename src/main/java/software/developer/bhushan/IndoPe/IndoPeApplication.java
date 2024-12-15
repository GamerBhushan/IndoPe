package software.developer.bhushan.IndoPe;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.ArrayList;


@SpringBootApplication 
public class IndoPeApplication {
	
	public static ArrayList<String> USERS_ARE_LOGINED = new ArrayList<>();


	public static void main(String[] args) throws Exception {
		AppProperties.AppName = "IndoPe";
		SpringApplication.run(IndoPeApplication.class, args);
	}

	public static boolean checkIsUserLogin(String accountNumber) {
		return USERS_ARE_LOGINED.stream().anyMatch(acNo -> acNo.equals(accountNumber));
	}

}
