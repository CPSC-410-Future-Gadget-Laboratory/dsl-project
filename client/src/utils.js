import { TYPE_ROOT, COLOR_ROOT, TYPE_REQUEST, COLOR_REQUEST, COLOR_ENDPOINT, TYPE_ENDPOINT } from './constants';

export const getNodeColorByType = node => {
    if (node.type === TYPE_ROOT) return COLOR_ROOT;
    if (node.type === TYPE_ENDPOINT) return COLOR_ENDPOINT;
    if (node.type === TYPE_REQUEST) return COLOR_REQUEST;
};

const sampleLogs = {
    "1": {
        id: 1,
        type: "REQUEST",
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
        type: "REQUEST",
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
        type: "REQUEST",
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
        type: "REQUEST",
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

export const getLogsAsArrays = (logs) => {
    return Object.values(logs);
}

/* Get list of endpoints from logs */
export const getListOfEndpointsPathname = (logs) => {
    return Object.values(logs).reduce((set, log) => {
        if (!set.includes(log.endpointName)) {
            set.push(log.endpointName);
        }
        return set;
    }, []);
};

export const filterLogsByPathname = (logs, endpointName) => logs.filter((log) => log.endpointName === endpointName);

/* Get list of endpoints from logs */
export const buildGraphDataFromLogs = (logs) => {
    // const graphData = {
    //     "nodes": [
    //       {
    //         "id": "1",
    //         "name": "project_name",
    //         "val": 1,
    //         "type": TYPE_ROOT,
    //         "color": "red",
    //       },
    //       {
    //         "id": "2",
    //         "name": "/",
    //         "val": 0.1,
    //         "type": TYPE_ENDPOINT,
    //         "color": "green",
    //       },
    //       {
    //         "id": "3",
    //         "name": "/books",
    //         "val": 0.1,
    //         "type": TYPE_ENDPOINT,
    //         "color": "green",
    //       },
    //       {
    //         "id": "4",
    //         "name": "/books/{id}",
    //         "val": 0.1,
    //         "type": TYPE_ENDPOINT,
    //         "color": "green",
    //       },
    //       {
    //         "id": "5",
    //         "name": "/payment",
    //         "val": 0.1,
    //         "type": TYPE_ENDPOINT,
    //         "color": "green",
    //       },
    //       {
    //         "id": "6",
    //         "name": "/home",
    //         "val": 0.1,
    //         "type": TYPE_ENDPOINT,
    //         "color": "green",
    //       },
    //       {
    //         "id": "7",
    //         "name": "request /home #7",
    //         "val": 0.01,
    //         "type": TYPE_REQUEST,
    //       },
    //       {
    //         "id": "8",
    //         "name": "request /home #7",
    //         "val": 0.01,
    //         "type": TYPE_REQUEST,
    //       },
    //       {
    //         "id": "9",
    //         "name": "request /home #7",
    //         "val": 0.01,
    //         "type": TYPE_REQUEST,
    //       },
    //       {
    //         "id": "10",
    //         "name": "request /home #7",
    //         "val": 0.01,
    //         "type": TYPE_REQUEST,
    //       },
    //       {
    //         "id": "11",
    //         "name": "request /home #7",
    //         "val": 0.01,
    //         "type": TYPE_REQUEST,
    //       },
    //     ],
    //     "links": [
    //       {
    //         source: "1",
    //         target: "2",
    //       },
    //       {
    //         source: "1",
    //         target: "3",
    //       },
    //       {
    //         source: "1",
    //         target: "4",
    //       },
    //       {
    //         source: "1",
    //         target: "5",
    //       },
    //       {
    //         source: "1",
    //         target: "6",
    //       },
    //       {
    //         source: "2",
    //         target: "7",
    //       },
    //       {
    //         source: "2",
    //         target: "8",
    //       },
    //       {
    //         source: "2",
    //         target: "9",
    //       },
    //       {
    //         source: "2",
    //         target: "10",
    //       },
    //       {
    //         source: "2",
    //         target: "11",
    //       }
    //     ]
    //   };

    const graphData = {
        nodes: [],
        links: [],
    };

    const rootNode = {
        id: "ROOT",
        name: "Server",
        val: 10,
        type: TYPE_ROOT,
        color: "white",
    };

    graphData.nodes.push(rootNode);

    const endpointNames = getListOfEndpointsPathname(logs);

    for (const endpointName of endpointNames) {
        const newNode = {
            id: endpointName,
            name: endpointName,
            val: 5,
            type: TYPE_ENDPOINT,
            color: "green",
        };

        const newLinks = {
            source: rootNode.id,
            target: newNode.id,
        };

        graphData.nodes.push(newNode);
        graphData.links.push(newLinks);

        // Get all request and response.
        const endpointLogs = filterLogsByPathname(Object.values(logs), endpointName);
        for (const endpointLog of endpointLogs) {
            const endpointLogNode = {
                id: endpointLog.id,
                name: endpointLog.logTime,
                val: 2,
                type: TYPE_REQUEST,
                color: "red",
            };

            const endpointLogLink = {
                source: newNode.id,
                target: endpointLogNode.id,
            };

            graphData.nodes.push(endpointLogNode);
            graphData.links.push(endpointLogLink);
        }
    }

    return graphData;
};

export default {
    getListOfEndpointsPathname,
    // More stuff...
};