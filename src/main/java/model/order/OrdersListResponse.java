package model.order;

import java.util.List;

public class OrdersListResponse {

    private boolean success;
    private List<Order> orders;
    private int total;
    private int totalToday;

    public boolean isSuccess() {
        return success;
    }

    public List<Order> getOrders() {
        return orders;
    }

    public int getTotal() {
        return total;
    }

    public int getTotalToday() {
        return totalToday;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public void setOrders(List<Order> orders) {
        this.orders = orders;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public void setTotalToday(int totalToday) {
        this.totalToday = totalToday;
    }
}
