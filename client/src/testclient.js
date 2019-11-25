const http = require("http");

let cmdArgs = process.argv.slice(2);

const options = {
    hostname: "localhost",
    port: 8000,
    path: cmdArgs[0],
    method: "GET"
};

for (let i = 0; i < cmdArgs[1]; i++) {
    const req = http.request(options, res => {
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