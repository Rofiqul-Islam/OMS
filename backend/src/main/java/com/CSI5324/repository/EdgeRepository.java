package com.CSI5324.repository;

import com.CSI5324.model.Edge;
import org.springframework.data.neo4j.repository.Neo4jRepository;

public interface EdgeRepository extends Neo4jRepository<Edge, Long> {

}
