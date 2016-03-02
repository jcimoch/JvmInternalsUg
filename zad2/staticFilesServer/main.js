/**
 * Created by Jarek on 01.03.2016.
 */
'use strict';
const exec = require('exec');
const child_process = require('child_process');

console.log(`Server starting on 0.0.0.0:8080 ...`);
child_process.exec('node node_modules/http-server/bin/http-server static', (err, out, code) => {
    if (err instanceof Error) {
        throw err;
    }
    process.stderr.write(err);
    process.stderr.write(out);
    process.stderr.write(code);
    process.exit(code);
});