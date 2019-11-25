const http = require("http");

const options = [
    {
        hostname: "localhost",
        port: 8000,
        path: "/path/to/success",
        method: "GET"
    },
    {
        hostname: "localhost",
        port: 8000,
        path: "/",
        method: "GET"
    },
    {
        hostname: "localhost",
        port: 8000,
        path: "/path",
        method: "GET"
    },
    {
        hostname: "localhost",
        port: 8000,
        path: "/to",
        method: "GET"
    },
    {
        hostname: "localhost",
        port: 8000,
        path: "/index",
        method: "GET"
    },
    {
        hostname: "localhost",
        port: 8000,
        path: "/success",
        method: "GET"
    }
];

for (let i = 0; i < options.length; i++) {
    let numRequests = getRandomInt(50);
    for (let j = 0; j < numRequests; j++) {
        const req = http.request(options[i], res => {
            console.log(`statusCode: ${res.statusCode}`)
            res.on('data', d => {
                process.stdout.write(d)
            })
        });
        req.on('error', error => {
            console.error(error)
        })
        req.end();
    }
}

function getRandomInt(max) {
    return Math.floor(Math.random() * Math.floor(max));
}
