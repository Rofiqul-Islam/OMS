package com.CSI5324.services;
import com.CSI5324.model.Edge;
import com.CSI5324.model.Node;
import com.CSI5324.model.Ontology;
import com.CSI5324.repository.EdgeRepository;
import com.CSI5324.repository.NodeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;


@Service
public class EdgeService {
    @Autowired
    EdgeRepository edgeRepository;

    @Autowired
    DAGService dagService;

    @Autowired
    OntologyService ontologyService;

    @Autowired
    NodeRepository nodeRepository;

    public Edge createEdge(Edge edge){
        if(!dagService.checkCycle(ontologyService.findById(edge.getOwnerOntologyId()),edge)) {
            Ontology owner = ontologyService.findById(edge.getOwnerOntologyId());
            edge = edgeRepository.save(edge);
            owner.getEdgeList().add(edge.getId());
            ontologyService.updateOntology(owner);
            return edge;
        }else{
            return null;
        }
    }

    public Iterable<Edge> findAllEdge(){
        return edgeRepository.findAll();
    }

    public Edge findById(Long id){
        try {
            return edgeRepository.findById(id).get();
        }catch (Exception e){
            return null;
        }
    }

    public Edge updateEdge(Edge edge){
        if(findById(edge.getId())!=null) {
            return edgeRepository.save(edge);
        }
        return null;
    }
    public void deleteById(Long id){
        Long ownerId  = edgeRepository.findById(id).get().getOwnerOntologyId();
        Ontology owner = ontologyService.findById(ownerId);
        owner.getEdgeList().remove(id);
        ontologyService.updateOntology(owner);
        edgeRepository.deleteById(id);
    }

}
