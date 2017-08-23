
<@dt.init>

    <table id="dataTable" class="display" cellspacing="0" width="100%">
        <thead>
        <tr>
            <th>编号</th>
            <th>酒庄编号</th>
            <th>图片上传编号</th>
            <th>标题</th>
            <th>标签</th>
            <th>类型</th>
            <th>创建时间</th>
        </tr>
        </thead>

        <tfoot>
        <tr>
            <th>编号</th>
            <th>酒庄编号</th>
            <th>图片上传编号</th>
            <th>标题</th>
            <th>标签</th>
            <th>类型</th>
            <th>创建时间</th>
        </tr>
        </tfoot>
    </table>
    <script type="text/javascript" language="javascript" class="init">
        var editor;

        $(document).ready(function() {
            var merchantData = Select.init('/backend/merchant/queryAll');

            editor = DataTable.Editor.newInstance('${moduleName}',[ {
                label: "编号:",
                name: "id",
                type: "readonly"
            }, {
                label: "商户编号:",
                name: "merchantId",
                type:  "select",
                options:merchantData
            },{
                label: "图片路径:",
                name: "picRef",
                type: "upload",
                display: function ( file_id ) {
                    return '<img src="'+editor.file( 'files', file_id ).web_path+'"/>';
                },
                clearText: "清除",
                noImageText: '请选择图片'
            }, {
                label: "标题:",
                name: "title"
            },{
                label: "标签:",
                name: "tag"
            },{
                label: "类型:",
                name: "type"
            }, {
                label: "描述:",
                name: "description"
            }
            ],{
                ajax:"${rc.contextPath}/api/upload/discover"
            });

            table = $('#dataTable').DataTable( DataTable.dataTableConfig('${moduleName}',[
                { data: "id"},
                { data: "merchantId" },
                { data: "picRef" },
                { data: "title"},
                { data: "tag"},
                { data: "type"},
                { data: "createTime"}
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