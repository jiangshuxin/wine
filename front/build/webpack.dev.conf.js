let config = require('./webpack.base.conf');
const webpack = require('webpack');
const HtmlWebpackPlugin = require('html-webpack-plugin');
const path = require('path');

config.devtool = 'eval-source-map';

/*
 *const hotClient = 'webpack-hot-middleware/client?noInfo=true&reload=true';
 *Object.keys(config.entry).forEach(name => {
 *    const extras = [hotClient];
 *    config.entry[name] = extras.concat(config.entry[name])
 *});
 */

config.plugins = (config.plugins || []).concat([
    // https://github.com/glenjamin/webpack-hot-middleware#installation--usage
    new webpack.HotModuleReplacementPlugin(),
    new webpack.NoErrorsPlugin(),
    // https://github.com/ampedandwired/html-webpack-plugin
    new HtmlWebpackPlugin({
        template: path.resolve(__dirname, '../src/main.html'),
        filename: 'main.html',
        chunks: ['main'],
        minify: {
            removeComments: true,
            collapseWhitespace: true,
            removeAttributeQuotes: true
        }
    })
]);

module.exports = config;
