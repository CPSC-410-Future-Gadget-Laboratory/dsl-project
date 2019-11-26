import React from 'react';
import { Button, Card, Header, Divider, List } from 'semantic-ui-react';
import Chart from 'react-google-charts';
import { TYPE_ROOT, TYPE_ENDPOINT, TYPE_REQUEST } from '../constants';
import {buildGraphDataFromLogs, getTraffic} from "../utils";

export default class InfoCard extends React.Component {

    renderServerCard() {
      const chartData = getTraffic(this.props.logs);
        return (
            <Card
                fluid
                className="info-card-card"
                style={{
                    opacity: 0.8
                }}
            >
                <Card.Content
                    header="Server"
                    description="Server"
                />
                <Card.Content style={{
                    overflowY: "scroll",
                }}>
                    <p><strong>No of Endpoints:</strong></p>
                    <p><strong>Request per Minute:</strong> </p>
                    <p><strong>Total Compute Time:</strong> </p>
                    <p><strong>Errors per Minute:</strong> </p>
                    <p><strong>No. of unique IP Address:</strong> </p>
                    <p><strong>2XX:</strong> </p>
                    <p><strong> - 200:</strong> </p>
                    <p><strong> - 204:</strong> </p>
                    <p><strong>4XX:</strong> </p>
                    <p><strong> - 404:</strong> </p>
                    <Header>
                        Traffic
                    </Header>
                    <Divider />
                    <Chart
                        width="340px"
                        height="300px"
                        chartType="LineChart"
                        data={chartData}
                        options={{
                            hAxis: {
                                title: 'Time',
                            },
                            vAxis: {
                                title: 'Traffic',
                            },
                        }}
                    />
                </Card.Content>
            </Card>
        )
    }

    renderEndpointCard() {
      const endpointName = this.props.node.id;
      const chartData = getTraffic(this.props.logs, endpointName);
        return (
            <Card
                fluid
                className="info-card-card"
                style={{
                    opacity: 0.8
                }}
            >
                <Card.Content
                    header="/books/{bookID}/user/{userID}"
                    description="ENDPOINT"
                />
                <Card.Content style={{
                    overflowY: "scroll",
                }}>
                    <p><strong>Pathname:</strong> /books/1/user/123</p>
                    <p><strong>Request per Minute:</strong> </p>
                    <p><strong>Average Latency:</strong> </p>
                    <p><strong>Errors per Minute:</strong> </p>
                    <p><strong>No. of unique IP Address:</strong> </p>
                    <p><strong>2XX:</strong> </p>
                    <p><strong> - 200:</strong> </p>
                    <p><strong> - 204:</strong> </p>
                    <p><strong>4XX:</strong> </p>
                    <p><strong> - 404:</strong> </p>
                    <Header>
                        Traffic
                    </Header>
                    <Divider />
                    <Chart
                        width="340px"
                        height="300px"
                        chartType="LineChart"
                        data={chartData}
                        options={{
                            hAxis: {
                                title: 'Time',
                            },
                            vAxis: {
                                title: 'Traffic',
                            },
                        }}
                    />
                </Card.Content>
            </Card>
        )
    }

    renderHitCard() {
        const hit = this.props.node;
        return (
            <Card
                fluid
                className="info-card-card"
                style={{
                    opacity: 0.8
                }}
            >
                <Card.Content
                    header="Hit"
                    description="ENDPOINT HIT"
                />
                <Card.Content style={{
                    overflowY: "scroll",
                }}>
                    <p><strong>Pathname:</strong></p>
                    <p><strong>Request per Minute:</strong> </p>
                    <p><strong>Average Latency:</strong> </p>
                    <p><strong>Errors per Minute:</strong> </p>
                    <p><strong>No. of unique IP Address:</strong> </p>
                    <p><strong>2XX:</strong> </p>
                    <p><strong> - 200:</strong> </p>
                    <p><strong> - 204:</strong> </p>
                    <p><strong>4XX:</strong> </p>
                    <p><strong> - 404:</strong> </p>
                </Card.Content>
            </Card>
        )
    }

    renderEmptyCard() {
        return (
            <Card
                fluid
                className="info-card-card"
                style={{
                    opacity: 0.8
                }}
            >
                <Card.Content
                    header="Click on a node to see details"
                />
            </Card>
        )
    }


    render() {
        if (!this.props.node) {
            return this.renderEmptyCard();
        } else if (this.props.node.type === TYPE_ROOT) {
            return this.renderServerCard();
        } else if (this.props.node.type === TYPE_ENDPOINT) {
            return this.renderEndpointCard();
        } else if (this.props.node.type === TYPE_REQUEST) {
            return this.renderHitCard();
        } else {
            return <div>Node unrecognized</div>;
        }
    }
}
