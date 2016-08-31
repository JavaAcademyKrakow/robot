package services.model.response;

import domain.CategoryName;

import java.util.List;

/**
 * Model Response - this class is parsed and then it's treat like JSON
 */
public class ProfileResponse {

    /**
     * list of books that we want to check when we profile our user
     */
    public final List<CategoryName> categories;

    /**
     * We set final variable here
     * @param categories
     */
    public ProfileResponse(List<CategoryName> categories) {
        this.categories = categories;
    }
}
