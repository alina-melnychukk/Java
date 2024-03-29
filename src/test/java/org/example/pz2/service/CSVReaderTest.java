package org.example.pz2.service;

import org.example.pz2.model.Transaction;
import org.example.pz2.service.CSVReader;
import org.junit.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class CSVReaderTest {

    @Test
    public void testReadTransaction() {
        CSVReader reader = new CSVReader();
        String filePath = "https://informer.com.ua/dut/java/pr2.csv";
        List<Transaction> transactions = reader.readTransaction(filePath);

        assertNotNull(transactions);
        assertEquals(26, transactions.size());

        Transaction firstTransaction = transactions.get(0);
        Transaction secondTransaction = transactions.get(1);
        Transaction thirdTransaction = transactions.get(2);
        Transaction lastTransaction = transactions.get(25);


        assertEquals("05-12-2023", firstTransaction.getDate());
        assertEquals(-7850, firstTransaction.getAmount());
        assertEquals("Сільпо", firstTransaction.getDescription());

        assertEquals("07-12-2023", secondTransaction.getDate());
        assertEquals(-1200.0, secondTransaction.getAmount());
        assertEquals("Аптека", secondTransaction.getDescription());

        assertEquals("10-12-2023", thirdTransaction.getDate());
        assertEquals(80000.0, thirdTransaction.getAmount());
        assertEquals("Зарплата", thirdTransaction.getDescription());

        assertEquals("30-01-2024", lastTransaction.getDate());
        assertEquals(-9100, lastTransaction.getAmount());
        assertEquals("Інші витрати", lastTransaction.getDescription());
    }
}
