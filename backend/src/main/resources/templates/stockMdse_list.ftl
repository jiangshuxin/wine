<@dt.init>

<table id="dataTable" class="display" cellspacing="0" width="100%">
    <thead>
    <tr>
        <th>编号</th>
        <th>部门</th>
        <th>盘点日期</th>
        <th>商品编码</th>
        <th>商品名称</th>
        <th>期初数量</th>
        <th>期末数量</th>
        <th>采购数量</th>
        <th>销售数量</th>
        <th>退货数量</th>
        <th>过期数量</th>
        <th>作废数量</th>
        <th>备注</th>
    </tr>
    </thead>

    <tfoot>
    <tr>
        <th>编号</th>
        <th>部门</th>
        <th>盘点日期</th>
        <th>商品编码</th>
        <th>商品名称</th>
        <th>期初数量</th>
        <th>期末数量</th>
        <th>采购数量</th>
        <th>销售数量</th>
        <th>退货数量</th>
        <th>过期数量</th>
        <th>作废数量</th>
        <th>备注</th>
    </tr>
    </tfoot>
</table>
<script type="text/javascript" language="javascript" class="init">


    $(document).ready(function() {
        editor = new $.fn.dataTable.Editor( DataTable.editorConfig('${moduleName}',[ {
            label: "部门:",
            name: "department"
        }, {
            label: "盘点日期:",
            name: "checkDate"
        }, {
            label: "商品编码:",
            name: "merchandiseId"
        }, {
            label: "商品名称:",
            name: "merchandiseName"
        }, {
            label: "期初数量:",
            name: "beginningNumber"
        }, {
            label: "期末数量:",
            name: "endingNumber"
        }, {
            label: "采购数量:",
            name: "purchaseNumber"
        }, {
            label: "销售数量:",
            name: "saleNumber"
        }, {
            label: "退货数量:",
            name: "returnNumber"
        }, {
            label: "过期数量:",
            name: "expireNumber"
        }, {
            label: "作废数量:",
            name: "voidedNumber"
        }, {
            label: "备注:",
            name: "memo"
        }
        ]) );


        table = $('#dataTable').DataTable( DataTable.dataTableConfig('${moduleName}',[
            { data: "id"},
            { data: "department" },
            { data: "checkDate" },
            { data: "merchandiseId" },
            { data: "merchandiseName" },
            { data: "beginningNumber" },
            { data: "endingNumber" },
            { data: "purchaseNumber" },
            { data: "saleNumber" },
            { data: "returnNumber" },
            { data: "expireNumber" },
            { data: "voidedNumber" },
            { data: "memo" }
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