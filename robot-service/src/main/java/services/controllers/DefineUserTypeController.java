package services.controllers;

import domain.CategoryName;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import services.model.User;
import services.model.UserType;
import services.model.response.ProfileResponse;

import java.util.Arrays;
import java.util.List;

@RestController
public class DefineUserTypeController {

    @RequestMapping(method = RequestMethod.GET, value = "/login")
    public ProfileResponse login(@RequestParam (value = "username") String username,
                                 @RequestParam (value = "password") String password) {
        User user = new User(UserType.Logged, password, username);

        ProfileResponse pr = new ProfileResponse(initList());
        return pr;
    }

    @RequestMapping(method = RequestMethod.GET, value = "/register")
    public ProfileResponse register(@RequestParam (value = "email") String email,
                            @RequestParam (value = "username") String username,
                            @RequestParam (value = "password") String password) {
        User user = new User(UserType.Registered, password, username, email);
        ProfileResponse pr = new ProfileResponse(initList());
        return pr;
    }

    @RequestMapping(method = RequestMethod.GET, value = "/guest")
    public ProfileResponse enterAsGuest() {
        User user = new User(UserType.Guest);

        ProfileResponse pr = new ProfileResponse(initList());
        return pr;
    }

    private List<CategoryName> initList() {
        return Arrays.asList(CategoryName.LIVE_STYLE, CategoryName.SEX);
    }
}
