package dev.pixel.graphqltest.service;

import dev.pixel.graphqltest.model.Coffee;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicInteger;

@Service
public class CoffeeService {

    private List<Coffee> coffees = new ArrayList<>();
    AtomicInteger id = new AtomicInteger(0);

    public List<Coffee> findAll() {
        return coffees;
    }

    public Optional<Coffee> findOne(Integer id) {
        return coffees.stream().filter(coffee -> coffee.id() == id).findFirst();
    }

    public Coffee create(String name,double price, String desc) {
        Coffee coffee = new Coffee(id.incrementAndGet(), name,price,desc);
        coffees.add(coffee);
        return coffee;
    }

    public Coffee update(Integer id, String name,double price, String desc) {
        Coffee updatedCoffee = new Coffee(id, name,price, desc);
        Optional<Coffee> optional = coffees.stream().filter(c -> c.id() == id).findFirst();
        if (optional.isPresent()) {
            Coffee coffee = optional.get();
            int index = coffees.indexOf(coffee);
            coffees.set(index, updatedCoffee);
        } else {
            throw new IllegalArgumentException("Invalid coffee");
        }
        return updatedCoffee;
    }

    public Coffee delete(Integer id) {
        Coffee coffee = coffees.stream().filter(c -> c.id() == id)
                .findFirst().orElseThrow(() -> new IllegalArgumentException());
        coffees.remove(coffee);
        return coffee;
    }

    @PostConstruct
    private void init() {
        coffees.add(new Coffee(id.incrementAndGet(), "Cafe Phin Sữa Đá",25000, "Cà phê"));
        coffees.add(new Coffee(id.incrementAndGet(), "Cafe Phin Truyền Thống",20000, "Cà phê"));
        coffees.add(new Coffee(id.incrementAndGet(), "Matcha Đá Xay", 3000,"Đá xay"));
    }
}