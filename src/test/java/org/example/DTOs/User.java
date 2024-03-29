package org.example.DTOs;

import lombok.Getter;

@Getter
public class User {

    private String username;
    private String email;
    private String phone;
    private String address;

    public static class UserBuilder {
        private String username;
        private String email;
        private String phone;
        private String address;

        public UserBuilder username(String username) {
            this.username = username;
            return this;
        }

        public UserBuilder email(String email) {
            this.email = email;
            return this;
        }

        public UserBuilder phone(String phone) {
            this.phone = phone;
            return this;
        }

        public UserBuilder address(String address) {
            this.address = address;
            return this;
        }

        public User build() {
            User user = new User();
            user.username = this.username;
            user.email = this.email;
            user.phone = this.phone;
            user.address = this.address;
            return user;
        }
    }
}
