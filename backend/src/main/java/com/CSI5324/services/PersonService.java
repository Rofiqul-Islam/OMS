package com.CSI5324.services;

import com.CSI5324.model.Person;
import com.CSI5324.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

@Service
public class PersonService {

    @Autowired
    PersonRepository userRepository;

    @PersistenceContext
    private EntityManager em;

    public Person registerPerson(Person user){
        return userRepository.save(user);
    }

    public Person updatePerson(Person user){
        System.out.println(user.getId());
        Person oldUser =   userRepository.findById(user.getId()).get();
        return userRepository.save(user);
    }
    public Person findPerson(Long id){
        try {
            Person user = userRepository.findById(id).get();
            return user;
        }catch (Exception e){
            return null;
        }
    }
    public List<Person> getAllPerson(){
        return (List<Person>)userRepository.findAll();
    }
    public void deletePerson(Long id){
        Person user =   userRepository.findById(id).get();
        userRepository.delete(user);
    }

    public Person login(String email, String password){
        try {
            Query query = em.createNamedQuery("Person.login").setParameter("email", email);
            query.setParameter("password", password);
            return (Person) query.getResultList().get(0);
        }catch (Exception e){
            return null;
        }
    }
}