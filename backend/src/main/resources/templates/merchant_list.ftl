
<@dt.init>

    <table id="dataTable" class="display" cellspacing="0" width="100%">
        <thead>
        <tr>
            <th>酒庄编号</th>
            <th>酒庄名称</th>
            <th>状态</th>
            <th>庄主</th>
            <th>创建时间</th>
            <th>酒庄地址</th>
        </tr>
        </thead>

        <tfoot>
        <tr>
            <th>酒庄编号</th>
            <th>酒庄名称</th>
            <th>状态</th>
            <th>庄主</th>
            <th>创建时间</th>
            <th>酒庄地址</th>
        </tr>
        </tfoot>
    </table>
    <script type="text/javascript" language="javascript" class="init">
        var editor;

        $(document).ready(function() {
            editor = DataTable.Editor.newInstance('${moduleName}',[ {
                label: "酒庄编号:",
                name: "merchantId",
            }, {
                label: "后台登录密码:",
                name: "password"
            }, {
                label: "酒庄名称:",
                name: "name"
            }, {
                label: "状态:",
                name: "status"
            }, {
                label: "庄主:",
                name: "master"
            }, {
                label: "创建时间:",
                name: "createYear"
            }, {
                label: "土壤类型:",
                name: "agroType"
            },{
                label: "葡萄品种:",
                name: "grapeType"
            },{
                label: "平均树龄:",
                name: "treeAge"
            },{
                label: "酿酒师:",
                name: "wineMaker"
            },{
                label: "酿造工艺:",
                name: "technology"
            },{
                label: "年产量:",
                name: "output"
            },{
                label: "橡木桶:",
                name: "barrel"
            },{
                label: "酒庄地址:",
                name: "address"
            },{
                label: "接待能力:",
                name: "capacity"
            },{
                label: "详细描述:",
                name: "description"
            }
            ],{

            });

            table = $('#dataTable').DataTable( DataTable.dataTableConfig('${moduleName}',[
                { data: "merchantId"},
                { data: "name" },
                { data: "status" },
                { data: "master"},
                { data: "createYear"},
                { data: "address"}
            ],[
                { extend: "create", editor: editor },
                { extend: "edit",   editor: editor },
                { extend: "remove", editor: editor }
            ],{initComplete: function ()
            {

            }}) );

            DataTable.enableColumnSearch(table);
        } );



    </script>


</@dt.init>