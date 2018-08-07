package io.swagger.web.repo;

import io.swagger.model.Category;
import io.swagger.model.Restaurant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface  CategoryRepository extends JpaRepository<Category, Integer> {
    Category findByCategoryName(String name);
}
