import { getListOfEndpointsPathname } from "../utils";

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
describe("utils", () => {
    it("getListOfEndpointsPathname should get list of unique endpoint names", () => {
        expect(getListOfEndpointsPathname(sampleLogs)).toEqual(expect.arrayContaining(["/books/{id}", "/books", "/"]));
    });
});