
<@dt.init>

    <table id="dataTable" class="display" cellspacing="0" width="100%">
        <thead>
        <tr>
            <th>编号</th>
            <th>中文名称</th>
            <th>酒庄编号</th>
            <th>状态</th>
            <th>价格</th>
            <th>状态</th>
            <th>分类</th>
        </tr>
        </thead>

        <tfoot>
        <tr>
            <th>编号</th>
            <th>中文名称</th>
            <th>酒庄编号</th>
            <th>状态</th>
            <th>价格</th>
            <th>状态</th>
            <th>分类</th>
        </tr>
        </tfoot>
    </table>
    <script type="text/javascript" language="javascript" class="init">
        var editor;

        $(document).ready(function() {
            var mdseStatusDdic = Ddic.show('mdseStatus');
            var merchantData = Select.init('/api/front/merchant/queryAll');

            editor = DataTable.Editor.newInstance('${moduleName}',[ {
                label: "编号:",
                name: "id",
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
                label: "价格:",
                name: "price"
            }, {
                label: "状态:",
                name: "status",
                type:  "select",
                options:mdseStatusDdic
            }, {
                label: "分类:",
                name: "catagory"
            },{
                label: "酒品类型:",
                name: "wineType"
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
                name: "wineMaker"
            },{
                label: "酒庄名称:",
                name: "merchantName"
            },{
                label: "产区:",
                name: "productArea"
            },{
                label: "小图，用于列表和购物车展示:",
                name: "smallPic"
            },{
                label: "大图1:",
                name: "bigPic1"
            },{
                label: "大图2:",
                name: "bigPic2"
            },{
                label: "大图3:",
                name: "bigPic3"
            },{
                label: "大图4:",
                name: "bigPic4"
            },{
                label: "品鉴与酒庄故事:",
                name: "storyPic"
            }, {
                label: "推荐理由:",
                name: "reason"
            }
            ],{

            });

            table = $('#dataTable').DataTable( DataTable.dataTableConfig('${moduleName}',[
                { data: "id"},
                { data: "name" },
                { data: "merchantId"},
                { data: "statusName",searchType:"select",ddic:"mdseStatus",ddicRef:"status"},
                { data: "price"},
                { data: "catagory"},
                { data: "status"}
            ],[
                { extend: "create", editor: editor },
                { extend: "edit",   editor: editor },
                { extend: "remove", editor: editor }
            ],{initComplete: function ()
            {

            },columnDefs: [
                { "visible": false, "targets": [6] }
            ]
                , order: [[ 0, 'desc' ]]}) );

            DataTable.enableColumnSearch(table);
        } );



    </script>


</@dt.init>