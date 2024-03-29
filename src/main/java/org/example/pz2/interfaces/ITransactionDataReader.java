package org.example.pz2.interfaces;

import org.example.pz2.model.Transaction;

import java.util.List;

public interface ITransactionDataReader {

    List<Transaction> readTransaction(String filePath);
}
