
<@dt.init>

    <table id="dataTable" class="display" cellspacing="0" width="100%">
        <thead>
        <tr>
            <th>编号</th>
            <th>酒庄编号</th>
            <th>图片路径</th>
            <th>排序号</th>
            <th>商品编号</th>
        </tr>
        </thead>

        <tfoot>
        <tr>
            <th>编号</th>
            <th>酒庄编号</th>
            <th>图片路径</th>
            <th>排序号</th>
            <th>商品编号</th>
        </tr>
        </tfoot>
    </table>
    <script type="text/javascript" language="javascript" class="init">
        var editor;

        $(document).ready(function() {
            editor = DataTable.Editor.newInstance('${moduleName}',[ {
                label: "编号:",
                name: "id",
                type: "readonly"
            }, {
                label: "商户编号:",
                name: "merchantId"
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
                label: "排序号:",
                name: "sortValue"
            }, {
                label: "商品编号:",
                name: "mdseId"
            }
            ],{
                ajax:"${rc.contextPath}/api/upload/banner"
            });

            table = $('#dataTable').DataTable( DataTable.dataTableConfig('${moduleName}',[
                { data: "id"},
                { data: "merchantId" },
                { data: "picRef" },
                { data: "sortValue"},
                { data: "mdseId"}
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