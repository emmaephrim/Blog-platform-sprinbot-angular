package com.blog_platform.category;

import org.springframework.stereotype.Repository;

import org.springframework.data.mongodb.repository.MongoRepository;

@Repository
public interface CategoryRepository extends MongoRepository<Category, String> {

}
