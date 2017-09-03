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
                bigPics: [
                    'http://img5.imgtn.bdimg.com/it/u=1289450689,1979040778&fm=27&gp=0.jpg',
                    'http://img3.imgtn.bdimg.com/it/u=3437921661,2217376258&fm=27&gp=0.jpg',
                    'http://img5.imgtn.bdimg.com/it/u=1815109083,527747720&fm=27&gp=0.jpg',
                    'http://img3.imgtn.bdimg.com/it/u=4112213050,985164891&fm=27&gp=0.jpg'
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
                status: 1,
                // 酿造工艺
                technology: '工艺特别牛逼',
                // 橡木桶
                barrel: '啊啊啊啊啊啊啊啊啊啊',
                // 罐装时间
                fillingTime: '2017-10-10 16:40:32',
                // 保质期
                expiryDate: '2017-12-30',
                // 试饮温度
                drinkTemperature: '27摄氏度',
                // 醒酒时间
                soberTime: '30分钟',
                // 搭配推荐
                collocation: '馒头片',
                // 产量
                yield: '1000ml/年',
                // 库存量
                inventory: '1000ml',
                // 获奖照片
                prizePics: [
                    'http://img5.imgtn.bdimg.com/it/u=1289450689,1979040778&fm=27&gp=0.jpg',
                    'http://img3.imgtn.bdimg.com/it/u=3437921661,2217376258&fm=27&gp=0.jpg',
                    'http://img5.imgtn.bdimg.com/it/u=1815109083,527747720&fm=27&gp=0.jpg',
                    'http://img3.imgtn.bdimg.com/it/u=4112213050,985164891&fm=27&gp=0.jpg'
                ],
                // 检验报告
                inspectionReportPics: [
                    'http://img5.imgtn.bdimg.com/it/u=1289450689,1979040778&fm=27&gp=0.jpg',
                    'http://img3.imgtn.bdimg.com/it/u=3437921661,2217376258&fm=27&gp=0.jpg',
                    'http://img5.imgtn.bdimg.com/it/u=1815109083,527747720&fm=27&gp=0.jpg',
                    'http://img3.imgtn.bdimg.com/it/u=4112213050,985164891&fm=27&gp=0.jpg'
                ]
            },
            totalCount: -1
        };
        next();
    }
}
