package org.example.DTOs;

import lombok.Getter;

@Getter
public class Pet {

    private int category_id;
    private String name;
    private String status;
    private String photoUrls;
    private String tags;

    public static class PetBuilder {
        private int category_id;
        private String name;
        private String status;
        private String photoUrls;
        private String tags;

        public PetBuilder category_id(int category_id) {
            this.category_id = category_id;
            return this;
        }

        public PetBuilder name(String name) {
            this.name = name;
            return this;
        }

        public PetBuilder status(String status) {
            this.status = status;
            return this;
        }

        public PetBuilder photoUrls(String photoUrls) {
            this.photoUrls = photoUrls;
            return this;
        }

        public PetBuilder tags(String tags) {
            this.tags = tags;
            return this;
        }

        public Pet build() {
            Pet pet = new Pet();
            pet.category_id = this.category_id;
            pet.name = this.name;
            pet.status = this.status;
            pet.photoUrls = this.photoUrls;
            pet.tags = this.tags;
            return pet;
        }
    }
}
