package ro.fasttrackit.curs20.homework.budgetTransactions.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ro.fasttrackit.curs20.homework.budgetTransactions.entity.Transaction;
import ro.fasttrackit.curs20.homework.budgetTransactions.entity.TransactionType;
import java.util.List;

public interface TransactionRepository extends JpaRepository<Transaction, Integer> {
    List<Transaction> findByType(TransactionType type);
    List<Transaction> findByMinAmount(double minAmount);
    List<Transaction> findByMaxAmount(double maxAmount);
    List<Transaction> findByTypeMinAmount(TransactionType type, double minAmount);
    List<Transaction> findByTypeMaxAmount(TransactionType type, double maxAmount);
    List<Transaction> findByAmountBetween(double minAmount, double maxAmount);
    List<Transaction> findByTypeMinAmountMaxAmount(TransactionType type, double minAmount, double maxAmount);
}
