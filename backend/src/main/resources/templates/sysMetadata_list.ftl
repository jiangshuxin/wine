
<@dt.init>

<table id="dataTable" class="display" cellspacing="0" width="100%">
    <thead>
    <tr>
        <th>模块名称</th>
        <th>表名</th>
        <th>展示名称</th>
        <th>排序号</th>
        <th>是否记录日志</th>
        <th>批处理数量</th>
        <th>批处理SQL</th>
        <th>校验选项</th>
        <th>编码</th>
        <th>是否需要上传</th>
    </tr>
    </thead>

    <tfoot>
    <tr>
        <th>模块名称</th>
        <th>表名</th>
        <th>展示名称</th>
        <th>排序号</th>
        <th>是否记录日志</th>
        <th>批处理数量</th>
        <th>批处理SQL</th>
        <th>校验选项</th>
        <th>编码</th>
        <th>是否需要上传</th>
    </tr>
    </tfoot>
</table>
<script type="text/javascript" language="javascript" class="init">


    $(document).ready(function() {
        editor = new $.fn.dataTable.Editor( DataTable.editorConfig('${moduleName}',[ {
                    label: "模块名称:",
                    name: "moduleName"
                }, {
                    label: "表名:",
                    name: "tableName"
                }, {
                    label: "展示名称:",
                    name: "displayName"
                }, {
                    label: "排序号:",
                    name: "sortNo"
                }, {
                    label: "是否日志记录:",
                    name: "needLog"
                }, {
                    label: "批处理数量:",
                    name: "batchSize"
                }, {
                    label: "批处理SQL:",
                    name: "batchSql",
                    type: "textarea"
                }, {
                    label: "校验选项:",
                    name: "checkOption"
                }, {
                    label: "编码:",
                    name: "encoding"
                }, {
                    label: "是否需要上传:",
                    name: "needUpload"
                }
                ]
                ,{
                    idSrc: "moduleName"
                }) );

        table = $('#dataTable').DataTable( DataTable.dataTableConfig('${moduleName}',[
            { data: "moduleName"},
            { data: "tableName" },
            { data: "displayName" },
            { data: "sortNo"},
            { data: "needLog" },
            { data: "batchSize"},
            { data: "batchSql"},
            { data: "checkOption"},
            { data: "encoding"},
            { data: "needUpload"}
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

        },columnDefs: [
            { "visible": false, "targets": 6 }
        ]}) );

        DataTable.enableColumnSearch(table);
    } );



</script>


</@dt.init>