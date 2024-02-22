package org.example.payloads;

public class Order {
      private int pet_id;
      private int quantity;
      private String shipDate;
      private String status;
      private boolean complete;

      public int getPet_id() {
            return pet_id;
      }

      public void setPet_id(int pet_id) {
            this.pet_id = pet_id;
      }

      public int getQuantity() {
            return quantity;
      }

      public void setQuantity(int quantity) {
            this.quantity = quantity;
      }

      public String getShipDate() {
            return shipDate;
      }

      public void setShipDate(String shipDate) {
            this.shipDate = shipDate;
      }

      public String getStatus() {
            return status;
      }

      public void setStatus(String status) {
            this.status = status;
      }

      public boolean isComplete() {
            return complete;
      }

      public void setComplete(boolean complete) {
            this.complete = complete;
      }
}
