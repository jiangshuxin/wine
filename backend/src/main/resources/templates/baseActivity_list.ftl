<@dt.init>
    <@dt.uploadFile moduleName=moduleName/>
    <@dt.buildRelationship moduleName=moduleName/>

<table id="dataTable" class="display" cellspacing="0" width="100%">
    <thead>
    <tr>
        <th>部门</th>
        <th>活动编号</th>
        <th>活动名称</th>
        <th>关联项目编号</th>
    </tr>
    </thead>

    <tfoot>
    <tr>
        <th>部门</th>
        <th>活动编号</th>
        <th>活动名称</th>
        <th>关联项目编号</th>
    </tr>
    </tfoot>
</table>
<script type="text/javascript" language="javascript" class="init">


    $(document).ready(function() {

        editor = DataTable.Editor.newInstance('${moduleName}',[ {
            label: "部门:",
            name: "department",
        }, {
            label: "活动编号:",
            name: "id"
        }, {
            label: "活动名称:",
            name: "name"
        }, {
            label: "关联项目编号:",
            name: "projectId"
        }
        ]);


        table = $('#dataTable').DataTable( DataTable.dataTableConfig('${moduleName}',[
            { data: "department" },
            { data: "id"},
            { data: "name" },
            { data: "projectId" }
        ],[
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
            }
        ],{initComplete: function ()
        {

        }}) );

        DataTable.enableColumnSearch(table);

        editor.on( 'open', function ( e, json, data ) {
            editor.hide('unifiedId');
        } );

    } );



</script>


</@dt.init>