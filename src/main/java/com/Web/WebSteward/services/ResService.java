package com.Web.WebSteward.services;

import com.Web.WebSteward.repositores.ResRepository;
import com.Web.WebSteward.models.Res;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
public class ResService {

    private final ResRepository resRepository;

    @Autowired
    public ResService(ResRepository resRepository) {
        this.resRepository = resRepository;
    }


    public List<Res> findAll() {
        return resRepository.findAll();
    }

    public Res findOne(int id) {
        Optional<Res> foundPerson = resRepository.findById(id);
        return foundPerson.orElse(null);
    }

    @Transactional
    public void save(Res res) {
        resRepository.save(res);
    }

    @Transactional
    public void update(int id, Res updatedRes) {
        updatedRes.setId(id);
        resRepository.save(updatedRes);
    }

    @Transactional
    public void delete(int id) {
        resRepository.deleteById(id);
    }
}
