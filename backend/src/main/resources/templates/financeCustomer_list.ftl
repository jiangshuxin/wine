
<@dt.init mode=mode!'normal'>
    <@dt.uploadFile moduleName=moduleName/>

    <table id="dataTable" class="display" cellspacing="0" width="100%">
        <thead>
        <tr>
        	<th>序号</th>
            <th>编码</th>
            <th>名称</th>
        </tr>
        </thead>

        <tfoot>
        <tr>
        	<th>序号</th>
            <th>编号</th>
            <th>名称</th>
        </tr>
        </tfoot>
    </table>
    <script type="text/javascript" language="javascript" class="init">


        $(document).ready(function() {
            editor = DataTable.Editor.newInstance('${moduleName}',[ {
                label: "序号:",
                name: "sortNo"
            }, {
                label: "编码:",
                name: "id"
            }, {
                label: "名称:",
                name: "name"
            }
            ]);


            table = $('#dataTable').DataTable( DataTable.dataTableConfig('${moduleName}',[
                { data: "sortNo"},
                { data: "id"},
                { data: "name" }
            ],[
            <#if !mode??>
                <#assign mode="normal">
            </#if>
            <#if mode?? && mode=='normal'>
                { extend: "create", editor: editor },
                { extend: "edit",   editor: editor },
                { extend: "remove", editor: editor },
                {
                    extend: 'collection',
                    text: '导出',
                    buttons: [
                        'copy',
                        'excel',
                        'csv',
                        'pdf',
                        'print'
                    ]
                },
                {
                    extend: "create",
                    text: "数据文件上传",
                    action: function ( e, dt, node, config ) {
                        $('#uploadModal').modal();
                    }
                }
            </#if>
            ],{initComplete: function ()
            {
            },columnDefs: [
                { "width": "7%", "targets": 0 },
                { "width": "7%", "targets": 1 }
            ]}) );

            DataTable.enableColumnSearch(table);
        } );



    </script>


</@dt.init>