package com.Web.WebSteward.services;

import com.Web.WebSteward.interfaces.ResRepository;
import com.Web.WebSteward.models.Res;
import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.Date;
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

//    public Optional<Res> getResTitle(String title) {
//        return resRepository.FindByTitle(title);
//    }

//    public List<Book> getBooksByPersonId(int id) {
//        Optional<Person> person = peopleRepository.findById(id);
//
//        if (person.isPresent()) {
//            Hibernate.initialize(person.get().getBooks());
//            // Мы внизу итерируемся по книгам, поэтому они точно будут загружены, но на всякий случай
//            // не мешает всегда вызывать Hibernate.initialize()
//            // (на случай, например, если код в дальнейшем поменяется и итерация по книгам удалится)
//
//            // Проверка просроченности книг
//            person.get().getBooks().forEach(book -> {
//                long diffInMillies = Math.abs(book.getTakenAt().getTime() - new Date().getTime());
//                // 864000000 милисекунд = 10 суток
//                if (diffInMillies > 864000000)
//                    book.setExpired(true); // книга просрочена
//            });
//
//            return person.get().getBooks();
//        }
//        else {
//            return Collections.emptyList();
//        }
//    }
}
