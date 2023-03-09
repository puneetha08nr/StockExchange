package geektrust.model;

import java.time.LocalTime;

public class StockDetail implements Comparable<StockDetail> {

    private String id;
    private LocalTime time;
    private String stockName;
    private StockType stockType;
    private double price;
    private int quantity;

    public StockDetail(String id, LocalTime time, String stockName, StockType stockType,
        double price, int quantity) {
        this.id = id;
        this.time = time;
        this.stockName = stockName;
        this.stockType = stockType;
        this.price = price;
        this.quantity = quantity;
    }

    public String getId() {
        return id;
    }

    public LocalTime getTime() {
        return time;
    }

    public String getStockName() {
        return stockName;
    }

    public StockType getStockType() {
        return stockType;
    }

    public double getPrice() {
        return price;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    @Override
    public int compareTo(StockDetail o) {
        return this.getTime().compareTo(o.getTime());
    }
}
