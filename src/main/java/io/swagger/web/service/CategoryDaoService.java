package io.swagger.web.service;

import io.swagger.web.repo.CategoryRepository;
import io.swagger.model.Category;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@ComponentScan(basePackages = { "io.swagger", "io.swagger.web" })
public class CategoryDaoService {

    @Autowired
    private CategoryRepository categoryRepository;

    public List<Category> findAll() {
        return categoryRepository.findAll();
    }

    public int findByCategoryName(String name){
        Category category = categoryRepository.findByCategoryName(name);
        return category.getId();
    }

    public Category save(Category category) {
        return categoryRepository.save(category);
    }

    public Category update(Category category) {
        int Id = category.getId();
        Category catbyID = categoryRepository.findOne(Id);
        catbyID.setCategoryName(category.getCategoryName()) ;
        return categoryRepository.save(catbyID);
    }

    public Category findOne(int id) {
        return categoryRepository.findOne(id);
    }

    public Category deleteById(Category category) {
        int Id = category.getId();
        categoryRepository.delete(Id);
        return null;
    }

}
