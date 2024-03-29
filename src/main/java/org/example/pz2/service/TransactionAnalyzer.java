package org.example.pz2.service;

import org.example.pz2.model.Transaction;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class TransactionAnalyzer {

    private final List<Transaction> transactions;
    private final DateTimeFormatter dateTimeFormatter;

    public TransactionAnalyzer(List<Transaction> transactions) {
        this.transactions = transactions;
        this.dateTimeFormatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
    }

    public double calculateTotalBalance() {
        return transactions.stream().mapToDouble(Transaction::getAmount).sum();
    }

    public int countTransactionsByMonth(String monthYear) {
        int count = 0;
        for (Transaction transaction : transactions) {
            LocalDate date = LocalDate.parse(transaction.getDate(), dateTimeFormatter);
            String transactionsMonthYear = date.format(DateTimeFormatter.ofPattern("MM-yyyy"));
            if(transactionsMonthYear.equals(monthYear)) {
                count++;
            }
        }
        return count;
    }

    public List<Transaction> findTopExpenses() {
        return transactions.stream()
                .filter(t -> t.getAmount() < 0)
                .sorted(Comparator.comparing(Transaction::getAmount))
                .limit(10)
                .collect(Collectors.toList());
    }

    public List<Transaction> findTopExpensesForPeriod(String startDate, String endDate, int limit) {
        List<Transaction> transactionsByDate = filterTransactionsByDate(transactions, startDate, endDate);

        return transactionsByDate.stream()
                .sorted(Comparator.comparing(Transaction::getAmount))
                .limit(limit)
                .toList();
    }

    public List<Transaction> findLeastExpensesForPeriod(String startDate, String endDate, int limit) {
        List<Transaction> transactionsByDate = filterTransactionsByDate(transactions, startDate, endDate);

        return transactionsByDate.stream()
                .sorted(Comparator.comparing(Transaction::getAmount).reversed())
                .limit(limit)
                .toList();
    }

    private List<Transaction> filterTransactionsByDate(List<Transaction> transactions, String startDate, String endDate) {
        return transactions.stream()
                .filter(transaction -> formatDate(transaction.getDate()).isBefore(formatDate(endDate)))
                .filter(transaction -> formatDate(transaction.getDate()).isAfter(formatDate(startDate)))
                .toList();
    }

    private LocalDate formatDate(String date) {
        return LocalDate.parse(date, dateTimeFormatter);
    }
}
