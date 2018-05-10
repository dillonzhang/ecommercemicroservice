var path = require('path');
var webpack = require('webpack');
var HtmlWebpackPlugin = require('html-webpack-plugin');
var ExtractTextPlugin = require('extract-text-webpack-plugin');


module.exports = {
    entry: {
        main: path.resolve('./src/js/main.js'),
    },
    output: {
        path: path.resolve(__dirname, 'dist'),
        filename: '[name].[hash].bundle.js'
    }, 
    devtool: "source-map",   
    module: {
        rules: [              
               
            {
                test: /\.less$/,
                use: [{
                        loader: 'style-loader' 
                    }, {
                        loader: 'css-loader' 
                    }, {
                        loader: 'less-loader'
                }]
            },
          //  {
          //   test: /\.less$/,
          //   use: ExtractTextPlugin.extract({
          //     fallback: 'style-loader',
          //     use: ['css-loader', 'less-loader']
          //   })
          // },        
           { test: /\.js$/,
                exclude: /node_modules/,
                use: {
                    loader: 'babel-loader',
                    options:{
                        presets: ['es2015', 'react']
                    }
                }
            },
            // {
            //     test: /\.woff2?(\?v=[0-9]\.[0-9]\.[0-9])?$/,
            //     loader: "url?limit=10000"
            // },
            // {
            //     test: /\.(ttf|eot|svg)(\?[\s\S]+)?$/,
            //     loader: 'file'
            // }, 
            // {test: /\.svg/, loader: 'svg-url-loader'}
        ],
    },
    plugins: [
        new HtmlWebpackPlugin({
            template: 'src/template/index.html'           
        }),
        // new ExtractTextPlugin({
        //     filename: 'style.css'
        // }),
    ],
    devServer: {
        contentBase: path.join(__dirname, "dist"), 
        port: 9000, 
        host: '10.32.154.111',
        open:true, 
        compress: true,
        historyApiFallback: true,
        watchContentBase: true,
        inline: true,
        watchOptions: {
            poll: true
        }
    }
};