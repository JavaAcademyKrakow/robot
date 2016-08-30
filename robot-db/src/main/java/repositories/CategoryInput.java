package repositories;


import dao.CategoryDAO;
import domain.Category;
import domain.CategoryName;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
@Slf4j
public class CategoryInput {

    @Autowired
    CategoryDAO categoryDAO;

    Category saveCategory(CategoryName name) {
        Category category = categoryDAO.findByName(name);
        log.info(" save category " + category);
        if (category == null) {
            category = Category.builder().name(name).build();
            categoryDAO.save(category);
        }
        return category;
    }

}
