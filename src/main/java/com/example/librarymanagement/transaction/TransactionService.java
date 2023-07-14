package com.example.librarymanagement.transaction;

import com.example.librarymanagement.common.client.BookServiceClient;
import com.example.librarymanagement.common.client.UserServiceClient;
import com.example.librarymanagement.common.model.Book;
import com.example.librarymanagement.common.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class TransactionService {

    @Autowired
    private TransactionRepository transactionRepository;

    @Autowired
    private BookServiceClient bookServiceClient;

    @Autowired
    private UserServiceClient userServiceClient;

    public Transaction borrowBook(Transaction transaction) {
        User user = userServiceClient.getUserById(transaction.getUserId());
        Book book = bookServiceClient.getBookById(transaction.getBookId());

        if (user == null && book == null) {
            throw new IllegalArgumentException("Invalid user or book ID");
        }

        if (book.isAvailable()) {
            Transaction transaction1 = Transaction.builder()
                    .id(UUID.randomUUID().toString().replace("-", ""))
                    .bookId(transaction.getBookId())
                    .userId(transaction.getUserId())
                    .transactionDate(LocalDate.now())
                    .build();

            book.setAvailable(false);
            bookServiceClient.updateBook(transaction.getBookId(), book);
            return transactionRepository.save(transaction1);
        } else {
            throw new IllegalStateException("Book is not available for borrowing");
        }
    }

    public List<Transaction> getAllTransactions(Long userId, Long bookId) {

        if (userId != null && bookId != null) {
            return transactionRepository.findTransactionsByUserIdAndBookId(userId, bookId);
        } else if (userId != null) {
            return transactionRepository.findTransactionsByUserId(userId);
        } else if (bookId != null) {
            return transactionRepository.findTransactionsByBookId(bookId);
        } else {
            return transactionRepository.findAll();
        }
    }

    public Transaction getTransactionById(String id) {
        Optional<Transaction> transaction = transactionRepository.findById(id);
        return transaction.orElse(null);
    }
}
