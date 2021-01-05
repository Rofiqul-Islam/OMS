import React from 'react';
import ReactFlow, { ArrowHeadType } from 'react-flow-renderer';
import axios from 'axios';

const elements = [
    { id: '1', data: { label: 'Node 1' }, position: { x: 225, y: 5 } },
    { id: '2', data: { label: 'Node 2' }, position: { x: 100, y: 100 } },
    { id: 'e1-2', source: '1', target: '2', animated: true },
  ];
const graphStyles = { width: '100%', height: '1000px' };
const BasicGraph = () => <ReactFlow elements={elements} style={graphStyles} />;

{/* <ul>
        { this.state.ontologies.map(ontology => <li>{ontology.name}</li>)}
        {BasicGraph}
      </ul> */}

export default class OntologyList extends React.Component {
  state = {
    values: []
  }

  componentDidMount() {
    axios.get(`http://localhost:8081/oms/edge/findAll`)
      .then(res => {
        const edges = res.data;
        
        const nodes = [];
        let direction = 20;
        for (let i = 0; i < edges.length; i++) {


            const fromNode = {
                id: edges[i].fromNode.id.toString(),
                data: { label: edges[i].fromNode.name},
                position: { x: 250, y: 250 } 
            }

            if (!nodes.some(item => fromNode.id === item.id)) {
                nodes.push(fromNode);
            }
            
            const toNode = {
                id: edges[i].toNode.id.toString(),
                data: { label: edges[i].toNode. name},
                position: { x: 250, y: 250 } 
            }
            

            if (!nodes.some(item => toNode.id === item.id)) {
                nodes.push(toNode);
            }
           
            nodes.push({
                id: edges[i].fromNode.id.toString() + '_' + edges[i].toNode.id.toString(),
                source: edges[i].fromNode.id,
                target: edges[i].toNode.id,
                animated: false,
                label: edges[i].edgeCategory,
                arrowHeadType: ArrowHeadType.ArrowClosed
            });
           
        }

        console.log(nodes)

        for (let j = 0; j < nodes.length; j++) {

            let model = nodes[j];
            if(!model.id.includes('_')) {
                direction += 100;
                model.position.x = direction;
                model.position.y = direction;
            }
            
        }

        this.setState({values : nodes});
        console.log(this.state.values)
        console.log(nodes);
        
      })
  }

  render() {
    return (

        <ReactFlow elements={this.state.values} style={graphStyles} />

    )
  }
}