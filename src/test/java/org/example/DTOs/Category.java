package org.example.DTOs;

import lombok.Getter;

@Getter
public class Category {
    private String name;

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
