<@dt.init>

<table id="dataTable" class="display" cellspacing="0" width="100%">
    <thead>
    <tr>
        <th>编号</th>
        <th>部门</th>
        <th>盘点日期</th>
        <th>优惠券编码</th>
        <th>优惠券名称</th>
        <th>项目编号</th>
        <th>期初数量</th>
        <th>新建数量</th>
        <th>剩余数量</th>
        <th>使用数量</th>
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
        <th>优惠券编码</th>
        <th>优惠券名称</th>
        <th>项目编号</th>
        <th>期初数量</th>
        <th>新建数量</th>
        <th>剩余数量</th>
        <th>使用数量</th>
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
            label: "优惠券编码:",
            name: "couponId"
        }, {
            label: "优惠券名称:",
            name: "couponName"
        }, {
            label: "项目编号:",
            name: "projectId"
        }, {
            label: "期初数量:",
            name: "beginningNumber"
        }, {
            label: "新建数量:",
            name: "createNumber"
        }, {
            label: "剩余数量:",
            name: "remainingNumber"
        }, {
            label: "使用数量:",
            name: "usedNumber"
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
            { data: "couponId" },
            { data: "couponName" },
            { data: "projectId" },
            { data: "beginningNumber" },
            { data: "createNumber" },
            { data: "remainingNumber" },
            { data: "usedNumber" },
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