package services.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Rest controller class.
 */
@RestController
public class LoginController {

    /**
     * SampleIt method...
     * @param username - string representation of username
     * @param password - string representation of password
     * @return - boolean (true for now)
     */
    @RequestMapping("/login")
    public boolean sampleIt(String username, String password) {
        return true;
    }
}
