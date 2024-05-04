package com.example.projectr;

public class Order {
    private static int orderNumber;
    private static int status;

    public Order(int orderNumber, int status) {
        Order.orderNumber = orderNumber;
        Order.status = status;
    }

    // Getter and setter methods for orderNumber and status

    public static int getOrderNumber() {
        return orderNumber;
    }

    public void setOrderNumber(int orderNumber) {
        Order.orderNumber = orderNumber;
    }

    public static int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        Order.status = status;
    }

    // Define constants for order status
    public static final int STATUS_APPROVED = 1;
    public static final int STATUS_REJECTED = 2;
    public static final int STATUS_PENDING = 3;
}

