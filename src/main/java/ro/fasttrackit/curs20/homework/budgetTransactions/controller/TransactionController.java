package ro.fasttrackit.curs20.homework.budgetTransactions.controller;

import org.springframework.web.bind.annotation.*;
import ro.fasttrackit.curs20.homework.budgetTransactions.entity.Transaction;
import ro.fasttrackit.curs20.homework.budgetTransactions.entity.TransactionType;
import ro.fasttrackit.curs20.homework.budgetTransactions.service.TransactionService;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("transactions")
public class TransactionController {

    private final TransactionService service;

    public TransactionController(TransactionService service){
        this.service = service;
    }

    @GetMapping
    List<Transaction> filterAll(
            @RequestParam(required = false) TransactionType type,
            @RequestParam(defaultValue = "0") double minAmount,
            @RequestParam(defaultValue = "0") double maxAmount
    ){
        return service.filterAll(type, minAmount, maxAmount);
    }

    @GetMapping("/{id}")
    Transaction byId(@PathVariable int id){
        return service.byId(id);
    }

    @GetMapping(value = "/reports",params = "type")
    Map<TransactionType, List<Transaction>> orderByType(){
        return service.orderByType();
    }

    @GetMapping(value = "/reports",params = "prod")
    Map<String, List<Transaction>> sumByProduct(){
        return service.orderByProduct();
    }

    @PostMapping
    Transaction addProduct(@RequestBody Transaction product){
        return service.addProduct(product);
    }

    @PutMapping("/{id}")
    Transaction updateProduct(@PathVariable int id,
                              @RequestParam Transaction trans){
        return service.updateProduct(id, trans);
    }

    @DeleteMapping("/{id}")
    Optional<Transaction> deleteById(@PathVariable int id){
        return service.deleteById(id);
    }
}
