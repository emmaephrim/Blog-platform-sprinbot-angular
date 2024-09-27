package com.blog_platform.category;

import java.util.List;

import org.springframework.stereotype.Service;

@Service
public class CategoryService {

    private final CategoryRepository categoryRepository;

    public CategoryService(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    public void saveCategory(Category category) {
        categoryRepository.save(category);
    }

    public void deleteCategory(String id) {
        categoryRepository.deleteById(id);
    }

    public Category getCategoryById(String id) {
        return categoryRepository.findById(id).orElse(null);
    }

    public List<Category> getAllCategories() {
        return categoryRepository.findAll();
    }

    public Category getCategoryByName(String name) {
        return categoryRepository.findAll().stream()
                .filter(category -> category.getName().equals(name))
                .findFirst()
                .orElse(null);
    }

    public void updateCategory(String id, Category category) {
        Category updateCategory = categoryRepository.findById(id).orElse(null);
        if (updateCategory == null) {
            return;
        }
        updateCategory.setName(category.getName());
        categoryRepository.save(updateCategory);
    }

}
