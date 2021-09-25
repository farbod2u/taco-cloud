package ir.farbod.tacocloud.service;

import ir.farbod.tacocloud.entity.Ingredient;
import ir.farbod.tacocloud.repository.IngredientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class IngredientService {

    private IngredientRepository repository;

    @Autowired
    public IngredientService(IngredientRepository repository) {
        this.repository = repository;
    }

    public Ingredient get(String id){
        return repository.getById(id);
    }

    public List<Ingredient> getAll(){
        return repository.findAll();
    }

}
