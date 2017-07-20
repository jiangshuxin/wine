module.exports = {
    plugins: [
        require('postcss-cssnext')({
            browsers: ['last 2 versions', 'IE >= 10']
        }),
        require('postcss-import')({
            glob: true,
            onImport: files => {
                // add dependecies from the main.css files to the other css files...
                // so they get hot–reloaded when something changes...
                files.forEach(this.addDependency);
            }
        })
    ],
    sourceMap: true
}
