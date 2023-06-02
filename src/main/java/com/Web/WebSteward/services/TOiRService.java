package com.Web.WebSteward.services;

import com.Web.WebSteward.models.Res;

import com.Web.WebSteward.models.TOiR;
import com.Web.WebSteward.repositores.TOiRRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
public class TOiRService {

    private final TOiRRepository TOiRrepository;

    public TOiRService(TOiRRepository tOiRrepository) {
        TOiRrepository = tOiRrepository;
    }

    @Autowired



    public List<TOiR> findAll() {
        return TOiRrepository.findAll();
    }

    public TOiR findOne(long id) {
        Optional<TOiR> foundPerson = TOiRrepository.findById(id);
        return foundPerson.orElse(null);
    }

    @Transactional
    public void save(TOiR TOiR) {
        TOiRrepository.save(TOiR);
    }

    @Transactional
    public void update(long id, TOiR updatedTOiR) {
        updatedTOiR.setId(id);
        TOiRrepository.save(updatedTOiR);
    }

    @Transactional
    public void delete(long id) {
        TOiRrepository.deleteById(id);
    }
}
