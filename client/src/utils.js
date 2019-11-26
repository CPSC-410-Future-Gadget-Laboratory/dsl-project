import { TYPE_ROOT, COLOR_ROOT, TYPE_REQUEST, COLOR_REQUEST, COLOR_ENDPOINT, TYPE_ENDPOINT } from './constants';
import {Card} from "semantic-ui-react";
import React from "react";

export const getNodeColorByType = node => {
    if (node.type === TYPE_ROOT) return COLOR_ROOT;
    if (node.type === TYPE_ENDPOINT) return COLOR_ENDPOINT;
    if (node.type === TYPE_REQUEST) return COLOR_REQUEST;
};

export const getLogsAsArrays = (logs) => {
    return Object.values(logs);
};

/* Get list of endpoints from logs */
export const getListOfEndpointsPathname = (logs) => {
    return Object.values(logs).reduce((set, log) => {
        if (!set.includes(log.endpointName)) {
            set.push(log.endpointName);
        }
        return set;
    }, []);
};

export const filterLogsByPathname = (logs, endpointName) => logs.filter((log) => log.endpointName === endpointName && log.type === "response");

/* Get list of endpoints from logs */
export const buildGraphDataFromLogs = (logs) => {

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
        const endpointLogs = filterLogsByPathname(Object.values(logs), endpointName);
        for (const endpointLog of endpointLogs) {
            const endpointLogNode = {
                id: endpointLog.id,
                name: endpointLog.logTime,
                val: 0.5,
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

/**
 * Returns the number of requests per minute of an endpoint.
 * @param {object} logs
 * @param {string} endpointName
 */
export const getTraffic = (logs, endpointName) => {

  let logArray = Object.values(logs);

  if(endpointName){
    logArray = logArray.filter(log => {
      return log.endpointName === endpointName;
    });
  }


  const NUM_HOURS = 4;

  console.log();

  const currTime = new Date();
  console.log(currTime);

  let updatedLogTime = logArray.map(log => {
    let hour = Math.floor(Math.abs(currTime - new Date(log.logTime)) / 36e5);
    return hour;
  });

  updatedLogTime = updatedLogTime.filter(hour => {
    return hour <= NUM_HOURS;
  });

  const groupByHours = updatedLogTime.reduce((acc, it) => {
    acc[it] = acc[it] + 1 || 1;
    return acc;
  }, {});


  return [
    ['Time', 'Traffic'],
    ["4 Hour Ago", groupByHours['4'] ? groupByHours['4'] : 0],
    ["3 Hour Ago", groupByHours['3'] ? groupByHours['3'] : 0],
    ["2 Hour Ago", groupByHours['2'] ? groupByHours['2'] : 0],
    ["1 Hour Ago", groupByHours['1'] ? groupByHours['1'] : 0],
    ["< 1 Hour Ago", groupByHours['0'] ? groupByHours['0'] : 0],
  ]
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