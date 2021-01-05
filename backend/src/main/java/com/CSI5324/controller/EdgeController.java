package com.CSI5324.controller;

import com.CSI5324.model.Edge;
import com.CSI5324.model.Node;
import com.CSI5324.services.EdgeService;
import com.CSI5324.services.NodeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("/oms/edge")
@RestController
public class EdgeController {
    @Autowired
    EdgeService edgeService;

    @Autowired
    NodeService nodeService;

    @PostMapping("/create")
    @ResponseBody
    public ResponseEntity<Edge> createNewEdge(@RequestBody Edge edge){
        return ResponseEntity.ok().body(edgeService.createEdge(edge));
    }

    @GetMapping("/findAll")
    public Iterable<Edge> findAllEdge(){
        return edgeService.findAllEdge();
    }

    @GetMapping("/findById")
    public Edge findById(@RequestParam Long id){
        return edgeService.findById(id);
    }

    @PostMapping("/update")
    @ResponseBody
    public ResponseEntity<Edge> updateEdge(@RequestBody Edge edge){
        Edge updatedEdge = edgeService.updateEdge(edge);
        if(updatedEdge == null){
            return (ResponseEntity<Edge>) ResponseEntity.notFound();
        }

        return ResponseEntity.ok().body(updatedEdge);
    }

    @GetMapping("/deleteById")
    public ResponseEntity deleteById(@RequestParam Long id){
        edgeService.deleteById(id);
        return ResponseEntity.ok().body(null);
    }


}
