<@dt.init>

<table id="dataTable" class="display" cellspacing="0" width="100%">
    <thead>
    <tr>
        <th>编号</th>
        <th>部门</th>
        <th>对账日期</th>
        <th>供应商编码</th>
        <th>供应商名称</th>
        <th>账号</th>
        <th>账号名称</th>
        <th>期初金额</th>
        <th>期末金额</th>
        <th>充值笔数</th>
        <th>充值金额</th>
        <th>支付笔数</th>
        <th>支付金额</th>
        <th>退款笔数</th>
        <th>退款金额</th>
    </tr>
    </thead>

    <tfoot>
    <tr>
        <th>编号</th>
        <th>部门</th>
        <th>对账日期</th>
        <th>供应商编码</th>
        <th>供应商名称</th>
        <th>账号</th>
        <th>账号名称</th>
        <th>期初金额</th>
        <th>期末金额</th>
        <th>充值笔数</th>
        <th>充值金额</th>
        <th>支付笔数</th>
        <th>支付金额</th>
        <th>退款笔数</th>
        <th>退款金额</th>
    </tr>
    </tfoot>
</table>
<script type="text/javascript" language="javascript" class="init">


    $(document).ready(function() {
        editor = new $.fn.dataTable.Editor( DataTable.editorConfig('${moduleName}',[ {
            label: "部门:",
            name: "department"
        }, {
            label: "对账日期:",
            name: "checkDate"
        }, {
            label: "供应商编码:",
            name: "supplierId"
        }, {
            label: "供应商名称:",
            name: "supplierName"
        }, {
            label: "账号:",
            name: "accountId"
        }, {
            label: "账号名称:",
            name: "accountName"
        }, {
            label: "期初金额:",
            name: "beginningBalance"
        }, {
            label: "期末金额:",
            name: "endingBalance"
        }, {
            label: "充值笔数:",
            name: "depositNumber"
        }, {
            label: "充值金额:",
            name: "depositSum"
        }, {
            label: "支付笔数:",
            name: "payNumber"
        }, {
            label: "支付金额:",
            name: "paySum"
        }, {
            label: "退款笔数:",
            name: "returnNumber"
        }, {
            label: "退款金额:",
            name: "returnSum"
        }
        ]) );


        table = $('#dataTable').DataTable( DataTable.dataTableConfig('${moduleName}',[
            { data: "id"},
            { data: "department" },
            { data: "checkDate" },
            { data: "supplierId" },
            { data: "supplierName" },
            { data: "accountId" },
            { data: "accountName" },
            { data: "beginningBalance" },
            { data: "endingBalance" },
            { data: "depositNumber" },
            { data: "depositSum" },
            { data: "payNumber" },
            { data: "paySum" },
            { data: "returnNumber" },
            { data: "returnSum" }
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