import { TYPE_ROOT, COLOR_ROOT, TYPE_REQUEST, COLOR_REQUEST, COLOR_ENDPOINT, TYPE_ENDPOINT } from './constants';

export const getNodeColorByType = node => {
    if (node.type === TYPE_ROOT) return COLOR_ROOT;
    if (node.type === TYPE_ENDPOINT) return COLOR_ENDPOINT;
    if (node.type === TYPE_REQUEST) return COLOR_REQUEST;
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

export const filterRequestLogsByPathname = (logs, endpointName) => logs.filter((log) => log.endpointName === endpointName && log.type === "request");

/* Get list of endpoints from logs */
export const buildGraphDataFromLogs = (logs) => {
    const logsArray = getLogsAsArrays(logs);

    const graphData = {
        nodes: [],
        links: [],
    };

    const rootNode = {
        id: "ROOT",
        name: "Server",
        val: 1000,
        type: TYPE_ROOT,
        color: "white",
    };

    graphData.nodes.push(rootNode);

    const endpointNames = getListOfEndpointsPathname(logs);

    for (const endpointName of endpointNames) {
        const newNode = {
            id: endpointName,
            name: endpointName,
            val: 200,
            type: TYPE_ENDPOINT,
            color: "grey",
        };

        const newLinks = {
            source: rootNode.id,
            target: newNode.id,
        };

        graphData.nodes.push(newNode);
        graphData.links.push(newLinks);

        // Get all request and response.
        const requestLogs = filterRequestLogsByPathname(logsArray, endpointName);
        for (const requestLog of requestLogs) {
            const endpointLogNode = {
                id: requestLog.id,
                name: requestLog.logTime,
                val: 0.5,
                type: TYPE_REQUEST,
                color: "red",
                request: requestLog,
                response: logsArray.find(log => log.endpointHitId === requestLog.endpointHitId && log.type === "response"),
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

/**
 * Returns the number of requests per minute of an endpoint.
 * @param {object} logs 
 * @param {string} endpointName 
 */
export const getRequestPerMinute = (logs, endpointName) => {
    // Implement Here...
};

/**
 * Returns the average latency of an endpoint.
 * @param {object} logs 
 * @param {string} endpointName 
 */
export const getAverageLatency = (logs, endpointName) => {
    // Implement Here...
};

/**
 * Returns Errors Per Minute of an endpoint.
 * @param {object} logs 
 * @param {string} endpointName 
 */
export const getErrorsPerMinute = (logs, endpointName) => {
    // Implement Here...
};

/**
 * Returns the total number of unique requestor of an endpoint.
 * @param {object} logs 
 * @param {string} endpointName 
 */
export const getNumberOfUniqueIPAddresses = (logs, endpointName) => {
    // Implement Here...
};

/**
 * Returns the number of logs with status code starting with 2XX.
 * @param {object} logs 
 * @param {string} endpointName 
 */
export const getNumberOf2XXStatusCode = (logs, endpointName) => {
    // Implement Here...
};

/**
 * Returns a hashmap (JavaScript object) with status codes starting with 2XX as key and the number of occurences the values.
 * @param {object} logs 
 * @param {string} endpointName 
 */
export const accumulate2XXStatusCodes = (logs, endpointName) => {
    // Implement Here...
};

/**
 * Returns the number of logs with status code starting with 4XX.
 * @param {object} logs 
 * @param {string} endpointName 
 */
export const getNumberOf4XXStatusCode = (logs, endpointName) => {
    // Implement Here...
};

/**
 * Returns a hashmap (JavaScript object) with status codes starting with 4XX as key and the number of occurences the values.
 * @param {object} logs 
 * @param {string} endpointName 
 */
export const accumulate4XXStatusCodes = (logs, endpointName) => {
    // Implement Here...
};

/**
 * Returns the number of logs with status code starting with 5XX.
 * @param {object} logs 
 * @param {string} endpointName 
 */
export const getNumberOf5XXStatusCode = (logs, endpointName) => {
    // Implement Here...
};

/**
 * Returns a hashmap (JavaScript object) with status codes starting with 5XX as key and the number of occurences the values.
 * @param {object} logs 
 * @param {string} endpointName 
 */
export const accumulate5XXStatusCodes = (logs, endpointName) => {
    // Implement Here...
};


export default {
    getListOfEndpointsPathname,
    // More stuff...
};