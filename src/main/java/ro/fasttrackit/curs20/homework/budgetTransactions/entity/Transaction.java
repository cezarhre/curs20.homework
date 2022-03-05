package ro.fasttrackit.curs20.homework.budgetTransactions.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Transaction {
    @Id
    @GeneratedValue
    private int id;
    private String product;
    private TransactionType type;
    private double amount;

    public Transaction() {
    }

    public Transaction(String product, TransactionType type, double amount) {
        this.product = product;
        this.type = type;
        this.amount = amount;
    }

    public TransactionType getType() {
        return type;
    }

    public String getProduct() {
        return product;
    }

    public double getAmount() {
        return amount;
    }

    public int getId() {
        return id;
    }

    public void setProduct(String product) {
        this.product = product;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public void setType(TransactionType type) {
        this.type = type;
    }

    public void setId(int id) {
        this.id = id;
    }
}