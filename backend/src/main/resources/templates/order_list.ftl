
<@dt.init>

    <table id="dataTable" class="display" cellspacing="0" width="100%">
        <thead>
        <tr>
            <th>编号</th>
            <th>订单时间</th>
            <th>用户ID</th>
            <th>酒庄编号</th>
            <th>商品数量</th>
            <th>订单金额</th>
            <th>支付金额</th>
            <th>订单状态</th>
            <th>支付时间</th>
            <th>快递公司名称</th>
        </tr>
        </thead>

        <tfoot>
        <tr>
            <th>编号</th>
            <th>订单时间</th>
            <th>用户ID</th>
            <th>酒庄编号</th>
            <th>商品数量</th>
            <th>订单金额</th>
            <th>支付金额</th>
            <th>订单状态</th>
            <th>支付时间</th>
            <th>快递公司名称</th>
        </tr>
        </tfoot>
    </table>
    <script type="text/javascript" language="javascript" class="init">
        var editor;
        var orderStatusDdic = Ddic.show('orderStatus');

        $(document).ready(function() {
            editor = DataTable.Editor.newInstance('${moduleName}',[ {
                label: "编号:",
                name: "id",
            }, {
                label: "订单时间:",
                name: "time"
            }, {
                label: "买家用户ID:",
                name: "userId"
            }, {
                label: "酒庄编号:",
                name: "merchantId"
            }, {
                label: "订单金额:",
                name: "amount"
            }, {
                label: "商品数量:",
                name: "mdseCount"
            }, {
                label: "支付金额:",
                name: "payAmount"
            },{
                label: "订单状态:",
                name: "status",
                type:'select',
                options:orderStatusDdic
            },{
                label: "支付时间:",
                name: "payTime"
            },{
                label: "支付流水号:",
                name: "paySeqs"
            },{
                label: "支付二维码图片:",
                name: "payPic"
            },{
                label: "买家备注:",
                name: "comment"
            },{
                label: "快递公司名称:",
                name: "logisticsCompany"
            },{
                label: "快递单号:",
                name: "logisticsSeqs"
            },{
                label: "发票信息:",
                name: "invoiceInfo"
            },{
                label: "收货人:",
                name: "receiver"
            },{
                label: "收货人电话:",
                name: "phone"
            },{
                label: "省市区:",
                name: "province"
            }, {
                label: "详细地址:",
                name: "address"
            }
            ],{

            });

            table = $('#dataTable').DataTable( DataTable.dataTableConfig('${moduleName}',[
                { data: "id"},
                { data: "time" },
                { data: "userId" },
                { data: "merchantId"},
                { data: "mdseCount"},
                { data: "amount",render: function ( data, type, row ) {
                    if(!isNaN(row.amount)){
                        return new Number(row.amount)/100;
                    }
                }},
                { data: "payAmount",render: function ( data, type, row ) {
                    if(!isNaN(row.payAmount)){
                        return new Number(row.payAmount)/100;
                    }
                }},
                { data: "statusName",searchType:"select",ddic:"orderStatus",ddicRef:"status"},
                { data: "payTime"},
                { data: "logisticsCompany"},
                { data: "status"}
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

            },"columnDefs": [
                { "visible": false, "targets": [10] }
            ]
                , order: [[ 0, 'desc' ]]}) );

            DataTable.enableColumnSearch(table);

            editor.on( 'submitSuccess', function ( e, json, data ) {
                if(data.logisticsSeqs){
                    var paramObj = {
                        orderId: data.id,
                        status: 3
                    };
                    var jsonObj = Invoker.post('/backend/order/updateStatus',paramObj);
                    if(jsonObj.success && jsonObj.data > 0){
                        alert('更新成功，订单已完成！');
                        DataTable.reload(table);
                    }else{
                        alert(jsonObj.errorMsg);
                    }
                }
            } );
        } );



    </script>


</@dt.init>