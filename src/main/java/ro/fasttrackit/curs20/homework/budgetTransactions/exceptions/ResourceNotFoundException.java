package ro.fasttrackit.curs20.homework.budgetTransactions.exceptions;

public class ResourceNotFoundException extends RuntimeException {
    public ResourceNotFoundException(String msg) {
        super(msg);
    }
}
