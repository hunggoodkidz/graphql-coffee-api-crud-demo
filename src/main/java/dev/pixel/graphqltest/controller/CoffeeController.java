package dev.pixel.graphqltest.controller;

import dev.pixel.graphqltest.model.Coffee;
import dev.pixel.graphqltest.service.CoffeeService;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import java.util.List;
import java.util.Optional;

@Controller
public class CoffeeController {

    private final CoffeeService coffeeService;

    public CoffeeController(CoffeeService coffeeService) {
        this.coffeeService = coffeeService;
    }

    @QueryMapping
    public List<Coffee> findAll() {
        return coffeeService.findAll();
    }

    @QueryMapping
    public Optional<Coffee> findOne(@Argument Integer id) {
        return coffeeService.findOne(id);
    }

    @MutationMapping
    public Coffee create(@Argument String name, @Argument int price,@Argument String desc) {
        return coffeeService.create(name,price,desc);
    }

    @MutationMapping
    public Coffee update(@Argument Integer id, @Argument String name,@Argument int price, @Argument String desc) {
        return coffeeService.update(id,name,price,desc);
    }

    @MutationMapping
    public Coffee delete(@Argument Integer id) {
        return coffeeService.delete(id);
    }
}