import java.util.*;
class Stock {
    private String symbol;
    private String name;
    private double price;

    public Stock(String symbol, String name, double price) {
        this.symbol = symbol;
        this.name = name;
        this.price = price;
    }

    public String getSymbol() {
        return symbol;
    }

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return String.format("%s (%s): $%.2f", name, symbol, price);
    }
}
class Portfolio {
    private Map<Stock, Integer> stocks;
    
    public Portfolio() {
        stocks = new HashMap<>();
    }

    public void addStock(Stock stock, int quantity) {
        stocks.put(stock, stocks.getOrDefault(stock, 0) + quantity);
    }

    public void removeStock(Stock stock, int quantity) {
        if (stocks.containsKey(stock)) {
            int currentQuantity = stocks.get(stock);
            if (currentQuantity <= quantity) {
                stocks.remove(stock);
            } else {
                stocks.put(stock, currentQuantity - quantity);
            }
        }
    }
    
    public double getTotalValue() {
        double totalValue = 0;
        for (Map.Entry<Stock, Integer> entry : stocks.entrySet()) {
            totalValue += entry.getKey().getPrice() * entry.getValue();
        }
        return totalValue;
    }

    public void displayPortfolio() {
        System.out.println("Portfolio:");
        for (Map.Entry<Stock, Integer> entry : stocks.entrySet()) {
            System.out.println(entry.getKey() + " - Quantity: " + entry.getValue());
        }
        System.out.println("Total Portfolio Value: $" + getTotalValue());
    }

    public Map<Stock, Integer> getStocks() {
        return stocks;
    }
    
}class Market {
    private List<Stock> stocks;
    private Random random;

    public Market() {
        stocks = new ArrayList<>();
        random = new Random();
        initializeStocks();
    }

    private void initializeStocks() {
        stocks.add(new Stock("AAPL", "Apple Inc.", 150.0));
        stocks.add(new Stock("GOOGL", "Alphabet Inc.", 2800.0));
        stocks.add(new Stock("AMZN", "Amazon.com Inc.", 3400.0));
        stocks.add(new Stock("TSLA", "Tesla Inc.", 700.0));
        stocks.add(new Stock("MSFT", "Microsoft Corporation", 299.0));
    }

    public List<Stock> getStocks() {
        return stocks;
    }

    public void updateMarket() {
        for (Stock stock : stocks) {
            double newPrice = stock.getPrice() * (1 + (random.nextDouble() - 0.5) * 0.05);
            stock.setPrice(newPrice);
        }
    }

    public void displayMarket() {
        System.out.println("Market Data:");
        for (Stock stock : stocks) {
            System.out.println(stock);
        }
    }
}
public class TradingPlatform {
    private Market market;
    private Portfolio portfolio;
    private Scanner scanner;

    public TradingPlatform() {
        market = new Market();
        portfolio = new Portfolio();
        scanner = new Scanner(System.in);
    }

    public void start() {
        while (true) {
            System.out.println("\n1. View Market Data");
            System.out.println("2. Buy Stocks");
            System.out.println("3. Sell Stocks");
            System.out.println("4. View Portfolio");
            System.out.println("5. Exit");
            System.out.print("Choose an option: ");
            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    market.displayMarket();
                    break;
                case 2:
                    buyStocks();
                    break;
                case 3:
                    sellStocks();
                    break;
                case 4:
                    portfolio.displayPortfolio();
                    break;
                case 5:
                    System.out.println("Exiting...");
                    return;
                default:
                    System.out.println("Invalid option. Please try again.");
            }

            market.updateMarket();
        }
    }

    private void buyStocks() {
        market.displayMarket();
        System.out.print("Enter the stock symbol to buy: ");
        String symbol = scanner.next();
        System.out.print("Enter the quantity to buy: ");
        int quantity = scanner.nextInt();

        Stock stockToBuy = null;
        for (Stock stock : market.getStocks()) {
            if (stock.getSymbol().equalsIgnoreCase(symbol)) {
                stockToBuy = stock;
                break;
            }
        }

        if (stockToBuy != null) {
            portfolio.addStock(stockToBuy, quantity);
            System.out.println("Bought " + quantity + " shares of " + stockToBuy.getName());
        } else {
            System.out.println("Stock not found.");
        }
    }

    private void sellStocks() {
        portfolio.displayPortfolio();
        System.out.print("Enter the stock symbol to sell: ");
        String symbol = scanner.next();
        System.out.print("Enter the quantity to sell: ");
        int quantity = scanner.nextInt();

        Stock stockToSell = null;
        for (Stock stock : portfolio.getStocks().keySet()) {
            if (stock.getSymbol().equalsIgnoreCase(symbol)) {
                stockToSell = stock;
                break;
            }
        }

        if (stockToSell != null) {
            portfolio.removeStock(stockToSell, quantity);
            System.out.println("Sold " + quantity + " shares of " + stockToSell.getName());
        } else {
            System.out.println("Stock not found in portfolio.");
        }
    }

    public static void main(String[] args) {
        TradingPlatform platform = new TradingPlatform();
        platform.start();
    }
}
//Sample Output:
//1. View Market Data
//2. Buy Stocks
//3. Sell Stocks
//4. View Portfolio
//5. Exit
//Choose an option: 1
//Market Data:
//Apple Inc. (AAPL): $150.00
//Alphabet Inc. (GOOGL): $2800.00
//Amazon.com Inc. (AMZN): $3400.00
//Tesla Inc. (TSLA): $700.00
//Microsoft Corporation (MSFT): $299.00
//
//1. View Market Data
//2. Buy Stocks
//3. Sell Stocks
//4. View Portfolio
//5. Exit
//Choose an option: 2
//Market Data:
//Apple Inc. (AAPL): $150.71
//Alphabet Inc. (GOOGL): $2808.23
//Amazon.com Inc. (AMZN): $3385.03
//Tesla Inc. (TSLA): $693.50
//Microsoft Corporation (MSFT): $302.80
//Enter the stock symbol to buy: AAPL
//Enter the quantity to buy: 5
//Bought 5 shares of Apple Inc.
//
//1. View Market Data
//2. Buy Stocks
//3. Sell Stocks
//4. View Portfolio
//5. Exit
//Choose an option: 3
//Portfolio:
//Apple Inc. (AAPL): $150.99 - Quantity: 5
//Total Portfolio Value: $754.9673271891248
//Enter the stock symbol to sell: AAPL
//Enter the quantity to sell: 2
//Sold 2 shares of Apple Inc.
//
//1. View Market Data
//2. Buy Stocks
//3. Sell Stocks
//4. View Portfolio
//5. Exit
//Choose an option: 4
//Portfolio:
//Apple Inc. (AAPL): $153.92 - Quantity: 3
//Total Portfolio Value: $461.7611132897799
//
//1. View Market Data
//2. Buy Stocks
//3. Sell Stocks
//4. View Portfolio
//5. Exit
//Choose an option: 4
//Portfolio:
//Apple Inc. (AAPL): $155.39 - Quantity: 3
//Total Portfolio Value: $466.1621654885942
//
//1. View Market Data
//2. Buy Stocks
//3. Sell Stocks
//4. View Portfolio
//5. Exit
//Choose an option: 1
//Market Data:
//Apple Inc. (AAPL): $157.85
//Alphabet Inc. (GOOGL): $2776.69
//Amazon.com Inc. (AMZN): $3273.92
//Tesla Inc. (TSLA): $678.31
//Microsoft Corporation (MSFT): $290.02
//
//1. View Market Data
//2. Buy Stocks
//3. Sell Stocks
//4. View Portfolio
//5. Exit
//Choose an option: 5
//Exiting...
