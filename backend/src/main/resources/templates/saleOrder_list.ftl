<@dt.init>

<table id="dataTable" class="display" cellspacing="0" width="100%">
    <thead>
    <tr>
        <th>编号</th>
        <th>部门</th>
        <th>商品编码</th>
        <th>商品名称</th>
        <th>进价</th>
        <th>售价</th>
        <th>数量</th>
        <th>订单流水号</th>
        <th>订单那生成时间</th>
        <th>订单金额</th>
        <th>供应商编码</th>
        <th>供应商名称</th>
        <th>供应商发货时间</th>
        <th>供应商结算日期</th>
        <th>支付方式名称</th>
        <th>支付方式编码</th>
        <th>支付机构编码</th>
        <th>支付机构名称</th>
        <th>支付流水号</th>
        <th>支付时间</th>
        <th>支付金额</th>
        <th>支付结算日期</th>
        <th>支付费用</th>
        <th>优惠券编码</th>
        <th>优惠券名称</th>
        <th>优惠券金额</th>
        <th>优惠券99成本</th>
        <th>兑换券编码</th>
        <th>兑换券名称</th>
        <th>兑换券金额</th>
        <th>兑换券99成本</th>
        <th>订单结算日期</th>
        <th>渠道编码</th>
        <th>渠道名称</th>
        <th>渠道费用</th>
        <th>应用编码</th>
        <th>应用名称</th>
        <th>业务类型编码</th>
        <th>业务类型名称</th>
        <th>客户编码</th>
        <th>客户名称</th>
        <th>客户支付费用</th>
        <th>用户Id</th>
        <th>用户名称</th>
        <th>手机号</th>
        <th>收货人</th>
        <th>收获手机号</th>
        <th>收货账号</th>
        <th>上传记录编号</th>
    </tr>
    </thead>

    <tfoot>
    <tr>
        <th>编号</th>
        <th>部门</th>
        <th>商品编码</th>
        <th>商品名称</th>
        <th>进价</th>
        <th>售价</th>
        <th>数量</th>
        <th>订单流水号</th>
        <th>订单那生成时间</th>
        <th>订单金额</th>
        <th>供应商编码</th>
        <th>供应商名称</th>
        <th>供应商发货时间</th>
        <th>供应商结算日期</th>
        <th>支付方式名称</th>
        <th>支付方式编码</th>
        <th>支付机构编码</th>
        <th>支付机构名称</th>
        <th>支付流水号</th>
        <th>支付时间</th>
        <th>支付金额</th>
        <th>支付结算日期</th>
        <th>支付费用</th>
        <th>优惠券编码</th>
        <th>优惠券名称</th>
        <th>优惠券金额</th>
        <th>优惠券99成本</th>
        <th>兑换券编码</th>
        <th>兑换券名称</th>
        <th>兑换券金额</th>
        <th>兑换券99成本</th>
        <th>订单结算日期</th>
        <th>渠道编码</th>
        <th>渠道名称</th>
        <th>渠道费用</th>
        <th>应用编码</th>
        <th>应用名称</th>
        <th>业务类型编码</th>
        <th>业务类型名称</th>
        <th>客户编码</th>
        <th>客户名称</th>
        <th>客户支付费用</th>
        <th>用户Id</th>
        <th>用户名称</th>
        <th>手机号</th>
        <th>收货人</th>
        <th>收获手机号</th>
        <th>收货账号</th>
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
            label: "商品编码:",
            name: "merchandiseId"
        }, {
            label: "商品名称:",
            name: "merchandiseName"
        }, {
            label: "进价:",
            name: "priceIn"
        }, {
            label: "售价:",
            name: "priceSale"
        }, {
            label: "数量:",
            name: "merchandiseNumber"
        }, {
            label: "订单流水号:",
            name: "orderSerialNo"
        }, {
            label: "订单生成时间:",
            name: "orderGenerateTime"
        }, {
            label: "订单金额:",
            name: "orderAmount"
        }, {
            label: "供应商编码:",
            name: "supplierId"
        }, {
            label: "供应商名称:",
            name: "supplierName"
        }, {
            label: "供应商发货时间:",
            name: "supplierDispatchTime"
        }, {
            label: "供应商结算日期:",
            name: "supplierSettlementDate"
        }, {
            label: "支付方式名称:",
            name: "paymentMethodName"
        }, {
            label: "支付方式编码:",
            name: "paymentMethodId"
        }, {
            label: "支付机构编码:",
            name: "paymentAgencyId"
        }, {
            label: "支付机构名称:",
            name: "paymentAgencyName"
        }, {
            label: "支付流水号:",
            name: "paymentSerialNo"
        }, {
            label: "支付时间:",
            name: "paymentTime"
        }, {
            label: "支付金额:",
            name: "paymentAmount"
        }, {
            label: "支付结算日期:",
            name: "paymentSettlementDate"
        }, {
            label: "支付费用:",
            name: "paymentFee"
        }, {
            label: "优惠券编码:",
            name: "couponId"
        }, {
            label: "优惠券名称:",
            name: "couponName"
        }, {
            label: "优惠券金额:",
            name: "couponAmount"
        }, {
            label: "优惠券99成本:",
            name: "couponCost99"
        }, {
            label: "兑换券编码:",
            name: "exchangeId"
        }, {
            label: "兑换券，名称:",
            name: "exchangeName"
        }, {
            label: "兑换券金额:",
            name: "exchangeAmount"
        }, {
            label: "兑换券99成本:",
            name: "exchangeCost99"
        }, {
            label: "订单结算日期:",
            name: "orderSettlementDate"
        }, {
            label: "渠道编码:",
            name: "channelId"
        }, {
            label: "渠道名称:",
            name: "channelName"
        }, {
            label: "渠道费用:",
            name: "channelFee"
        }, {
            label: "应用编码:",
            name: "appId"
        }, {
            label: "应用名称:",
            name: "appName"
        }, {
            label: "业务类型编码:",
            name: "businessId"
        }, {
            label: "业务类型名称:",
            name: "businessName"
        }, {
            label: "客户编码:",
            name: "customerId"
        }, {
            label: "客户名称:",
            name: "customerName"
        }, {
            label: "客户支付费用:",
            name: "customerFee"
        }, {
            label: "用户Id:",
            name: "userId"
        }, {
            label: "用户名:",
            name: "userName"
        }, {
            label: "手机号:",
            name: "userPhone"
        }, {
            label: "收货人:",
            name: "receivingName"
        }, {
            label: "收获电话:",
            name: "receivingPhone"
        }, {
            label: "收货账号:",
            name: "receivingAccount"
        }
        ]) );


        table = $('#dataTable').DataTable( DataTable.dataTableConfig('${moduleName}',[
            { data: "id"},
            { data: "department" },
            { data: "merchandiseId" },
            { data: "merchandiseName" },
            { data: "priceIn" },
            { data: "priceSale" },
            { data: "merchandiseNumber" },
            { data: "orderSerialNo" },
            { data: "orderGenerateTime" },
            { data: "orderAmount" },
            { data: "supplierId" },
            { data: "supplierName" },
            { data: "supplierDispatchTime" },
            { data: "supplierSettlementDate" },
            { data: "paymentMethodName" },
            { data: "paymentMethodId" },
            { data: "paymentAgencyId" },
            { data: "paymentAgencyName" },
            { data: "paymentSerialNo" },
            { data: "paymentTime" },
            { data: "paymentAmount" },
            { data: "paymentSettlementDate" },
            { data: "paymentFee" },
            { data: "couponId" },
            { data: "couponName" },
            { data: "couponAmount" },
            { data: "couponCost99" },
            { data: "exchangeId" },
            { data: "exchangeName" },
            { data: "exchangeAmount" },
            { data: "exchangeCost99" },
            { data: "orderSettlementDate" },
            { data: "channelId" },
            { data: "channelName" },
            { data: "channelFee" },
            { data: "appId" },
            { data: "appName" },
            { data: "businessId" },
            { data: "businessName" },
            { data: "customerId" },
            { data: "customerName" },
            { data: "customerFee" },
            { data: "userId" },
            { data: "userName" },
            { data: "userPhone" },
            { data: "receivingName" },
            { data: "receivingPhone" },
            { data: "receivingAccount" },
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