package com.CSI5324.services;

import com.CSI5324.model.Edge;
import com.CSI5324.model.Ontology;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class DAGService {
    //public static int[][] arr = new int[1000][1000];

    @Autowired
    EdgeService edgeService;

    public Map<Long,ArrayList<Long>> createAdgencyMatrix(Ontology ontology){
        Map<Long,ArrayList<Long>> map = new HashMap<>();
        List<Long> edgeIdList = ontology.getEdgeList();
        List<Edge> edgeList = new LinkedList<>();
        for(Long id:edgeIdList){
            System.out.println(id);
            edgeList.add(edgeService.findById(id));
        }
        for(Edge e: edgeList){
            System.out.println(e.getFromNode().getId()+" -> "+e.getToNode().getId());
            if(map.get(e.getFromNode().getId()) == null){
                ArrayList<Long> temp = new ArrayList<>();
                temp.add(e.getToNode().getId());
                map.put(e.getFromNode().getId(),temp);
            }
            else{
                ArrayList<Long> temp = map.get(e.getFromNode().getId());
                temp.add(e.getToNode().getId());
                map.put(e.getFromNode().getId(),temp);
            }

        }
        return map;
    }

    public boolean checkCycle(Ontology ontology, Edge edge){
        Map<Long,ArrayList<Long>> graph = createAdgencyMatrix(ontology);
        int x = dfs(graph,edge.getToNode().getId(),edge.getFromNode().getId());
        if(x==1){
            System.out.println("cycle found");
            return true;
        }else{
            System.out.println("cycle not found");
            return false;
        }
    }

    public int dfs(Map<Long,ArrayList<Long>> graph,Long start, Long end){
        int f =0;
        System.out.println(start+" , "+end);
        if(start == end){
            return 1;
        }
        if(graph.get(start)!=null){
            for(Long id:graph.get(start)){
               f = dfs(graph,id,end);
               if(f ==1){
                   return f;
               }
            }
        }
        return f;
    }
}
