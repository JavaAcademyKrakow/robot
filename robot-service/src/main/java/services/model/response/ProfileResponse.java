package services.model.response;

import domain.CategoryName;

import java.util.List;

/**
 * Created by daniel on 30.08.16.
 */
public class ProfileResponse {
    public final List<CategoryName> categories;

    public ProfileResponse(List<CategoryName> categories) {
        this.categories = categories;
    }
}
