const path = require('path');
const webpack = require('webpack');
const ExtractTextPlugin = require('extract-text-webpack-plugin');

module.exports = {
    entry: {
        // 主文件入口
        main: 'main.js'
    },
    output: {
        // 输出路径，绝对路径
        path: path.resolve(__dirname, '../release'),
        publicPath: '/',
        filename: '[name].[hash].js',
        chunkFilename: '[id].[chunkhash].js'
    },
    resolve: {
        // 允许引入文件不带扩展名 如 from './jquery.min'
        enforceExtension: false,
        // 允许模块不带扩展名
        enforceModuleExtension: false,
        // 能够使用户在引入模块时不带扩展
        extensions: ['.js', '.vue', '.styl', '.css'],
        // 模块索引, 从左到右依次查找
        modules: [path.join(__dirname, '../src'), 'node_modules']
    },
    module: {
        rules: [{
            test: /\.(vue|js)$/,
            enforce: 'pre',
            exclude: /node_modules/,
            use: [{
                loader: 'eslint-loader',
                options: {
                    formatter: require('eslint-friendly-formatter')
                }
            }]
        }, {
            test: /\.js?$/,
            loader: ['babel-loader'],
            exclude: /node_modules/
        }, {
            test: /\.css$/,
            use: ExtractTextPlugin.extract({
                fallback: 'style-loader',
                use: [{
                    loader: 'css-loader',
                    options: {importLoaders: 1}
                }, {
                    loader: 'postcss-loader',
                }]
            })
        }, {
            test: /\.styl$/,
            use: ExtractTextPlugin.extract({
                fallback: 'style-loader',
                use: [{
                    loader: 'css-loader',
                    options: {importLoaders: 1}
                }, {
                    loader: 'postcss-loader'
                }, {
                    loader: 'stylus-loader'
                }]
            })
        }, {
            test: /\.(png|jpe?g|gif)$/i,
            loader: 'url-loader',
            options: {
                limit: 1024,
                name: '[name].[hash].[ext]'
            }
        }, {
            test: /\.(woff|svg|eot|ttf)\??.*$/,
            loader: 'url-loader',
            options: {
                limit: 10240,
                name: '[name].[hash].[ext]'
            }
        }, {
            test: /\.vue$/,
            loader: 'vue-loader'
        }]
    },
    plugins: [
        new ExtractTextPlugin('[name].[contenthash].css')
    ]
}
