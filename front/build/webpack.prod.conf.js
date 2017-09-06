const webpack = require('webpack');
const path = require('path');
const HtmlWebpackPlugin = require('html-webpack-plugin');
/*
 *const UglifyJSPlugin = require('uglifyjs-webpack-plugin');
 */
let config = require('./webpack.base.conf');

config.devtool = false;

config.plugins = (config.plugins || []).concat([
    new webpack.DefinePlugin({
        'process.env': {
            NODE_ENV: '"production"'
        }
    }),
    /*
     *new webpack.optimize.UglifyJsPlugin({
     *    compress: {
     *        warnings: false,
     *        // 上线时移除debugger以及console，防止报错
     *        drop_console: true,
     *        drop_debugger: true
     *    }
     *}),
     */
    /*
     *new UglifyJSPlugin({
     *    mangle: {
     *        // 跳过这些
     *        except: ['$super', '$', 'exports', 'require'],
     *    },
     *    comments: false
     *}),
     */
    new webpack.optimize.CommonsChunkPlugin({
      name: 'vendor',
      minChunks: function (module, count) {
        // any required modules inside node_modules are extracted to vendor
        return (
          module.resource &&
          /\.js$/.test(module.resource) &&
          module.resource.indexOf(
            path.join(__dirname, '../node_modules')
          ) === 0
        )
      }
    }),
    new webpack.optimize.CommonsChunkPlugin({
      name: 'manifest',
      chunks: ['vendor']
    }),
    // https://github.com/ampedandwired/html-webpack-plugin
    new HtmlWebpackPlugin({
        template: path.resolve(__dirname, '../src/main.html'),
        filename: 'main.html',
        chunks: ['vendor', 'manifest', 'main'],
        inject: true,
        minify: {
            removeComments: true,
            collapseWhitespace: true,
            removeAttributeQuotes: true
        },
        chunksSortMode: 'dependency',
    }),
    new webpack.DefinePlugin({
        'process.env.NODE_ENV': JSON.stringify('production')
    })
])

config.output.publicPath = './';

module.exports = config;
