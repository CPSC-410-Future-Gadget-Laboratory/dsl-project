import React from 'react';
import '../App.css';
import { Card, Header, Divider } from 'semantic-ui-react';

export default class LogsBar extends React.Component {
    render() {
        return (
            <Card fluid className="logs-bar-card" style={{
                padding: "10px",
                opacity: 0.9
            }}>
                <Header as="h1">
                    Logs
                </Header>
                <Divider />
                <Card.Group style={{
                    overflowY: "scroll",
                }}>
                    {Object.values(this.props.logs).map(log =>
                        <Card>
                            <Card.Content
                                header={log.endpointName}
                                description={log.statusCode}
                            />
                        </Card>)}
                    <Card>
                        <Card.Content
                            header="/books/{bookID}/user/{userID}"
                            description="ENDPOINT"
                        />
                    </Card>
                    <Card>
                        <Card.Content
                            header="/books/{bookID}/user/{userID}"
                            description="ENDPOINT"
                        />
                    </Card>
                    <Card>
                        <Card.Content
                            header="/books/{bookID}/user/{userID}"
                            description="ENDPOINT"
                        />
                    </Card>
                    <Card>
                        <Card.Content
                            header="/books/{bookID}/user/{userID}"
                            description="ENDPOINT"
                        />
                    </Card>
                    <Card>
                        <Card.Content
                            header="/books/{bookID}/user/{userID}"
                            description="ENDPOINT"
                        />
                    </Card>
                    <Card>
                        <Card.Content
                            header="/books/{bookID}/user/{userID}"
                            description="ENDPOINT"
                        />
                    </Card>
                    <Card>
                        <Card.Content
                            header="/books/{bookID}/user/{userID}"
                            description="ENDPOINT"
                        />
                    </Card>
                    <Card>
                        <Card.Content
                            header="/books/{bookID}/user/{userID}"
                            description="ENDPOINT"
                        />
                    </Card>
                    <Card>
                        <Card.Content
                            header="/books/{bookID}/user/{userID}"
                            description="ENDPOINT"
                        />
                    </Card>
                    <Card>
                        <Card.Content
                            header="/books/{bookID}/user/{userID}"
                            description="ENDPOINT"
                        />
                    </Card>
                    <Card>
                        <Card.Content
                            header="/books/{bookID}/user/{userID}"
                            description="ENDPOINT"
                        />
                    </Card>
                </Card.Group>
            </Card>
        )
    }
}