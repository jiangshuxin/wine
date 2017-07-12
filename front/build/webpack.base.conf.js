const path = require('path');
const webpack = require('webpack');
const ExtractTextPlugin = require('extract-text-webpack-plugin');

const museUiThemePath = '~muse-ui/src/styles/themes/variables/default.less';

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
        extensions: ['.js', '.vue'],
        // 模块索引, 从左到右依次查找
        modules: [path.join(__dirname, '../src'), 'node_modules'],
        alias: {
            'muse-components': 'muse-ui/src'
        }
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
            use: ['style-loader', 'css-loader', 'postcss-loader']
        }, {
            test: /\.styl$/,
            use: ['style-loader', 'css-loader', 'postcss-loader', 'stylus-loader']
        }, {
            test: /\.less$/,
            use: ['style-loader', 'css-loader', {
                loader: 'less-loader'
            }]
        }, {
            test: /\.(png|jpe?g|gif|svg)$/i,
            loader: 'url-loader',
            options: {
                limit: 1024,
                name: '[name].[hash].[ext]'
            }
        }, {
            test: /\.vue$/,
            loader: 'vue-loader',
            options: {
                loaders: {
                    styl: 'style-loader!css-loader!postcss-loader!stylus-loader',
                    less: ['style-loader', 'css-loader', {
                        loader: 'less-loader',
                        options: {
                            globalVars: {museUiTheme: `'${museUiThemePath}'`}
                        }
                    }]
                },
                postcss: [require('postcss-cssnext')({browsers: ['last 2 versions', 'IE >= 9']})],
                postLoader: {
                    html: 'babel-loader'
                },
                extractCSS: true
            }
        }]
    },
    plugins: [
        new ExtractTextPlugin('[name].[contenthash].css')
    ]
}
