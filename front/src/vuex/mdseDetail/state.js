import { propertyLayout } from './config';
function generatorBasicInfo() {
    return {
        name: '',
        nameEn: '',
        smallPic: '',
        bigPics: [],
        price: -1,
        wineType: '',
        // 葡萄品种及介绍, 长篇文字
        grapeType: '',
        year: -1,
        degree: '',
        ml: '',
        treeAge: '',
        // 酿酒师及介绍, 长篇文字
        wineMaker: '',
        merchantName: '',
        productArea: '',
        // reason: '',
        // storyPic: '',
        status: -1,
        // 酿造工艺
        technology: '',
        // 橡木桶
        barrel: '',
        // 罐装时间
        fillingTime: '',
        // 保质期
        expiryDate: '',
        // 试饮温度
        drinkTemperature: '',
        // 醒酒时间
        soberTime: '',
        // 搭配推荐
        collocation: '',
        // 产量
        yield: '',
        // 库存量
        inventory: '',
        // 获奖照片
        prizePics: [],
        // 检验报告
        inspectionReportPics: []
    };
}


export default {
    mdseId: -1,
    propertyLayout,
    generatorBasicInfo,
    basicInfo: generatorBasicInfo()
};
