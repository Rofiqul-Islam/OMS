package com.CSI5324.services;

import com.CSI5324.model.Node;
import com.CSI5324.repository.NodeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class NodeService {

    @Autowired
    NodeRepository nodeRepository;

    public Node createNode(Node node){
        return nodeRepository.save(node);
    }

    public Node updateNode(Node node){
        if(findById(node.getId())!=null) {
            return nodeRepository.save(node);
        }
        return null;
    }

    public Node findById(Long id){
        try {
            return nodeRepository.findById(id).get();
        }
        catch (Exception e){
            return null;
        }
    }

    public Iterable<Node> findAllNode(){
        return nodeRepository.findAll();
    }

    public void deleteById(Long id){
        nodeRepository.deleteById(id);
    }
}
