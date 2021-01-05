import { useReadCypher } from 'use-neo4j'

function Neo4jDriver() {
    const { loading, first, cypher } = useReadCypher('MATCH (n) RETURN count(n) AS count')
    const count = first && first.get('count').toNumber()
  
    return (
      <div>
        
  
          <pre>{cypher}</pre>
  
          { loading && <p>Loading...</p> }
          { !loading && <p>There are {count} nodes in the database</p> }
  
        
      </div>
    );
  }
  
  export default Neo4jDriver;
  
