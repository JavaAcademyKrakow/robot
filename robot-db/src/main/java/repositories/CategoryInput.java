package repositories;


import dao.CategoryDAO;
import domain.Category;
import domain.CategoryName;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.annotation.PostConstruct;
import java.util.EnumMap;
import java.util.Map;

/**
 * Class tests if Category with given CategoryName exists in database if not it put him
 */
@Repository
@Slf4j
public class CategoryInput {

    @Autowired
    private CategoryDAO categoryDAO;
    private Map<CategoryName, Category> categories;

    @PostConstruct
    private void init() {
        categories = new EnumMap<>(CategoryName.class);
        categoryDAO.findAll().forEach(category -> categories.put(category.getName(), category));
    }

    Category saveCategory(CategoryName name) {
        if (categories.containsKey(name)) {
            return categories.get(name);
        }
        Category author = Category.builder().name(name).build();
        categories.put(name, author);
        categoryDAO.save(author);
        return author;

    }

}
