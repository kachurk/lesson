package ru.ssu.dto;


public class TransactionDto {

    private Long userId;
    private Long itemId;
    private TransactionStatus transactionStatus;
    private String message;

    public TransactionDto() {
    }

    public TransactionDto(Long userId, Long itemId) {
        this.userId = userId;
        this.itemId = itemId;
    }

    public TransactionDto(Long userId, Long itemId, TransactionStatus transactionStatus) {
        this.userId = userId;
        this.itemId = itemId;
        this.transactionStatus = transactionStatus;
    }

    public TransactionDto(Long userId, Long itemId, TransactionStatus transactionStatus, String message) {
        this.userId = userId;
        this.itemId = itemId;
        this.transactionStatus = transactionStatus;
        this.message = message;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getItemId() {
        return itemId;
    }

    public void setItemId(Long itemId) {
        this.itemId = itemId;
    }

    public TransactionStatus getTransactionStatus() {
        return transactionStatus;
    }

    public void setTransactionStatus(TransactionStatus transactionStatus) {
        this.transactionStatus = transactionStatus;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
