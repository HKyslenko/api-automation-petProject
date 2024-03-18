package org.example.DTOs;

import lombok.Getter;

@Getter
public class Tag {
    private String name;

    public static class TagBuilder {
        private String name;

        public TagBuilder name(String name) {
            this.name = name;
            return this;
        }

        public Tag build() {
            Tag tag = new Tag();
            tag.name = this.name;
            return tag;
        }
    }
}
