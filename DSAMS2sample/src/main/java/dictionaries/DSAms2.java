package dictionaries;

import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.TreeMap;

public class DSAms2 {

    public static void main(String[] args) {
        Scanner strScanner = new Scanner(System.in);
        Scanner intScanner = new Scanner(System.in);

        System.out.print("Enter the number of stocks: ");
        int number = intScanner.nextInt();

        // Implementation of Map
        Map<String, Map<String, String>> stockInfo = new HashMap<>();

        // Input stock information to the Map
        for (int i = 0; i < number; i++) {
            System.out.print("Enter brand for stock " + (i + 1) + ": ");
            String brand = strScanner.nextLine();
            System.out.print("Enter engine number for stock " + (i + 1) + ": ");
            String engineNumber = strScanner.nextLine();
            System.out.print("Enter label status of stock " + (i + 1) + ": ");
            String labelStatus = strScanner.nextLine();
            System.out.print("Enter purchase status of stock " + (i + 1) + ": ");
            String purchaseStatus = strScanner.nextLine();

            Map<String, String> stockDetails = new HashMap<>();
            stockDetails.put("brand", brand);
            stockDetails.put("engineNumber", engineNumber);
            stockDetails.put("labelStatus", labelStatus);
            stockDetails.put("purchaseStatus", purchaseStatus);

            stockInfo.put(brand + engineNumber, stockDetails);
        }

        // Print values of the stockInfo Map (unsorted)
        System.out.println("\nUnsorted Values");
        printStockInfo(stockInfo);

        // Sort stockInfo map according to brand
        System.out.println("\nSorted Values according to Brand");
        Map<String, Map<String, String>> sortedStockInfo = sortStockInfo(stockInfo);
        printStockInfo(sortedStockInfo);

        boolean searchActive = true;
        while (searchActive) {
            // Prompt the user for the search criteria
            System.out.println("\nWhat would you like to search for?");
            System.out.println("1. Brand");
            System.out.println("2. Engine Number");
            System.out.println("3. Label Status");
            System.out.println("4. Purchase Status");
            System.out.println("5. Exit");

            Scanner srchscanner = new Scanner(System.in);
            int searchChoice = srchscanner.nextInt();

            switch (searchChoice) {
                case 1:
                    // Search by brand
                    System.out.print("Enter the brand to search for: ");
                    String searchBrand = strScanner.next();
                    searchStockByBrand(sortedStockInfo, searchBrand);
                    break;
                case 2:
                    // Search by engine number
                    System.out.print("Enter the engine number to search for: ");
                    String searchEngineNumber = strScanner.next();
                    searchStockByEngineNumber(sortedStockInfo, searchEngineNumber);
                    break;
                case 3:
                    // Search by label status
                    System.out.print("Enter the label status to search for: ");
                    String searchLabelStatus = strScanner.next();
                    searchStockByLabelStatus(sortedStockInfo, searchLabelStatus);
                    break;
                case 4:
                    // Search by purchase status
                    System.out.print("Enter the purchase status to search for: ");
                    String searchPurchaseStatus = strScanner.next();
                    searchStockByPurchaseStatus(sortedStockInfo, searchPurchaseStatus);
                    break;
                case 5:
                    // Exit the search mode
                    searchActive = false;
                    System.out.println("Exiting search mode.");
                    break;
                default:
                    System.out.println("Invalid choice");
            }
        }
    }

    // Method for printing stock information
    private static void printStockInfo(Map<String, Map<String, String>> stockInfo) {
        for (Map.Entry<String, Map<String, String>> entry : stockInfo.entrySet()) {
            Map<String, String> stockDetails = entry.getValue();
            System.out.println(stockDetails.get("brand") + "\t" + stockDetails.get("engineNumber") + "\t" + stockDetails.get("labelStatus") + "\t" + stockDetails.get("purchaseStatus"));
        }
    }

    // Method for sorting stock information according to brand
    private static Map<String, Map<String, String>> sortStockInfo(Map<String, Map<String, String>> stockInfo) {
    Map<String, Map<String, String>> sortedStockInfo = new TreeMap<>(new Comparator<String>() {
        @Override
        public int compare(String o1, String o2) {
            Map<String, String> stock1 = stockInfo.get(o1);
            Map<String, String> stock2 = stockInfo.get(o2);
            int brandCompare = stock1.get("brand").compareTo(stock2.get("brand"));
            if (brandCompare == 0) {
                return stock1.get("engineNumber").compareTo(stock2.get("engineNumber"));
            }
            return brandCompare;
        }
    });
    sortedStockInfo.putAll(stockInfo);
    return sortedStockInfo;
}

    // Method for searching stock information by brand
    private static void searchStockByBrand(Map<String, Map<String, String>> stockInfo, String searchBrand) {
        boolean found = false;
        for (Map.Entry<String, Map<String, String>> entry : stockInfo.entrySet()) {
            Map<String, String> stockDetails = entry.getValue();
            if (stockDetails.get("brand").equals(searchBrand)) {
                System.out.println(stockDetails.get("brand") + "\t" + stockDetails.get("engineNumber") + "\t" + stockDetails.get("labelStatus") + "\t" + stockDetails.get("purchaseStatus"));
                found = true;
            }
        }
        if (!found) {
            System.out.println("No stocks found with brand " + searchBrand);
        }
    }

    // Method for searching stock information by engine number
    private static void searchStockByEngineNumber(Map<String, Map<String, String>> stockInfo, String searchEngineNumber) {
        boolean found = false;
        for (Map.Entry<String, Map<String, String>> entry : stockInfo.entrySet()) {
            Map<String, String> stockDetails = entry.getValue();
            if (stockDetails.get("engineNumber").equals(searchEngineNumber)) {
                System.out.println(stockDetails.get("brand") + "\t" + stockDetails.get("engineNumber") + "\t" + stockDetails.get("labelStatus") + "\t" + stockDetails.get("purchaseStatus"));
                found = true;
            }
        }
        if (!found) {
            System.out.println("No stocks found with engine number " + searchEngineNumber);
        }
    }

    // Method for searching stock information by label status
    private static void searchStockByLabelStatus(Map<String, Map<String, String>> stockInfo, String searchLabelStatus) {
        boolean found = false;
        for (Map.Entry<String, Map<String, String>> entry : stockInfo.entrySet()) {
            Map<String, String> stockDetails = entry.getValue();
            if (stockDetails.get("labelStatus").equals(searchLabelStatus)) {
                System.out.println(stockDetails.get("brand") + "\t" + stockDetails.get("engineNumber") + "\t" + stockDetails.get("labelStatus") + "\t" + stockDetails.get("purchaseStatus"));
                found = true;
            }
        }
        if (!found) {
            System.out.println("No stocks found with label status " + searchLabelStatus);
        }
    }

    // Method for searching stock information by purchase status
    private static void searchStockByPurchaseStatus(Map<String, Map<String, String>> stockInfo, String searchPurchaseStatus) {
        boolean found = false;
        for (Map.Entry<String, Map<String, String>> entry : stockInfo.entrySet()) {
            Map<String, String> stockDetails = entry.getValue();
            if (stockDetails.get("purchaseStatus").equals(searchPurchaseStatus)) {
                System.out.println(stockDetails.get("brand") + "\t" + stockDetails.get("engineNumber") + "\t" + stockDetails.get("labelStatus") + "\t" + stockDetails.get("purchaseStatus"));
                found = true;
            }
        }
        if (!found) {
            System.out.println("No stocks found with purchase status " + searchPurchaseStatus);
        }
    }
}