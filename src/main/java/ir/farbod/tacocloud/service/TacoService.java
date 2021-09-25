package ir.farbod.tacocloud.service;

import ir.farbod.tacocloud.entity.Taco;
import ir.farbod.tacocloud.repository.TacoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TacoService {

    private TacoRepository tacoRepository;

    @Autowired
    public TacoService(TacoRepository tacoRepository) {
        this.tacoRepository = tacoRepository;
    }

    public Taco get(Long id){
        return tacoRepository.getById(id);
    }

    public Taco save(Taco entity){
        return tacoRepository.save(entity);
    }

}
