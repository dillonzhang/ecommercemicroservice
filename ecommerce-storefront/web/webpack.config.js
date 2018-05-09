var path = require('path');
var webpack = require('webpack');
var HtmlWebpackPlugin = require('html-webpack-plugin');
var ExtractTextPlugin = require('extract-text-webpack-plugin');


module.exports = {
    entry: {
        main: path.resolve(__dirname, './src/js/main.js')
    },
    output: {
        path: path.resolve(__dirname, 'dist'),
        filename: '[name].bundle.js'
    }, 
    devtool: "source-map",   
    
    module: {
        rules: [               
               
            {
                test: /\.css$/,
                use: ['style-loader',
                    {
                        loader: 'css-loader',
                        options: {                           
                            modules: false
                        }
                    }]
            },
             {
                test: /\.less$/,
                use: ['style-loader',
                    {
                        loader: 'css-loader',
                        options: {
                            modules: false
                        }
                    }]
            },            
            { test: /\.js$/,
                exclude: /node_modules/,
                use: {
                    loader: 'babel-loader',
                    options:{
                        presets: ['es2015', 'react']
                    }
                }
            }  
        ],
    },
    plugins: [
        new HtmlWebpackPlugin({
            template: 'src/template/index.html'           
        })
    ],
    devServer: {
        contentBase: path.join(__dirname, "dist"), 
        port: 8080, 
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