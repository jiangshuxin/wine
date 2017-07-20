
<@dt.init>

<table id="dataTable" class="display" cellspacing="0" width="100%">
    <thead>
    <tr>
        <th>模块名称</th>
        <th>Entity全名</th>
        <th>展示名称</th>
        <th>排序号</th>
    </tr>
    </thead>

</table>
<script type="text/javascript" language="javascript" class="init">


    $(document).ready(function() {
        editor = new $.fn.dataTable.Editor( DataTable.editorConfig('${moduleName}',[ {
                    label: "模块名称:",
                    name: "moduleName"
                }, {
                    label: "Entity全名:",
                    name: "tableName"
                }, {
                    label: "展示名称:",
                    name: "displayName"
                }, {
                    label: "排序号:",
                    name: "sortNo"
                }
                ]
                ,{
                    idSrc: "moduleName"
                }) );

        table = $('#dataTable').DataTable( DataTable.dataTableConfig('${moduleName}',[
            { data: "moduleName"},
            { data: "tableName" },
            { data: "displayName" },
            { data: "sortNo"}
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
    } );



</script>


</@dt.init>