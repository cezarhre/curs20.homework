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
        if (type != null) {
            return repository.findByType(type);
        } else if (maxAmount != 0) {
            return repository.findByMaxAmount(maxAmount);
        } else if (minAmount != 0) {
            return repository.findByMinAmount(minAmount);
        } else if (minAmount != 0 && maxAmount != 0) {
            return repository.findByAmountBetween(minAmount, maxAmount);
        } else if (type != null && maxAmount != 0) {
            return repository.findByTypeMaxAmount(type, maxAmount);
        } else if (type != null && minAmount != 0) {
            return repository.findByTypeMinAmount(type, minAmount);
        } else if (type != null && minAmount != 0 && maxAmount != 0) {
            return repository.findByTypeMinAmountMaxAmount(type, minAmount, maxAmount);
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

    public Map<TransactionType, Double> sumByType(TransactionType sumType) {
        return repository.findAll().stream()
                .filter(trans -> trans.getType() == null || trans.getType().equals(sumType))
                .collect(Collectors.groupingBy(Transaction::getType, Collectors.summingDouble(Transaction::getAmount)));
    }

    public Map<String, Double> sumByProduct(String sumProd) {
        return repository.findAll().stream()
                .filter(trans -> trans.getProduct().equalsIgnoreCase(sumProd))
                .collect(Collectors.groupingBy(Transaction::getProduct, Collectors.summingDouble(Transaction::getAmount)));
    }
}
