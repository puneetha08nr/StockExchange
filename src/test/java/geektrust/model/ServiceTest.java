package geektrust.model;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.PriorityQueue;
import org.junit.jupiter.api.Test;

class ServiceTest {

    PriorityQueue<StockDetail> buyStocks = new PriorityQueue<>();
    PriorityQueue<StockDetail> sellStocks = new PriorityQueue<>();
    List<TradeExecutionData> tradeExecutions = new ArrayList<>();

    @Test
    void name() {

        List<StockDetail> stockDetails = new ArrayList<>();
        stockDetails.add(
            new StockDetail("#1", LocalTime.parse("09:46"), "BAC", StockType.SELL, 240.12, 100));
        stockDetails.add(
            new StockDetail("#2", LocalTime.parse("09:47"), "BAC", StockType.SELL, 237.45, 90));
        stockDetails.add(
            new StockDetail("#3", LocalTime.parse("09:48"), "BAC", StockType.BUY, 238.10, 110));
        stockDetails.add(
            new StockDetail("#4", LocalTime.parse("09:49"), "BAC", StockType.BUY, 237.80, 10));
        stockDetails.add(
            new StockDetail("#5", LocalTime.parse("09:50"), "BAC", StockType.BUY, 237.80, 40));
        stockDetails.add(
            new StockDetail("#6", LocalTime.parse("09:30"), "BAC", StockType.SELL, 236.00, 50));

        stockDetails.forEach(stockDetail -> {
            createStockDetail(stockDetail);
            tradeExecution(stockDetail);
        });

        tradeExecutions.forEach(value -> System.out.println(value));
    }

    private void tradeExecution(StockDetail stockDetail) {
        StockType stockType = stockDetail.getStockType();
        PriorityQueue<StockDetail> stockListToCompare = getStockListToCompare(stockType);
        if (stockListToCompare.isEmpty()) {
            return;
        }
        while (true) {
            StockDetail stockToCompare = stockListToCompare.peek();
            if (Objects.isNull(stockToCompare)) {
                break;
            }
            if (StockType.BUY.equals(stockType)) {
                tradeOrders(stockDetail, stockToCompare);
            } else {
                tradeOrders(stockToCompare, stockDetail);
            }
            if (stockToCompare.getQuantity() == 0) {
                stockListToCompare.poll();
            } else {
                break;
            }
        }

//        stockListToCompare.forEach(stockToCompare -> {
//            if (StockType.BUY.equals(stockType)) {
//                tradeOrders(stockDetail, stockToCompare);
//            } else {
//                tradeOrders(stockToCompare, stockDetail);
//            }
//            if (stockToCompare.getQuantity() == 0) {
//                stockListToCompare.poll();
//            }
//        });
    }

    private void tradeOrders(StockDetail buyStockDetail, StockDetail sellStockDetail) {
        if (buyStockDetail.getPrice() >= sellStockDetail.getPrice()) {
            int quantity = buyStockDetail.getQuantity();
            int stockToCompareQuantity = sellStockDetail.getQuantity();
            int difference = Math.abs(quantity - stockToCompareQuantity);
            if (quantity > stockToCompareQuantity) {
                buyStockDetail.setQuantity(difference);
                sellStockDetail.setQuantity(0);
            } else if (quantity < stockToCompareQuantity) {
                sellStockDetail.setQuantity(difference);
                buyStockDetail.setQuantity(0);
            } else {
                sellStockDetail.setQuantity(0);
                buyStockDetail.setQuantity(0);
            }
            TradeExecutionData tradeExecutionData = new TradeExecutionData(buyStockDetail.getId(),
                sellStockDetail.getPrice(), Math.min(quantity, stockToCompareQuantity),
                sellStockDetail.getId());
            tradeExecutions.add(tradeExecutionData);
        }
    }

    private PriorityQueue<StockDetail> getStockListToCompare(StockType stockType) {
        if (StockType.BUY.equals(stockType)) {
            return sellStocks;
        }
        return buyStocks;
    }

    private void createStockDetail(StockDetail stockDetail) {
        if (StockType.BUY.equals(stockDetail.getStockType())) {
            buyStocks.add(stockDetail);
            return;
        }
        sellStocks.add(stockDetail);
    }
}
