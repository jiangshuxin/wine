module.exports = {
    plugins: [
        require('postcss-cssnext')({browsers: ['last 2 versions', 'IE >= 10']})
    ]
}
