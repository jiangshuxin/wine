<@dt.init>
    <@dt.uploadFile moduleName=moduleName/>
    <@dt.buildRelationship moduleName=moduleName/>

<table id="dataTable" class="display" cellspacing="0" width="100%">
    <thead>
    <tr>
        <th>部门</th>
        <th>合同编号</th>
        <th>合同名称</th>
        <th>合同类型</th>
        <th>关联客户编码</th>
        <th>关联供应商编码</th>
        <th>合同日期</th>
        <th>有效期</th>
    </tr>
    </thead>

    <tfoot>
    <tr>
        <th>部门</th>
        <th>合同编号</th>
        <th>合同名称</th>
        <th>合同类型</th>
        <th>关联客户编码</th>
        <th>关联供应商编码</th>
        <th>合同日期</th>
        <th>有效期</th>
    </tr>
    </tfoot>
</table>
<script type="text/javascript" language="javascript" class="init">

    $(document).ready(function () {
        editor = DataTable.Editor.newInstance('${moduleName}', [{
            label: "部门:",
            name: "department",
        }, {
            label: "合同编号:",
            name: "id"
        }, {
            label: "合同名称:",
            name: "name"
        }, {
            label: "合同类型:",
            name: "type"
        }, {
            label: "关联客户编码:",
            name: "customerId"
        }, {
            label: "关联供应商编码:",
            name: "supplierId"
        }, {
            label: "合同日期:",
            name: "contractDate"
        }, {
            label: "有效期:",
            name: "expiryDate"
        }
        ]);


        table = $('#dataTable').DataTable(DataTable.dataTableConfig('${moduleName}', [
            {data: "department"},
            {data: "id"},
            {data: "name"},
            {data: "type"},
            {data: "customerId"},
            {data: "supplierId"},
            {data: "contractDate"},
            {data: "expiryDate"}
        ], [
            {extend: "create", editor: editor},
            {extend: "edit", editor: editor},
            {extend: "remove", editor: editor},
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
        ], {
            initComplete: function () {

            }
        }));

        DataTable.enableColumnSearch(table);

        editor.on('open', function (e, json, data) {
            editor.hide('unifiedId');
        });

        <#if unifiedId??>
            DataTable.searchByColumn(table, 'unifiedId', '${unifiedId}');
        </#if>
    });


</script>


</@dt.init>