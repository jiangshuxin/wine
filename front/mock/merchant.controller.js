module.exports = {
    getMerchantInfo: (req, res, next) => {
        res.__data = {
            totalCount: 100,
            data: {
                merchantId: 'M0001',
                // 名称
                name: '小不点的酒庄',
                // 英文名称
                nameEn: 'small wine',
                // 庄主
                master: '小不点',
                // 注册时间
                createYear: '2017-01-01',
                // 种植面积
                acreage: '10000平方米',
                // 葡萄种植种类
                agroType: '超级大葡萄',
                // 平均树龄
                treeAge: '1000年',
                // 酿酒师及介绍, 长篇文字
                wineMaker: `宁夏汇达阳光生态酒庄有限责任公司，成立于2013年10月，注册资金368万元，注册地位于吴忠市红寺堡区城东老监区，是一家以有机葡萄种植，葡萄及葡萄酒生产、加工、销售为一体的、一二三产业融合发展的现代化农业企业。2016年3月，公司成为红寺堡区葡萄产业协会会长单位，会长由公司董事长豆孝明担任。
公司现有员工32人，其中大专及以上学历人数21人，占总员工人数66%。拥有3名葡萄种植师，1名国家级酿酒师、1名品酒师，外聘葡萄良种选育、葡萄栽培、葡萄酒酿造和葡萄酒文化等方面专家7人。季节性工人520余人(全部为当地移民)。公司设有技术研发部、财务部、经营部、生产部、种植灾害防护部、办公室、专家大院等部门。
2016年4月，公司分别与宁夏大学葡萄酒学院、宁夏葡萄与葡萄酒研究院建立长期战略合作关系，在科研项目、人才培养培训等领域展开深度合作，通过产学研合作，提升公司核心竞争力。
公司拥有优质酿酒葡萄种植基地6400亩，先后通过了ISO9001管理体系、HACCP体系认证（ISO22000）、有机葡萄种植基地认证、葡萄酒酿造有机产品认证等，严格按照国际葡萄与葡萄酒组织（OIV）标准进行种植和生产。同时，通过采用“酒庄+基地+农户”等多种生产经营模式，实现农业增效、农民增收。2013年以来，汇达葡萄酒产业链`,
                // 年产量
                output: '10顿',
                // 酒庄级别和级别介绍, 长篇文字
                level: `宁夏汇达阳光生态酒庄有限责任公司，成立于2013年10月，注册资金368万元，注册地位于吴忠市红寺堡区城东老监区，是一家以有机葡萄种植，葡萄及葡萄酒生产、加工、销售为一体的、一二三产业融合发展的现代化农业企业。2016年3月，公司成为红寺堡区葡萄产业协会会长单位，会长由公司董事长豆孝明担任。
公司现有员工32人，其中大专及以上学历人数21人，占总员工人数66%。拥有3名葡萄种植师，1名国家级酿酒师、1名品酒师，外聘葡萄良种选育、葡萄栽培、葡萄酒酿造和葡萄酒文化等方面专家7人。季节性工人520余人(全部为当地移民)。公司设有技术研发部、财务部、经营部、生产部、种植灾害防护部、办公室、专家大院等部门。
2016年4月，公司分别与宁夏大学葡萄酒学院、宁夏葡萄与葡萄酒研究院建立长期战略合作关系，在科研项目、人才培养培训等领域展开深度合作，通过产学研合作，提升公司核心竞争力。
公司拥有优质酿酒葡萄种植基地6400亩，先后通过了ISO9001管理体系、HACCP体系认证（ISO22000）、有机葡萄种植基地认证、葡萄酒酿造有机产品认证等，严格按照国际葡萄与葡萄酒组织（OIV）标准进行种植和生产。同时，通过采用“酒庄+基地+农户”等多种生产经营模式，实现农业增效、农民增收。2013年以来，汇达葡萄酒产业链`,
                // 酒庄文字介绍, 长篇文字
                description: `宁夏汇达阳光生态酒庄有限责任公司，成立于2013年10月，注册资金368万元，注册地位于吴忠市红寺堡区城东老监区，是一家以有机葡萄种植，葡萄及葡萄酒生产、加工、销售为一体的、一二三产业融合发展的现代化农业企业。2016年3月，公司成为红寺堡区葡萄产业协会会长单位，会长由公司董事长豆孝明担任。
公司现有员工32人，其中大专及以上学历人数21人，占总员工人数66%。拥有3名葡萄种植师，1名国家级酿酒师、1名品酒师，外聘葡萄良种选育、葡萄栽培、葡萄酒酿造和葡萄酒文化等方面专家7人。季节性工人520余人(全部为当地移民)。公司设有技术研发部、财务部、经营部、生产部、种植灾害防护部、办公室、专家大院等部门。
2016年4月，公司分别与宁夏大学葡萄酒学院、宁夏葡萄与葡萄酒研究院建立长期战略合作关系，在科研项目、人才培养培训等领域展开深度合作，通过产学研合作，提升公司核心竞争力。
公司拥有优质酿酒葡萄种植基地6400亩，先后通过了ISO9001管理体系、HACCP体系认证（ISO22000）、有机葡萄种植基地认证、葡萄酒酿造有机产品认证等，严格按照国际葡萄与葡萄酒组织（OIV）标准进行种植和生产。同时，通过采用“酒庄+基地+农户”等多种生产经营模式，实现农业增效、农民增收。2013年以来，汇达葡萄酒产业链`,
                // 实时土壤和气候信息
                soilAndClimate: '室外温度32摄氏度',
                // 酒庄照片, 最多4张
                chateauPics: [
                    'http://img5.imgtn.bdimg.com/it/u=1289450689,1979040778&fm=27&gp=0.jpg',
                    'http://img3.imgtn.bdimg.com/it/u=3437921661,2217376258&fm=27&gp=0.jpg',
                    'http://img5.imgtn.bdimg.com/it/u=1815109083,527747720&fm=27&gp=0.jpg',
                    'http://img3.imgtn.bdimg.com/it/u=4112213050,985164891&fm=27&gp=0.jpg'
                ],
                // 种植地照片, 4
                landPics: [
                    'http://img5.imgtn.bdimg.com/it/u=1289450689,1979040778&fm=27&gp=0.jpg',
                    'http://img3.imgtn.bdimg.com/it/u=3437921661,2217376258&fm=27&gp=0.jpg',
                    'http://img5.imgtn.bdimg.com/it/u=1815109083,527747720&fm=27&gp=0.jpg',
                    'http://img3.imgtn.bdimg.com/it/u=4112213050,985164891&fm=27&gp=0.jpg'
                ],
                // 部分证照, 4
                certPics: [
                    'http://img5.imgtn.bdimg.com/it/u=1289450689,1979040778&fm=27&gp=0.jpg',
                    'http://img3.imgtn.bdimg.com/it/u=3437921661,2217376258&fm=27&gp=0.jpg',
                    'http://img5.imgtn.bdimg.com/it/u=1815109083,527747720&fm=27&gp=0.jpg',
                    'http://img3.imgtn.bdimg.com/it/u=4112213050,985164891&fm=27&gp=0.jpg'
                ],
                // 得奖照片, 4
                prizePics: [
                    'http://img5.imgtn.bdimg.com/it/u=1289450689,1979040778&fm=27&gp=0.jpg',
                    'http://img3.imgtn.bdimg.com/it/u=3437921661,2217376258&fm=27&gp=0.jpg',
                    'http://img5.imgtn.bdimg.com/it/u=1815109083,527747720&fm=27&gp=0.jpg',
                    'http://img3.imgtn.bdimg.com/it/u=4112213050,985164891&fm=27&gp=0.jpg'
                ],
                videoLink: 'http://player.youku.com/embed/XMjkxOTkwNzQyNA=='
            }
        };
        next();
    }
}
