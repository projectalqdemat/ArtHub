package com.example.projectr;

public class NotificationItem {
    private int orderCount;
    private String notificationText;

    public NotificationItem(int orderCount, String notificationText) {
        this.orderCount = orderCount;
        this.notificationText = notificationText;
    }

    public int getOrderCount() {
        return orderCount;
    }

    public String getNotificationText() {
        return notificationText;
    }
}

