package services.controllers;

import domain.CategoryName;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import services.model.response.ProfileResponse;

import java.util.Arrays;
import java.util.List;

/**
 * Controller class. Manage controllers when user want to define his type.
 */
@RestController
public class DefineUserTypeController {

    /**
     *
     * @param username
     * @param password
     * @return Response with books - this is because server define user profile base on statistics in db.
     */
    @RequestMapping(method = RequestMethod.POST, value = "/login")
    public ProfileResponse login(@RequestParam (value = "username") String username,
                                 @RequestParam (value = "password") String password) {

        return new ProfileResponse(initList());
    }

    /**
     * We want to create user here (write it to database)
     * @param email
     * @param username
     * @param password
     * @return profiles - after registration it list should be empty
     */
    @RequestMapping(method = RequestMethod.POST, value = "/register")
    public ProfileResponse register(@RequestParam (value = "email") String email,
                            @RequestParam (value = "username") String username,
                            @RequestParam (value = "password") String password) {
        return new ProfileResponse(initList());
    }

    /**
     * User can specify his access to web content as guest. We want to know it on server side
     * @return profiles - it should be empty
     */
    @RequestMapping(method = RequestMethod.GET, value = "/guest")
    public ProfileResponse enterAsGuest() {

        return new ProfileResponse(initList());
    }

    /**
     *
     * @return testing list
     */
    private List<CategoryName> initList() {
        return Arrays.asList(CategoryName.LIVE_STYLE, CategoryName.SEX);
    }
}
