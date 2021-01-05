package com.CSI5324.services;

import com.CSI5324.model.*;
import com.CSI5324.repository.EdgeRepository;
import com.CSI5324.repository.NodeRepository;
import com.CSI5324.repository.OntologyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.neo4j.annotation.Depth;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.util.LinkedList;

@Service
public class OntologyService {
    @Autowired
    OntologyRepository ontologyRepository;

    @Autowired
    NodeService nodeService;

    @Autowired
    EdgeService edgeService;

    @Autowired
    PersonService personService;

    public Ontology createOntology(Ontology ontology){
        ontology.setCreateTime(String.valueOf(new Timestamp(System.currentTimeMillis())));
        System.out.println(ontology.getCreateTime());
        System.out.println(ontology);
        return ontologyRepository.save(ontology);
    }


    public Ontology findById(Long id){
        try {
            return ontologyRepository.findById(id).get();
        }catch (Exception e){
            return null;
        }
    }

    public Iterable<Ontology> findAllOntolology(){
        return ontologyRepository.findAll();
    }

    public Ontology updateOntology(Ontology ontology){
        if(findById(ontology.getId())!=null) {
            return ontologyRepository.save(ontology);
        }
        return null;
    }
    public void deleteById(Long id){
        ontologyRepository.deleteById(id);
    }

    public void populate(){
        Person person1 = new Person();
        person1.setName("testUser");
        person1.setEmail("acfe@gmail.com");
        person1.setPassword("test123");
        person1.setDob(Date.valueOf("2002-12-12"));
        person1.setPhoneNumber("0156456789");
        person1 = personService.registerPerson(person1);

        Ontology ontology1 = new Ontology();
        ontology1.setCreatedBy(person1.getId());
        ontology1.setName("testOntology");
        ontology1.setCreateTime("2020-12-12");
        ontology1.setNodeList(new LinkedList<Long>());
        ontology1.setEdgeList(new LinkedList<Long>());
        ontology1 = ontologyRepository.save(ontology1);

        Node node1 = new Node();
        node1.setName("Operational Policies");
        node1.setNodeCategory(NodeCategory.BuildingComponent);
        node1 = nodeService.createNode(node1);
        System.out.println(node1.getId());
        ontology1.getNodeList().add(node1.getId());

        Node node2 = new Node();
        node2.setName("Nutrition");
        node2.setNodeCategory(NodeCategory.HealthConcern);
        node2 = nodeService.createNode(node2);
        ontology1.getNodeList().add(node2.getId());

        Edge edge1 = new Edge();
        edge1.setFromNode(node1);
        edge1.setToNode(node2);
        edge1.setEdgeCategory(EdgeCategory.IS_A);
        edge1.setOwnerOntologyId(ontology1.getId());
        edge1=edgeService.createEdge(edge1);
        ontology1.getEdgeList().add(edge1.getId());

        Node node3 = new Node();
        node3.setName("Nutritional Labeling");
        node3.setNodeCategory(NodeCategory.Nutrition);
        node3 = nodeService.createNode(node3);
        ontology1.getNodeList().add(node3.getId());

        Edge edge2 = new Edge();
        edge2.setFromNode(node2);
        edge2.setToNode(node3);
        edge2.setEdgeCategory(EdgeCategory.IS_A);
        edge2.setOwnerOntologyId(ontology1.getId());
        edge2=edgeService.createEdge(edge2);
        ontology1.getEdgeList().add(edge2.getId());

        Node node4 = new Node();
        node4.setName("Physical Activity");
        node4.setNodeCategory(NodeCategory.HealthConcern);
        node4 = nodeService.createNode(node4);
        ontology1.getNodeList().add(node4.getId());

        Edge edge3 = new Edge();
        edge3.setFromNode(node1);
        edge3.setToNode(node4);
        edge3.setEdgeCategory(EdgeCategory.IS_A);
        edge3.setOwnerOntologyId(ontology1.getId());
        edge3=edgeService.createEdge(edge3);
        ontology1.getEdgeList().add(edge3.getId());

        Node node5 = new Node();
        node5.setName("Encourage Use of Vertical Circulaiton");
        node5.setNodeCategory(NodeCategory.HealthConcern);
        node5 = nodeService.createNode(node5);
        ontology1.getNodeList().add(node5.getId());

        Edge edge4 = new Edge();
        edge4.setFromNode(node4);
        edge4.setToNode(node5);
        edge4.setEdgeCategory(EdgeCategory.IS_A);
        edge4.setOwnerOntologyId(ontology1.getId());
        edge4=edgeService.createEdge(edge4);
        ontology1.getEdgeList().add(edge4.getId());

        Node node6 = new Node();
        node6.setName("Use of Step Counter");
        node6.setNodeCategory(NodeCategory.HealthConcern);
        node6 = nodeService.createNode(node6);
        ontology1.getNodeList().add(node6.getId());

        Edge edge5 = new Edge();
        edge5.setFromNode(node5);
        edge5.setToNode(node6);
        edge5.setEdgeCategory(EdgeCategory.IS_A);
        edge5.setOwnerOntologyId(ontology1.getId());
        edge5=edgeService.createEdge(edge5);
        ontology1.getEdgeList().add(edge5.getId());

        Edge edge6 = new Edge();
        edge6.setFromNode(node4);
        edge6.setToNode(node6);
        edge6.setEdgeCategory(EdgeCategory.IS_A);
        edge6.setOwnerOntologyId(ontology1.getId());
        edge6=edgeService.createEdge(edge6);
        ontology1.getEdgeList().add(edge6.getId());

        ontology1 = ontologyRepository.save(ontology1);

    }
}
