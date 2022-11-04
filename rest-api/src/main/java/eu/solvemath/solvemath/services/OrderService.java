package eu.solvemath.solvemath.services;

import eu.solvemath.solvemath.domain.Order;
import eu.solvemath.solvemath.repositories.OrderRepository;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
public class OrderService {

    private final OrderRepository repository;

    public OrderService(OrderRepository repository) {
        this.repository = repository;
    }

    public Set<Order> findAll(){
        Set<Order> orders = new HashSet<>();
        repository.findAll().iterator().forEachRemaining(orders::add);
        return orders;
    }

    public void add(Order order){
        repository.save(order);
    }
}
