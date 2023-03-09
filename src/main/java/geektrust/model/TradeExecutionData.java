package geektrust.model;

public class TradeExecutionData {

    private String buyOrderId;
    private Double sellPrice;
    private int quantity;
    private String sellOrderId;

    public TradeExecutionData(String buyOrderId, Double sellPrice, int quantity,
        String sellOrderId) {
        this.buyOrderId = buyOrderId;
        this.sellPrice = sellPrice;
        this.quantity = quantity;
        this.sellOrderId = sellOrderId;
    }
}
