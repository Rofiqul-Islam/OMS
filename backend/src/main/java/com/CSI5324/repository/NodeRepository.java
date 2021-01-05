package com.CSI5324.repository;

import com.CSI5324.model.Node;
import org.springframework.data.neo4j.repository.Neo4jRepository;

import java.util.Optional;

public interface NodeRepository extends Neo4jRepository<Node, Long> {

    Optional<Node> findById(Long id);

}
