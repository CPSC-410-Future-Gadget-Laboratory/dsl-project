import React from 'react';
import './App.css';
import LogsBar from './components/LogsBar';
import InfoCard from './components/InfoCard';
import ForceGraph3D from 'react-force-graph-3d';


function App() {

  const logsData = {
    1: {
      id: 1,
      title: "REQUEST_RECEIVED",
      timestamp: "2012-11-10",
      requestId: "1",
    },
    2: {
      id: 1,
      title: "REQUEST_RECEIVED",
      timestamp: "2012-09-10",
      requestId: "5",
    },
    3: {
      id: 1,
      title: "RESPONSE_SENT",
      timestamp: "2012-09-10",
      requestId: "5",
    },
    4: {
      id: 1,
      title: "RESPONSE_SENT",
      timestamp: "2012-09-10",
      requestId: "5",
    },
  }

  const graphData = {
    "nodes": [ 
        { 
          "id": "1",
          "name": "name1",
          "val": 1 
        },
        { 
          "id": "2",
          "name": "name2",
        },
    ],
    "links": [
      {
        source: "1",
        target: "2",
      }
    ]
};

  return (
    <div className="App">
        <div className="side-bar">
          <LogsBar logs={logsData}/>
        </div>
        <ForceGraph3D graphData={graphData}/>
        <div className="info-card">
          <InfoCard />
        </div>
    </div>
  );
}

export default App;
