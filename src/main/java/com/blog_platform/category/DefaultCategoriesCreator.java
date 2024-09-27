package com.blog_platform.category;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

@Component
@Configuration
public class DefaultCategoriesCreator {

    @Autowired
    private CategoryRepository categoryRepository;

    @Bean
    public ApplicationRunner createDefaultCategories() {
        return args -> {
            if (categoryRepository.count() == 0) {
                Category category1 = new Category();
                category1.setName("Education");
                categoryRepository.save(category1);

                Category category2 = new Category();
                category2.setName("Finance");
                categoryRepository.save(category2);

                Category category3 = new Category();
                category3.setName("Politics");
                categoryRepository.save(category3);

                Category category4 = new Category();
                category4.setName("Sports");
                categoryRepository.save(category4);

                Category category5 = new Category();
                category5.setName("Entertainment");
                categoryRepository.save(category5);

                Category category6 = new Category();
                category6.setName("Technology");
                categoryRepository.save(category6);

                System.out.println("Default categories created");
            } else {
                System.out.println("Default categories already exist, skipping creation....");
            }
        };
    }

}
