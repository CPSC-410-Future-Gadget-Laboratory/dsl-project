import React from 'react';
import './App.css';
import LogsBar from './components/LogsBar';
import InfoCard from './components/InfoCard';
import ForceGraph3D from 'react-force-graph-3d';
import { getNodeColorByType, buildGraphDataFromLogs } from './utils';
import { TYPE_ROOT, TYPE_ENDPOINT, TYPE_REQUEST } from './constants';


class App extends React.Component {

  constructor(props) {
    super(props);

    this.state = {
      ready: false,
      logs: {},
      active: undefined,
    }
  }

  componentDidMount() {
    fetch("/_logs").then(response => {
      response.text().then(dataText => {
        const data = JSON.parse(dataText);

        const logs = data.reduce((obj, datum) => {
          obj[datum.log_id] = {
            id: datum.log_id,
            endpointHitId: datum.id,
            url: datum.path,
            type: datum.type,
            endpointName: datum.path,
            logTime: datum.log_time,
            IPAddress: datum.client_ip,
            contentType: datum.content_type,
            statusCode: datum.status_code,
            message: datum.response_message,
          }

          return obj;
        }, {});

        this.setState({ logs });
      })
    });
  }

  render() {
    const logsData = {
      "1": {
        id: 1,
        type: "request",
        url: "/books/1",
        endpointName: "/books/{id}",
        timeRequested: "2019-11-10:00:10:10",
        timeResponded: "2019-11-10:00:10:10",
        IPAddress: "1023-12301203u-1230",
        contentType: "html/blahlbala",
        statusCode: 200,
        message: "Something cool.",
      },
      "2": {
        id: 2,
        type: "request",
        url: "/books/2",
        endpointName: "/books/{id}",
        timeRequested: "2019-11-10:00:10:10",
        timeResponded: "2019-11-10:00:10:10",
        IPAddress: "1023-12301203u-1230",
        contentType: "html/blahlbala",
        statusCode: 200,
        message: "Something cool.",
      },
      "3": {
        id: 3,
        type: "request",
        url: "/books",
        endpointName: "/books",
        timeRequested: "2019-11-10:00:10:10",
        timeResponded: "2019-11-10:00:10:10",
        IPAddress: "1023-12301203u-1230",
        contentType: "html/blahlbala",
        statusCode: 200,
        message: "Something cool.",
      },
      "4": {
        id: 3,
        type: "request",
        url: "/",
        endpointName: "/",
        timeRequested: "2019-11-10:00:10:10",
        timeResponded: "2019-11-10:00:10:10",
        IPAddress: "1023-12301203u-1230",
        contentType: "html/blahlbala",
        statusCode: 200,
        message: "Something cool.",
      },
    };

    const graphData = buildGraphDataFromLogs(this.state.logs);
    const currentlyActiveNode = graphData.nodes.find((node) => node.id === this.state.active);

    return (
      <div className="App">
        <div className="side-bar">
          <LogsBar logs={this.state.logs} />
        </div>
        <ForceGraph3D
          backgroundColor="black"
          graphData={graphData}
          onNodeClick={node => {
            this.setState({ active: node.id });
          }}
        />
        <div className="info-card">
          <InfoCard node={currentlyActiveNode} />
        </div>
      </div>
    );
  }
}

  export default App;
