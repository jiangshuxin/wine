module.exports = {
    getMdses: (req, res, next) => {
        function generatorlist() {
            return new Array(10).fill(0).map((item, index) => {
                return {
                    mdseId: parseInt(`${index * +req.body.pageNumber}${((Math.random() + 1) * 50).toFixed(2)}`),
                    name: '维德尊贵干红',
                    nameEn: 'Valdelosfrailes Prestigio',
                    year: 2016,
                    smallPic: 'http://img5.imgtn.bdimg.com/it/u=1568156851,1614184660&fm=26&gp=0.jpg',
                    price: 3000
                };
            });
        }
        res.__data = {
            totalCount: 100,
            data: generatorlist()
        };
        next();
    },
    getMdseDetail: (req, res, next) => {
        res.__data = {
            data: {
                mdseId: +req.params.id,
                name: '维德尊贵干红',
                nameEn: 'Valdelosfrailes Prestigio',
                smallPic: 'http://img5.imgtn.bdimg.com/it/u=1568156851,1614184660&fm=26&gp=0.jpg',
                bigPic: [
                    'http://onefunnyjoke.com/wp-content/uploads/2011/09/%E9%96%8B%E8%86%9B%E6%89%8B.gif',
                    'http://img1.imgtn.bdimg.com/it/u=2158572121,184430064&fm=26&gp=0.jpg',
                    'http://img1.imgtn.bdimg.com/it/u=3617220880,3026076505&fm=26&gp=0.jpg'
                ],
                price: 3800,
                wineType: '红葡萄酒',
                grapeType: '巨峰葡萄',
                year: 2012,
                degree: '12%',
                ml: '750ml',
                treeAge: '80年',
                wineMaker: '蒋树新',
                merchantName: '潘家园酒庄',
                productArea: '北京',
                reason: '没有理由！就是好喝！',
                storyPic: 'http://img.zcool.cn/community/01cbf555c863b232f8755e66ec06f5.jpg'
            },
            totalCount: -1
        };
        next();
    }
}
