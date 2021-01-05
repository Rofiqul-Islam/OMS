package com.CSI5324.controller;

import com.CSI5324.model.Ontology;
import com.CSI5324.model.Person;
import com.CSI5324.services.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping(value = "/oms/person")
@RestController
public class PersonController {
    @Autowired
    private PersonService personService;

    @PostMapping(path="/register",consumes = "application/json", produces="application/json")
    @ResponseBody
    public ResponseEntity<Person> registerperson(@RequestBody Person person) {
        Person registeredperson = personService.registerPerson(person);
        return ResponseEntity.ok().body(registeredperson);
    }


    @GetMapping("/findById")
    public Person findById(@RequestParam Long id){
        return personService.findPerson(id);
    }

    @PostMapping(path="/update", consumes = "application/json", produces = "application/json")
    @ResponseBody
    public ResponseEntity<Person> updateStudent(@RequestBody Person person) {
        personService.updatePerson(person);
        return ResponseEntity.ok().body(person);
    }

    @GetMapping(path="/all", produces="application/json")
    public ResponseEntity<List<Person>> getAllperson(){
        List<Person> personList =  personService.getAllPerson();
        return ResponseEntity.ok().body(personList);
    }

    @GetMapping("/deleteById")
    public ResponseEntity deleteById(@RequestParam Long id){
        personService.deletePerson(id);
        return ResponseEntity.ok().body(null);
    }
    @GetMapping("/login")
    public ResponseEntity login(@RequestParam String email, @RequestParam String password){
        Person person =  personService.login(email,password);
        return ResponseEntity.ok().body(person);
    }
}
