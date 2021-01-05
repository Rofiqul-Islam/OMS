package com.CSI5324.repository;

import com.CSI5324.model.Ontology;
import org.springframework.data.neo4j.annotation.Depth;
import org.springframework.data.neo4j.repository.Neo4jRepository;

import java.util.Optional;

public interface OntologyRepository extends Neo4jRepository<Ontology, Long> {
    @Depth(value = 2)
    Optional<Ontology> findById(Long id);
}
