import logo from './logo.svg';
import './App.css';
import Neo4jDriver from './components/Neo4jDriver'
import axios from 'axios'
import OntologyList from './components/OntologyList';

function App() {

  return (
    <div className="App">
      <header className="App-header">
          <OntologyList>
            
          </OntologyList>
      </header>
    </div>
  );
}

export default App;

