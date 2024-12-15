package software.developer.bhushan.IndoPe.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import software.developer.bhushan.IndoPe.IndoPeApplication;
import software.developer.bhushan.IndoPe.db.DatabaseHelper;
import software.developer.bhushan.IndoPe.db.models.UserAccountModel;

import java.util.ArrayList;
import java.util.List;


@RestController
public class APIController {

    private DatabaseHelper databaseHelper = new DatabaseHelper();

    @GetMapping("/api/users")
    public List<UserAccountModel> getAllUsers() {
        ArrayList<UserAccountModel> userAccountModelArrayList = databaseHelper.getUserAccountTable()
                .FetchUserModelsInArrayList(databaseHelper.getConnection());

        return userAccountModelArrayList != null ? userAccountModelArrayList : new ArrayList<>();
    }

    @GetMapping("/api/users/{accountnumber}")
    public UserAccountModel getUser(@PathVariable("accountnumber") String accountnumber) {
        UserAccountModel userAccountModel = databaseHelper.getUserAccountTable()
                .FetchByAccountNumber(databaseHelper.getConnection(), accountnumber);
        return userAccountModel != null ? userAccountModel : new UserAccountModel();
    }

    @GetMapping("/api/users/{username}/{password}")
    public ResponseEntity<UserAccountModel> signInUserByUsernamePassword(@PathVariable String username,
            @PathVariable String password) {

        // Fetch the user by username and password from the database
        UserAccountModel userAccountModel = databaseHelper.getUserAccountTable()
                .FetchUserAccountModelByUserNameAndPassword(databaseHelper.getConnection(), username, password);

        // If user is found, return the user details
        if (userAccountModel != null) {
            IndoPeApplication.USERS_ARE_LOGINED.add(userAccountModel.getUserAccountNumber());
            return ResponseEntity.ok(userAccountModel); // Return HTTP 200 with user details
        } else {
            
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }

}
