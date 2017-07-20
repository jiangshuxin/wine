const webpack = require('webpack');
const path = require('path');
const HtmlWebpackPlugin = require('html-webpack-plugin');
let config = require('./webpack.base.conf');

config.devtool = false;

config.plugins = (config.plugins || []).concat([
    new webpack.DefinePlugin({
        'process.env': {
            NODE_ENV: '"production"'
        }
    }),
    new webpack.optimize.UglifyJsPlugin({
        compress: {
            warnings: false,
            // 上线时移除debugger以及console，防止报错
            drop_console: true,
            drop_debugger: true
        }
    }),
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
    }),
    new webpack.DefinePlugin({
        'process.env.NODE_ENV': JSON.stringify('production')
    })
])

module.exports = config;
