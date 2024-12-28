package com.ecom.amazon.Entity;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    private String categoryName;

    @ManyToOne(cascade = CascadeType.ALL)
    private Category parentCategory;

    @OneToMany(cascade = CascadeType.ALL)
    private List<Category> childCategories = new ArrayList<>();

    //Helper to add child category and set parent category of child category
    public void addChildCategory(Category category){
        this.childCategories.add(category);
        category.setParentCategory(this);
    }

    //Helper to Remove child category
    public void removeChildCategory(Category category){
        this.childCategories.remove(category);
        category.setParentCategory(null);
    }

    //Getters
    public long getId() {
        return id;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public List<Category> getChildCategories() {
        return childCategories;
    }

    public Category getParentCategory() {
        return parentCategory;
    }

    //Setters
    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public void setParentCategory(Category parentCategory) {
        this.parentCategory = parentCategory;
    }
}
