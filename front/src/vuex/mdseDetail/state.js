import { propertyLayout } from './config';
function generatorBasicInfo() {
    return {
        name: '',
        nameEn: '',
        smallPic: '',
        bigPic: [],
        price: -1,
        wineType: '',
        grapeType: '',
        year: -1,
        degree: '',
        ml: '',
        treeAge: '',
        wineMaker: '',
        merchantName: '',
        productArea: '',
        reason: '',
        storyPic: ''
    };
}


export default {
    mdseId: -1,
    propertyLayout,
    generatorBasicInfo,
    basicInfo: generatorBasicInfo()
};
