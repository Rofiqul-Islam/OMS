package com.CSI5324.controller;

import com.CSI5324.model.Edge;
import com.CSI5324.model.Node;
import com.CSI5324.services.NodeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("/oms/node")
@RestController
public class NodeController {

    @Autowired
    NodeService nodeService;

    @PostMapping("/create")
    @ResponseBody
    public ResponseEntity<Node> createNewNode(@RequestBody Node node){
        Node registeredNode = nodeService.createNode(node);
        System.out.println(registeredNode.getId());
        return ResponseEntity.ok().body(registeredNode);
    }

    @GetMapping("/findAll")
    public Iterable<Node> findAllEdge(){
        Iterable<Node> list = nodeService.findAllNode();
        return list;
    }

    @GetMapping("/findById")
    public Node findById(@RequestParam Long id){
        return nodeService.findById(id);
    }

    @PostMapping("/update")
    @ResponseBody
    public ResponseEntity<Node> updateNode(@RequestBody Node node){
        Node registeredNode = nodeService.updateNode(node);
        if(registeredNode == null){
            return (ResponseEntity<Node>) ResponseEntity.notFound();
        }

        return ResponseEntity.ok().body(registeredNode);
    }

    @GetMapping("/deleteById")
    public ResponseEntity deleteById(@RequestParam Long id){
        nodeService.deleteById(id);
        return ResponseEntity.ok().body(null);
    }
}
