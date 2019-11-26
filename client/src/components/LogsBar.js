import React from 'react';
import '../App.css';
import {Card, Header, Divider, Label} from 'semantic-ui-react';

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
                <Divider/>
                <Card.Group style={{
                    overflowY: "scroll",
                }}>
                    {Object.values(this.props.logs).map(log =>
                        <Card>
                            <Card.Content
                                header={`${log.type} ID: ${log.endpointHitId}`}
                            />
                            <Divider fitted/>
                            <Card.Content
                                description={`URL: ${log.url}`}
                            />
                            <Divider fitted/>
                            <Card.Content
                                description={`IPAddress: ${log.IPAddress}`}
                            />
                            <Divider fitted/>
                            <Card.Content
                                description={`Type: ${log.type}`}
                            />
                            <Divider fitted/>
                            <Card.Content
                                description={`Endpoint Name: ${log.endpointName}`}
                            />
                            <Divider fitted/>
                            <Card.Content
                                description={`Log Time: ${log.logTime}`}
                            />
                            {log.type === "response" ?
                                <React.Fragment>
                                    <Divider fitted/>
                                    <Card.Content
                                        description={`Content Type: ${log.contentType}`}
                                    />
                                    <Divider fitted/>
                                    <Card.Content
                                        description={<React.Fragment>Status
                                            Code: {log.statusCode} {log.statusCode < 300 ?
                                                <Label circular color={'green'} empty key={'green'}/> :
                                                <Label circular color={'red'} empty key={'red'}/>}</React.Fragment>}
                                    />
                                    <Divider fitted/>
                                    <Card.Content
                                        description={`Message: ${log.message}`}
                                    />
                                </React.Fragment> : ""}

                        </Card>)}
                </Card.Group>
            </Card>
        )
    }
}