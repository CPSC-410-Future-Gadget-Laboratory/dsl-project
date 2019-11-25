import React from 'react';
import { Button, Card, Header, Divider } from 'semantic-ui-react';
import Chart from 'react-google-charts';

export default class InfoCard extends React.Component {
    render() {
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
                        data={[
                            ['Time', 'Traffic'],
                            ["4 Hour Ago", 18],
                            ["3 Hour Ago", 17],
                            ["2 Hour Ago", 23],
                            ["1 Hour Ago", 10],
                            ["Just Now", 0],
                        ]}
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
}