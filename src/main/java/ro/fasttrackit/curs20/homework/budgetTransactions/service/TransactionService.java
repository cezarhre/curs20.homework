package ro.fasttrackit.curs20.homework.budgetTransactions.service;

import org.springframework.stereotype.Service;
import ro.fasttrackit.curs20.homework.budgetTransactions.entity.Transaction;
import ro.fasttrackit.curs20.homework.budgetTransactions.entity.TransactionType;
import ro.fasttrackit.curs20.homework.budgetTransactions.exceptions.ResourceNotFoundException;
import ro.fasttrackit.curs20.homework.budgetTransactions.repository.TransactionRepository;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class TransactionService {

    private final TransactionRepository repository;

    public TransactionService(TransactionRepository repository) {
        this.repository = repository;
    }

    public List<Transaction> filterAll(TransactionType type, double minAmount, double maxAmount) {
        if (type != null & minAmount != 0 & maxAmount != 0) {
            return repository.findByTypeAndAmountBetween(type, minAmount, maxAmount);
        } else if (minAmount != 0 & maxAmount != 0) {
            return repository.findByAmountBetween(minAmount, maxAmount);
        } else if (type != null & maxAmount != 0) {
            return repository.findByTypeAndAmountLessThan(type, maxAmount);
        } else if (type != null & minAmount != 0) {
            return repository.findByTypeAndAmountGreaterThan(type, minAmount);
        } else if (maxAmount != 0) {
            return repository.findByAmountLessThan(maxAmount);
        } else if (minAmount != 0) {
            return repository.findByAmountGreaterThan(minAmount);
        } else if (type != null) {
            return repository.findByType(type);
        } else {
            return repository.findAll();
        }
    }

    public Transaction byId(int id) {
        return repository.findById(id)
                .orElseThrow();
    }

    public Transaction addProduct(Transaction product) {
        return repository.save(product);
    }

    public Transaction updateProduct(int id, Transaction trans) {
        Transaction prod = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Not found" + id));
        prod.setProduct(trans.getProduct());
        prod.setType(trans.getType());
        prod.setAmount(trans.getAmount());
        return repository.save(prod);
    }

    public Optional<Transaction> deleteById(int id) {
        Optional<Transaction> prod = repository.findById(id);
        repository.deleteById(id);
        return prod;
    }

    public Map<TransactionType, List<Transaction>> orderByType() {
        return repository.findAll().stream()
                .collect(Collectors.groupingBy(Transaction::getType));
    }

    public Map<String, List<Transaction>> orderByProduct() {
        return repository.findAll().stream()
                .collect(Collectors.groupingBy(Transaction::getProduct));
    }
}
