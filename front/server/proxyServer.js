const httpProxy = require('http-proxy');
const fs = require('fs');
const proxy = httpProxy.createProxyServer();
// const mockServer = require('./mockServer');
const devEnv = {
    dev: ''
};

module.exports = function proxyServer(req, res, next) {
    if (process.env.MOCK === 'false') {
        console.log(req.url);
        return proxy.web(req, res, {
             target: devEnv.dev
        }, err => {
             console.log(err);
        });
    }
    return next();
};
