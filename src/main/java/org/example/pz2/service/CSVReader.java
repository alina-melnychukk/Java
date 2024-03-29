package org.example.pz2.service;

import org.example.pz2.interfaces.ITransactionDataReader;
import org.example.pz2.model.Transaction;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

public class CSVReader implements ITransactionDataReader {

    @Override
    public List<Transaction> readTransaction(String filePath) {
        List<Transaction> transactions = new ArrayList<>();
        try {
            URI uri = new URI(filePath);

            try (BufferedReader br = new BufferedReader(new InputStreamReader(uri.toURL().openStream(), StandardCharsets.UTF_8))) {
                String line;
                while ((line = br.readLine()) != null) {
                    String[] values = line.split(",");
                    Transaction transaction = new Transaction(values[0], Double.parseDouble(values[1]), values[2]);
                    transactions.add(transaction);
                }
            }
        } catch (URISyntaxException | IOException e) {
            System.err.println(e);
        }

        return transactions;
    }
}
