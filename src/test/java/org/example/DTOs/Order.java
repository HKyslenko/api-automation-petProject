package org.example.DTOs;

import lombok.Getter;

@Getter
public class Order {
    private int pet_id;
    private int quantity;
    private String shipDate;
    private String status;
    private boolean complete;

    public static class OrderBuilder {
        private int pet_id;
        private int quantity;
        private String shipDate;
        private String status;
        private boolean complete;

        public OrderBuilder pet_id(int pet_id) {
            this.pet_id = pet_id;
            return this;
        }

        public OrderBuilder quantity(int quantity) {
            this.quantity = quantity;
            return this;
        }

        public OrderBuilder shipDate(String shipDate) {
            this.shipDate = shipDate;
            return this;
        }

        public OrderBuilder status(String status) {
            this.status = status;
            return this;
        }

        public OrderBuilder complete(boolean complete) {
            this.complete = complete;
            return this;
        }

        public Order build() {
            Order order = new Order();
            order.pet_id = this.pet_id;
            order.quantity = this.quantity;
            order.shipDate = this.shipDate;
            order.status = this.status;
            order.complete = this.complete;
            return order;
        }
    }
}
