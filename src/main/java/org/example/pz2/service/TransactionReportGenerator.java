package org.example.pz2.service;

import org.example.pz2.model.Transaction;

import java.time.LocalDate;
import java.time.Month;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class TransactionReportGenerator {

    public void printBalanceReport(double totalBalance) {
        System.out.println("Загальний баланс: " + totalBalance);
    }

    public void printTransactionsCountByMonth(String monthYear, int count) {
        System.out.println("Кількість транзакцій за " + monthYear + ": " + count);
    }

    public void printTopExpensesReport(List<Transaction> topExpenses) {
        System.out.println("10 найбільших витрат:");
        for (Transaction expense : topExpenses) {
            System.out.println(expense.getDescription() + ": " + expense.getAmount());
        }
    }

    public void printExpensesByCategory(List<Transaction> transactions) {
        Map<String, Double> expensesByCategory = transactions.stream()
                .filter(t -> t.getAmount() < 0)
                .collect(Collectors.groupingBy(Transaction::getDescription, Collectors.summingDouble(Transaction::getAmount)));

        List<String> keyList = expensesByCategory.keySet().stream().toList();

        for (String key : keyList) {
            int charAmount = (int) (expensesByCategory.get(key) / -500);
            System.out.print(key + " : ");
            Stream.generate(() -> "*").limit(charAmount).forEach(System.out::print);
            System.out.println();
        }
    }

    public void printExpensesByMonth(List<Transaction> transactions) {
        Map<Month, Double> expensesByCategory = transactions.stream()
                .filter(t -> t.getAmount() < 0)
                .collect(Collectors.groupingBy(t -> LocalDate.parse(t.getDate(), DateTimeFormatter.ofPattern("dd-MM-yyyy")).getMonth(), Collectors.summingDouble(Transaction::getAmount)));

        List<Month> keyList = expensesByCategory.keySet().stream().toList();

        for (Month key : keyList) {
            int charAmount = (int) (expensesByCategory.get(key) / -500);
            System.out.println(key + " : ");
            Stream.generate(() -> "X").limit(charAmount).forEach(System.out::print);
            System.out.println();
        }
    }
}
