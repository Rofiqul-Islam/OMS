package com.CSI5324.controller;

import com.CSI5324.model.Node;
import com.CSI5324.model.Ontology;
import com.CSI5324.services.DAGService;
import com.CSI5324.services.OntologyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("/oms/ontology")
@RestController
public class OntologyController {

    @Autowired
    OntologyService ontologyService;

    @Autowired
    DAGService dagService;

    @PostMapping("/create")
    @ResponseBody
    public ResponseEntity<Ontology> createNewOntology(@RequestBody Ontology ontology){
        Ontology newOntology = ontologyService.createOntology(ontology);
        return ResponseEntity.ok().body(newOntology);
    }

    @GetMapping("/findById")
    public Ontology findById(@RequestParam Long id){
        return ontologyService.findById(id);
    }

    @GetMapping("/findAll")
    public Iterable<Ontology> findAllOntology(){
        return ontologyService.findAllOntolology();
    }

    @PostMapping("/update")
    @ResponseBody
    public ResponseEntity<Ontology> updateOntology(@RequestBody Ontology ontology){
        Ontology updatedOntology = ontologyService.updateOntology(ontology);
        if(updatedOntology == null){
            return (ResponseEntity<Ontology>) ResponseEntity.notFound();
        }

        return ResponseEntity.ok().body(updatedOntology);
    }

    @GetMapping("/deleteById")
    public ResponseEntity deleteById(@RequestParam Long id){
        ontologyService.deleteById(id);
        return ResponseEntity.ok().body(null);
    }

    @GetMapping("/populate")
    public ResponseEntity populate(){
        ontologyService.populate();
        return ResponseEntity.ok().body(null);
    }

    @GetMapping("/check")
    public ResponseEntity check(@RequestParam Long id){
        return ResponseEntity.ok().body(dagService.createAdgencyMatrix(ontologyService.findById(id)));
    }

}
