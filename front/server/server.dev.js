const Express = require('express');
const path = require('path');
const webpackDevConfig = require('../build/webpack.dev.conf');
const webpackDevMiddleware = require('webpack-dev-middleware');
const webpackHotMiddleware = require('webpack-hot-middleware');
const webpack = require('webpack');
const bodyParser = require('body-parser');

const app = new Express();
const compiler = webpack(webpackDevConfig);
const port = process.env.port || 8080;
// const router = require('../mock');

app.use(webpackDevMiddleware(compiler, {
    publicPath: webpackDevConfig.output.publicPath,
    stats: {
        colors: true,
        chunks: false
    }
}));

app.use(webpackHotMiddleware(compiler));
// proxy
app.use(require('./proxyServer'));
// mock
app.use(bodyParser.json());
app.use(require('../mock'));

console.log('Starting server, Please wait a moment...');
console.log('Remember to change hosts file...');
app.listen(port, err => {
    if (err) {
        console.log(err);
        return;
    }
    console.log(`Listening at 127.0.0.1 ${port === 80 ? '' : ':' + port}/`);
});
