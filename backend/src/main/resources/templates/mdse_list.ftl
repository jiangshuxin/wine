
<@dt.init>

    <table id="dataTable" class="display" cellspacing="0" width="100%">
        <thead>
        <tr>
            <th>编号</th>
            <th>中文名称</th>
            <th>酒庄编号</th>
            <th>状态</th>
            <th>分类</th>
            <th>价格</th>
            <th>分类</th>
            <th>状态</th>
            <th>图片上传编号</th>
            <th>图片上传编号</th>
            <th>图片上传编号</th>
            <th>图片上传编号</th>
            <th>图片上传编号</th>
            <th>图片上传编号</th>
        </tr>
        </thead>

        <tfoot>
        <tr>
            <th>编号</th>
            <th>中文名称</th>
            <th>酒庄编号</th>
            <th>状态</th>
            <th>分类</th>
            <th>价格</th>
            <th>分类</th>
            <th>状态</th>
            <th>图片上传编号</th>
            <th>图片上传编号</th>
            <th>图片上传编号</th>
            <th>图片上传编号</th>
            <th>图片上传编号</th>
            <th>图片上传编号</th>
            <th>图片上传编号</th>
            <th>图片上传编号</th>
            <th>图片上传编号</th>
            <th>图片上传编号</th>
            <th>图片上传编号</th>
            <th>图片上传编号</th>
            <th>图片上传编号</th>
        </tr>
        </tfoot>
    </table>
    <script type="text/javascript" language="javascript" class="init">
        var editor;

        $(document).ready(function() {
            var mdseStatusDdic = Ddic.show('mdseStatus');
            var mdseCatagoryDdic = Ddic.show('mdseCatagory');
            var merchantData = Select.init('/backend/merchant/queryAll');

            editor = DataTable.Editor.newInstance('${moduleName}',[ {
                label: "编号:",
                name: "id",
                type: "hidden"
            }, {
                label: "酒庄编号:",
                name: "merchantId",
                type:  "select",
                options:merchantData
            }, {
                label: "中文名称:",
                name: "name"
            }, {
                label: "英文名称:",
                name: "nameEn"
            }, {
                label: "价格(元):",
                name: "price"
            }, {
                label: "状态:",
                name: "status",
                type:  "select",
                options:mdseStatusDdic
            }, {
                label: "分类:",
                name: "catagory",
                type:  "select",
                options:mdseCatagoryDdic
            },{
                label: "葡萄品种:",
                name: "grapeType"
            },{
                label: "年份:",
                name: "year"
            },{
                label: "酒精度:",
                name: "degree"
            },{
                label: "净含量:",
                name: "ml"
            },{
                label: "平均树龄:",
                name: "treeAge"
            },{
                label: "酿酒师:",
                name: "wineMaker",
                type: "textarea"
            },{
                label: "产区:",
                name: "productArea"
            }, {
                label: "酿造工艺:",
                name: "technology"
            }, {
                label: "橡木桶:",
                name: "barrel"
            }, {
                label: "罐装时间:",
                name: "fillingTime"
            }, {
                label: "保质期:",
                name: "expiryDate"
            }, {
                label: "适饮温度:",
                name: "drinkTemperature"
            }, {
                label: "醒酒时间(单位：分钟):",
                name: "soberTime"
            }, {
                label: "搭配推荐:",
                name: "collocation"
            }, {
                label: "产量:",
                name: "yield"
            }, {
                label: "库存量:",
                name: "inventory"
            },{
                label: "小图，用于列表和购物车展示:",
                name: "smallPicRef",
                type: "upload",
                display: function ( file_id ) {
                    return '<img src="'+editor.file( 'files', file_id ).web_path+'"/>';
                },
                clearText: "清除",
                noImageText: '请选择图片'
            },{
                label: "大图1:",
                name: "bigPic1Ref",
                type: "upload",
                display: function ( file_id ) {
                    return '<img src="'+editor.file( 'files', file_id ).web_path+'"/>';
                },
                clearText: "清除",
                noImageText: '请选择图片'
            },{
                label: "大图2:",
                name: "bigPic2Ref",
                type: "upload",
                display: function ( file_id ) {
                    return '<img src="'+editor.file( 'files', file_id ).web_path+'"/>';
                },
                clearText: "清除",
                noImageText: '请选择图片'
            },{
                label: "大图3:",
                name: "bigPic3Ref",
                type: "upload",
                display: function ( file_id ) {
                    return '<img src="'+editor.file( 'files', file_id ).web_path+'"/>';
                },
                clearText: "清除",
                noImageText: '请选择图片'
            },{
                label: "大图4:",
                name: "bigPic4Ref",
                type: "upload",
                display: function ( file_id ) {
                    return '<img src="'+editor.file( 'files', file_id ).web_path+'"/>';
                },
                clearText: "清除",
                noImageText: '请选择图片'
            },{
                label: "得奖照片1:",
                name: "prizePic1Ref",
                type: "upload",
                display: function ( file_id ) {
                    return '<img src="'+editor.file( 'files', file_id ).web_path+'"/>';
                },
                clearText: "清除",
                noImageText: '请选择图片'
            },{
                label: "得奖照片2:",
                name: "prizePic2Ref",
                type: "upload",
                display: function ( file_id ) {
                    return '<img src="'+editor.file( 'files', file_id ).web_path+'"/>';
                },
                clearText: "清除",
                noImageText: '请选择图片'
            },{
                label: "得奖照片3:",
                name: "prizePic3Ref",
                type: "upload",
                display: function ( file_id ) {
                    return '<img src="'+editor.file( 'files', file_id ).web_path+'"/>';
                },
                clearText: "清除",
                noImageText: '请选择图片'
            },{
                label: "得奖照片4:",
                name: "prizePic4Ref",
                type: "upload",
                display: function ( file_id ) {
                    return '<img src="'+editor.file( 'files', file_id ).web_path+'"/>';
                },
                clearText: "清除",
                noImageText: '请选择图片'
            },{
                label: "检验报告1:",
                name: "inspectionReportPic1Ref",
                type: "upload",
                display: function ( file_id ) {
                    return '<img src="'+editor.file( 'files', file_id ).web_path+'"/>';
                },
                clearText: "清除",
                noImageText: '请选择图片'
            },{
                label: "检验报告2:",
                name: "inspectionReportPic2Ref",
                type: "upload",
                display: function ( file_id ) {
                    return '<img src="'+editor.file( 'files', file_id ).web_path+'"/>';
                },
                clearText: "清除",
                noImageText: '请选择图片'
            },{
                label: "检验报告3:",
                name: "inspectionReportPic3Ref",
                type: "upload",
                display: function ( file_id ) {
                    return '<img src="'+editor.file( 'files', file_id ).web_path+'"/>';
                },
                clearText: "清除",
                noImageText: '请选择图片'
            },{
                label: "检验报告4:",
                name: "inspectionReportPic4Ref",
                type: "upload",
                display: function ( file_id ) {
                    return '<img src="'+editor.file( 'files', file_id ).web_path+'"/>';
                },
                clearText: "清除",
                noImageText: '请选择图片'
            }
            ],{
                ajax:"${rc.contextPath}/api/upload/${moduleName}"
            });

            table = $('#dataTable').DataTable( DataTable.dataTableConfig('${moduleName}',[
                { data: "id"},
                { data: "name" },
                { data: "merchantId"},
                { data: "statusName",searchType:"select",ddic:"mdseStatus",ddicRef:"status"},
                { data: "catagoryName",searchType:"select",ddic:"mdseCatagory",ddicRef:"catagory"},
                { data: "price",render: function ( data, type, row ) {
                    if(!isNaN(row.price)){
                        return new Number(row.price)/100;
                    }
                }},
                { data: "catagory"},
                { data: "status"},
                { data: "smallPicRef"},
                { data: "bigPic1Ref"},
                { data: "bigPic2Ref"},
                { data: "bigPic3Ref"},
                { data: "bigPic4Ref"},
                { data: "prizePic1"},
                { data: "prizePic2"},
                { data: "prizePic3"},
                { data: "prizePic4"},
                { data: "inspectionReportPic1"},
                { data: "inspectionReportPic2"},
                { data: "inspectionReportPic3"},
                { data: "inspectionReportPic4"}
            ],[
                { extend: "create", editor: editor },
                { extend: "edit",   editor: editor },
                { extend: "remove", editor: editor }
            ],{initComplete: function ()
            {

            },columnDefs: [
                { "visible": false, "targets": [6,7,8,9,10,11,12,13,14,15,16,17,18,19,20] }
            ]
                , order: [[ 0, 'desc' ]]}) );

            DataTable.enableColumnSearch(table);

            editor.on( 'open', function ( e, json, data ) {
                var price = new Number(editor.get('price'));
                editor.set('price',price/100);
            } );
            editor.on( 'preSubmit', function ( e, json, action ) {
                var data = json.data;
                for(var k in data){
                    data[k].price = data[k].price*100;
                }
            } );
        } );



    </script>


</@dt.init>