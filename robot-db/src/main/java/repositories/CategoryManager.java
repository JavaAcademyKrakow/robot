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
 * {@link CategoryManager} reads all categories which exists in database and save them in memory
 */
@Repository
@Slf4j
public class CategoryManager {

    @Autowired
    private CategoryDAO categoryDAO;
    private Map<CategoryName, Category> categories;

    @PostConstruct
    private void init() {
        categories = new EnumMap<>(CategoryName.class);
        categoryDAO.findAll().forEach(category -> categories.put(category.getName(), category));
    }

    /**
     * Check if category with a given name exists in database if no create new entry
     * @param name {@link CategoryName} name of category
     * @return category from the memory
     */
    Category getCategory (CategoryName name) {
        if (categories.containsKey(name)) {
            return categories.get(name);
        }
        Category category = Category.builder().name(name).build();
        categories.put(name, category);
        log.info("category" + category);
        return categoryDAO.save(category);
    }

}
