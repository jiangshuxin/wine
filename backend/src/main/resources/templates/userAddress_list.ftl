
<@dt.init>

    <table id="dataTable" class="display" cellspacing="0" width="100%">
        <thead>
        <tr>
            <th>编号</th>
            <th>关联的用户ID</th>
            <th>收货人</th>
            <th>收货人电话</th>
            <th>省市区</th>
            <th>详细地址</th>
            <th>是否为默认地址</th>
        </tr>
        </thead>

        <tfoot>
        <tr>
            <th>编号</th>
            <th>关联的用户ID</th>
            <th>收货人</th>
            <th>收货人电话</th>
            <th>省市区</th>
            <th>详细地址</th>
            <th>是否为默认地址</th>
        </tr>
        </tfoot>
    </table>
    <script type="text/javascript" language="javascript" class="init">
        var editor;

        $(document).ready(function() {
            editor = DataTable.Editor.newInstance('${moduleName}',[ {
                label: "编号:",
                name: "id",
            }, {
                label: "关联的用户ID:",
                name: "userId"
            }, {
                label: "收货人:",
                name: "receiver"
            }, {
                label: "收货人电话:",
                name: "phone"
            }, {
                label: "省市区:",
                name: "province"
            }, {
                label: "详细地址:",
                name: "address"
            }, {
                label: "是否为默认地址:",
                name: "isDefault"
            }
            ],{

            });

            table = $('#dataTable').DataTable( DataTable.dataTableConfig('${moduleName}',[
                { data: "id"},
                { data: "userId" },
                { data: "receiver" },
                { data: "phone"},
                { data: "province"},
                { data: "address"},
                { data: "isDefault"}
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