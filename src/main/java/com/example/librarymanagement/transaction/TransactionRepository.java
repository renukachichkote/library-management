package com.example.librarymanagement.transaction;

import org.springframework.data.couchbase.repository.CouchbaseRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TransactionRepository extends CouchbaseRepository<Transaction, String> {

    List<Transaction> findTransactionsByUserIdAndBookId(Long userId, Long bookId);

    List<Transaction> findTransactionsByUserId(Long userId);

    List<Transaction> findTransactionsByBookId(Long bookId);
}
