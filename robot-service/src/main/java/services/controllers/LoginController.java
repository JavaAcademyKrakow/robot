package services.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * Rest controller class.
 */
@RestController
public class LoginController {

    @RequestMapping(method = RequestMethod.GET, value = "/login")
    public boolean login(@RequestParam (value = "username") String username,
                         @RequestParam (value = "password") String password) {
        return true;
    }
}
