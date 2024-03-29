package org.example.pz2;

import org.example.pz2.model.Transaction;
import org.example.pz2.service.CSVReader;
import org.example.pz2.service.TransactionAnalyzer;
import org.example.pz2.service.TransactionReportGenerator;

import java.util.List;

public class Main {
    public static void main(String[] args) {

        CSVReader CSVReader = new CSVReader();
        List<Transaction> transactions = CSVReader.readTransaction("https://informer.com.ua/dut/java/pr2.csv");

        for (Transaction tr : transactions) {
            System.out.println(tr);
        }

        TransactionAnalyzer transactionAnalyzer = new TransactionAnalyzer(transactions);

        double totalBalance = transactionAnalyzer.calculateTotalBalance();
        TransactionReportGenerator reportGenerator  = new TransactionReportGenerator();
        System.out.println("---------------------------------------------------------------------------------------");
        reportGenerator.printBalanceReport(totalBalance);

        System.out.println("---------------------------------------------------------------------------------------");
        int transactionCount = transactionAnalyzer.countTransactionsByMonth("01-2024");
        reportGenerator.printTransactionsCountByMonth("01-2024", transactionCount);

        System.out.println("---------------------------------------------------------------------------------------");
        List<Transaction> topExpenses = transactionAnalyzer.findTopExpenses();
        reportGenerator.printTopExpensesReport(topExpenses);

        System.out.println("---------------------------------------------------------------------------------------");
        reportGenerator.printExpensesByCategory(transactions);

        System.out.println("---------------------------------------------------------------------------------------");
        reportGenerator.printExpensesByMonth(transactions);

        System.out.println("Список найбільших витрат за період:");
        List<Transaction> topExpensesForPeriod = transactionAnalyzer.findTopExpensesForPeriod("05-12-2023", "24-01-2024", 5);
        topExpensesForPeriod.forEach(System.out::println);

        System.out.println("Список найменших витрат за період:");
        List<Transaction> lessExpensesForPeriod = transactionAnalyzer.findLeastExpensesForPeriod("05-12-2023", "24-01-2024", 5);
        lessExpensesForPeriod.forEach(System.out::println);
    }
}