const  path = require('path');
const  webpack = require('webpack');
const  HtmlWebpackPlugin = require('html-webpack-plugin');
//const  ExtractTextPlugin = require('extract-text-webpack-plugin');
const MiniCssExtractPlugin = require("mini-css-extract-plugin");
const CleanWebpackPlugin = require('clean-webpack-plugin');

module.exports = {
    entry: {
        main: path.resolve('./src/js/main.js'),
    },
    output: {
        path: path.resolve(__dirname, 'dist'),
        filename: '[name].bundle.js'
        // filename: '[name].[hash].bundle.js'
    }, 
    devtool: "source-map",   
    module: {
        rules: [              
            // {
            //     test: /\.less$/,
            //     use: [{
            //             loader: 'style-loader' 
            //         }, {
            //             loader: 'css-loader' 
            //         }, {
            //             loader: 'less-loader'
            //     }]
            // },
            {
                test: /\.less$/,
                use: [
                    MiniCssExtractPlugin.loader,
                  'css-loader',
                  'less-loader'
                ]
              },      
           { test: /\.js|jsx$/,
                exclude: /node_modules/,
                loader: 'babel-loader',
             /*   use: {
                    loader: 'babel-loader',
                    options:{
                        presets: ['es2015', 'react']
                    }
                }*/
                include: [
                  path.resolve(__dirname, 'src/js/')
                ],
                query: {
                  presets: ['es2015', 'env', 'react']
                }
            },
            {
                test: /\.(png|jpg|gif|svg)$/,
                use: [
                  {
                    loader: 'url-loader',
                    options: {
                      limit: 8192
                    }
                  }
                ]
              }

            {
                test: /\.woff2?(\?v=[0-9]\.[0-9]\.[0-9])?$/,
                loader: "url?limit=10000"
            },
            {
                test: /\.(ttf|eot|svg)(\?[\s\S]+)?$/,
                loader: 'file'
            }, 
            // {test: /\.svg/, loader: 'svg-url-loader'}
        ]
    },
    plugins: [
        // new CleanWebpackPlugin('dist/*.*', {
        //     root: __dirname
        // }),
        new HtmlWebpackPlugin({
            template: 'src/template/index.html'           
        }),
        new MiniCssExtractPlugin({
          filename: "[name].css",
        })
    ],
    devServer: {
        contentBase: path.join(__dirname, "dist"), 
        port: 8000, 
        // host: '10.32.153.147',
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