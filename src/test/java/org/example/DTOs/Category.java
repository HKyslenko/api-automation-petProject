package org.example.DTOs;

public class Category {
    private String name;

    public String getName() {
        return name;
    }

    public static class CategoryBuilder {
        private String name;

        public CategoryBuilder name(String name) {
            this.name = name;
            return this;
        }

        public Category build() {
            Category category = new Category();
            category.name = this.name;
            return category;
        }
    }
}
