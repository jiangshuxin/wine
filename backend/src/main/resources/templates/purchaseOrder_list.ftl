<@dt.init>

<table id="dataTable" class="display" cellspacing="0" width="100%">
    <thead>
    <tr>
        <th>编号</th>
        <th>部门</th>
        <th>批次</th>
        <th>商品编码</th>
        <th>商品名称</th>
        <th>进价</th>
        <th>项目编码</th>
        <th>供应商编码</th>
        <th>供应商名称</th>
        <th>采购数量</th>
        <th>采购时间</th>
        <th>返佣</th>
        <th>税金</th>
        <th>上传记录编号</th>
    </tr>
    </thead>

    <tfoot>
    <tr>
        <th>编号</th>
        <th>部门</th>
        <th>批次</th>
        <th>商品编码</th>
        <th>商品名称</th>
        <th>进价</th>
        <th>项目编码</th>
        <th>供应商编码</th>
        <th>供应商名称</th>
        <th>采购数量</th>
        <th>采购时间</th>
        <th>返佣</th>
        <th>税金</th>
        <th>上传记录编号</th>
    </tr>
    </tfoot>
</table>
<script type="text/javascript" language="javascript" class="init">


    $(document).ready(function() {
        editor = new $.fn.dataTable.Editor( DataTable.editorConfig('${moduleName}',[ {
            label: "部门:",
            name: "department"
        }, {
            label: "批次号:",
            name: "batchNo"
        }, {
            label: "商品编码:",
            name: "merchandiseId"
        }, {
            label: "商品名称:",
            name: "merchandiseName"
        }, {
            label: "进价:",
            name: "priceIn"
        }, {
            label: "项目编号:",
            name: "projectId"
        }, {
            label: "供应商编号:",
            name: "supplierId"
        }, {
            label: "供应商名称:",
            name: "supplierName"
        }, {
            label: "采购数量:",
            name: "purchaseNumber"
        }, {
            label: "采购时间:",
            name: "purchaseTime"
        }, {
            label: "返佣:",
            name: "commission"
        }, {
            label: "税金:",
            name: "tax"
        }
        ]) );

        table = $('#dataTable').DataTable( DataTable.dataTableConfig('${moduleName}',[
            { data: "id"},
            { data: "department" },
            { data: "batchNo" },
            { data: "merchandiseId" },
            { data: "merchandiseName" },
            { data: "priceIn" },
            { data: "projectId"},
            { data: "supplierId" },
            { data: "supplierName"},
            { data: "purchaseNumber" },
            { data: "purchaseDate"},
            { data: "commission"},
            { data: "tax"},
            { data: "uploadId",name:"uploadId"<#if uploadId??>,searchValue:"${uploadId?trim}"</#if>}
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

        <#if uploadId??>
            DataTable.searchByColumn(table,'uploadId','${uploadId}');
        </#if>
    } );



</script>


</@dt.init>