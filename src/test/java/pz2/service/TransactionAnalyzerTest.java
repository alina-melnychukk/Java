package pz2.service;

import org.example.pz2.model.Transaction;
import org.example.pz2.service.TransactionAnalyzer;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

public class TransactionAnalyzerTest {

    @Test
    public void testCalculateTotalBalance_ResultPositive() {
        Transaction transaction1 = new Transaction("", 1100.0, "");
        Transaction transaction2 = new Transaction("", -150.0, "");
        Transaction transaction3 = new Transaction("", 230.0, "");
        List<Transaction> transactions = Arrays.asList(transaction1, transaction2, transaction3);

        TransactionAnalyzer transactionAnalyzer = new TransactionAnalyzer(transactions);

        assertEquals(1180.0, transactionAnalyzer.calculateTotalBalance());
        assertNotEquals(0, transactionAnalyzer.calculateTotalBalance());
    }

    @Test
    public void testCalculateTotalBalance_ZeroResult() {
        Transaction transaction1 = new Transaction("2023-01-01", 0, "Income");
        Transaction transaction2 = new Transaction("2023-01-02", 0, "Spend");
        Transaction transaction3 = new Transaction("2023-01-03", 0, "Income");
        List<Transaction> transactions = Arrays.asList(transaction1, transaction2, transaction3);

        TransactionAnalyzer transactionAnalyzer = new TransactionAnalyzer(transactions);

        assertEquals(0, transactionAnalyzer.calculateTotalBalance());
    }

    @Test
    public void testCalculateTotalBalance_ResultLessThanZero() {
        Transaction transaction1 = new Transaction("", -20, "");
        Transaction transaction2 = new Transaction("", -30, "");
        Transaction transaction3 = new Transaction("", -5, "");
        List<Transaction> transactions = Arrays.asList(transaction1, transaction2, transaction3);

        TransactionAnalyzer transactionAnalyzer = new TransactionAnalyzer(transactions);

        assertEquals(-55, transactionAnalyzer.calculateTotalBalance());
    }

    @Test
    public void testCountTransactionsByMonth() {
        Transaction transaction1 = new Transaction("01-01-2023", -10, "");
        Transaction transaction2 = new Transaction("02-01-2023", -30, "");
        Transaction transaction3 = new Transaction("03-01-2023", 0, "");
        List<Transaction> transactions = Arrays.asList(transaction1, transaction2, transaction3);

        TransactionAnalyzer transactionAnalyzer = new TransactionAnalyzer(transactions);

        assertEquals(3, transactionAnalyzer.countTransactionsByMonth("01-2023"));
        assertEquals(0, transactionAnalyzer.countTransactionsByMonth("04-2023"));
    }

    @Test
    public void testFindTopExpenses() {
        Transaction transaction1 = new Transaction("", -10, "");
        Transaction transaction2 = new Transaction("", -20, "");
        Transaction transaction3 = new Transaction("", -30, "");
        Transaction transaction4 = new Transaction("", -40, "");
        Transaction transaction5 = new Transaction("", -50, "");
        Transaction transaction6 = new Transaction("", -60, "");
        Transaction transaction7 = new Transaction("", -70, "");
        Transaction transaction8 = new Transaction("", -80, "");
        Transaction transaction9 = new Transaction("", -90, "");
        Transaction transaction10 = new Transaction("", -100, "");
        List<Transaction> transactions = Arrays.asList(transaction1, transaction2, transaction3, transaction4, transaction5, transaction6, transaction7, transaction8, transaction9, transaction10);

        TransactionAnalyzer transactionAnalyzer = new TransactionAnalyzer(transactions);

        List<Transaction> result = transactionAnalyzer.findTopExpenses();

        assertEquals(result.get(0), transaction10);
        assertEquals(result.get(5), transaction5);
        assertEquals(result.get(9), transaction1);
    }
}
