package com.example.librarymanagement.transaction;

import jakarta.annotation.Nonnull;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;

@Data
@Builder
public class Transaction {

    private String id;

    @Nonnull
    private Long userId;

    @Nonnull
    private Long bookId;

    private LocalDate transactionDate;

}
