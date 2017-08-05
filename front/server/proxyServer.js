const httpProxy = require('http-proxy');
const fs = require('fs');
const proxy = httpProxy.createProxyServer();
const devEnv = {
    dev: 'http://123.57.234.184:7080'
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
    setTimeout(() => {
        next();
    }, 1000);
};
