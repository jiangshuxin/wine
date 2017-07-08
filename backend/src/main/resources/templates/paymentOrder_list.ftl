<@dt.init>

<table id="dataTable" class="display" cellspacing="0" width="100%">
    <thead>
    <tr>
        <th>编号</th>
        <th>部门</th>
        <th>支付方式编码</th>
        <th>支付方式名称</th>
        <th>支付流水号</th>
        <th>支付机构流水号</th>
        <th>支付机构流名称</th>
        <th>支付商户编码</th>
        <th>交易金额</th>
        <th>交易类型</th>
        <th>手续费</th>
        <th>支付发起时间</th>
        <th>支付结束时间</th>
        <th>结算日期</th>
        <th>上传记录编号</th>
    </tr>
    </thead>

    <tfoot>
    <tr>
        <th>编号</th>
        <th>部门</th>
        <th>支付方式编码</th>
        <th>支付方式名称</th>
        <th>支付流水号</th>
        <th>支付机构流水号</th>
        <th>支付机构流名称</th>
        <th>支付商户编码</th>
        <th>交易金额</th>
        <th>交易类型</th>
        <th>手续费</th>
        <th>支付发起时间</th>
        <th>支付结束时间</th>
        <th>结算日期</th>
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
            label: "支付方式编码:",
            name: "paymentId"
        }, {
            label: "支付方式名称:",
            name: "paymentName"
        }, {
            label: "支付流水号:",
            name: "paymentSerialNo"
        }, {
            label: "支付机构流水号:",
            name: "paymentAgencySerialNo"
        }, {
            label: "支付机构名称:",
            name: "paymentAgencyName"
        }, {
            label: "支付商户编码:",
            name: "paymentMerchantId"
        }, {
            label: "交易金额:",
            name: "tradingAmount"
        }, {
            label: "交易类型:",
            name: "tradingType"
        }, {
            label: "手续费:",
            name: "tradingFee"
        }, {
            label: "支付发起时间:",
            name: "startTime"
        }, {
            label: "支付完成时间:",
            name: "finishTime"
        }, {
            label: "结算日期:",
            name: "settlementDate"
        }
        ]) );


        table = $('#dataTable').DataTable( DataTable.dataTableConfig('${moduleName}',[
            { data: "id"},
            { data: "department" },
            { data: "paymentId" },
            { data: "paymentName"},
            { data: "paymentSerialNo" },
            { data: "paymentAgencySerialNo" },
            { data: "paymentAgencyName" },
            { data: "paymentMerchantId"},
            { data: "tradingAmount"},
            { data: "tradingType"},
            { data: "tradingFee"},
            { data: "startTime" },
            { data: "finishTime" },
            { data: "settlementDate"},
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