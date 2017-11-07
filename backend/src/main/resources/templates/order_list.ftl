
<@dt.init>

    <table id="dataTable" class="display" cellspacing="0" width="100%">
        <thead>
        <tr>
            <th>订单编号</th>
            <th>订单时间</th>
            <th>用户ID</th>
            <th>酒庄编号</th>
            <th>商品数量</th>
            <th>订单金额（元）</th>
            <th>支付金额（元）</th>
            <th>订单状态</th>
            <th>支付时间</th>
            <th>快递公司名称</th>
        </tr>
        </thead>

        <tfoot>
        <tr>
            <th>订单编号</th>
            <th>订单时间</th>
            <th>用户ID</th>
            <th>酒庄编号</th>
            <th>商品数量</th>
            <th>订单金额（元）</th>
            <th>支付金额（元）</th>
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
                label: "订单编号:",
                name: "id",
                type: "readonly"
            }, {
                label: "订单时间:",
                name: "time",
                type: "readonly"
            //}, {
            //    label: "买家用户ID:",
            //    name: "userId",
            //    type: "readonly"
            }, {
                label: "酒庄编号:",
                name: "merchantId",
                type: "readonly"
            }, {
                label: "订单金额（元）:",
                name: "amount",
                type: "readonly"
            //}, {
            //    label: "商品数量:",
            //    name: "mdseCount",
            //    type: "readonly"
            //}, {
            //    label: "支付金额:",
            //    name: "payAmount",
            //    type: "readonly"
            //},{
            //    label: "订单状态:",
            //    name: "status",
            //    type:'select',
            //    options:orderStatusDdic
            //},{
            //    label: "支付时间:",
            //    name: "payTime",
            //    type: "readonly"
            //},{
            //    label: "支付流水号:",
            //    name: "paySeqs",
            //    type: "readonly"
            //},{
            //    label: "支付二维码图片:",
            //    name: "payPic",
            //    type: "readonly"

            },{
                label: "发票信息:",
                name: "invoiceInfo",
                type: "readonly"
            },{
                label: "收货人:",
                name: "receiver",
                type: "readonly"
            },{
                label: "收货人电话:",
                name: "phone",
                type: "readonly"
            },{
                label: "省市区:",
                name: "province",
                type: "readonly"
            }, {
                label: "详细地址:",
                name: "address",
                type: "readonly"
            },{
                label: "买家备注:",
                name: "comment",
                type: "readonly"
            },{
                label: "快递公司名称:",
                name: "logisticsCompany"
            }, {
                label: "快递单号:",
                name: "logisticsSeqs"
            }

            ],{

            });

            table = $('#dataTable').DataTable( DataTable.dataTableConfig('${moduleName}',[
                { data: "id"},
                { data: "time" },
                { data: "userId" },
                { data: "merchantId"},
                { data: "mdseCount"},
                { data: "amount", isYuan:"true",render: function ( data, type, row ) {
                    if(!isNaN(row.amount)){
                        return "￥" + (new Number(row.amount)/100);
                    }
                }},
                { data: "payAmount", isYuan:"true",render: function ( data, type, row ) {
                    if(!isNaN(row.payAmount)){
                        return "￥" + (new Number(row.payAmount)/100);
                    }
                }},
                { data: "statusName",searchType:"select",ddic:"orderStatus",ddicRef:"status"},
                { data: "payTime"},
                { data: "logisticsCompany"},
                { data: "status"}
            ],[
                { extend: "edit", editor: editor, text: "录入发货信息"},
                //{ extend: "remove", editor: editor },
                {
                    extend: 'collection',
                    text: '导出',
                    autoClose: true,
                    buttons: [
                        {
                            extend: 'excel',
                            exportOptions: {
                                columns: ':visible'
                            }
                        },
                        {
                            extend: 'csv',
                            exportOptions: {
                                columns: ':visible'
                            }
                        },
                        {
                            extend: 'pdf',
                            exportOptions: {
                                columns: ':visible'
                            }
                        }
                    ]
                }
            ],{initComplete: function ()
            {

            },"columnDefs": [
                { "visible": false, "targets": [10] },
                { "searchable": false, "targets": [6,7] }
            ]
                , order: [[ 0, 'desc' ]]}) );

            DataTable.enableColumnSearch(table);

            editor.on( 'open', function ( e, json, data ) {
                var amount = new Number(editor.get('amount'));
                editor.set('amount',amount/100);
                var payAmount = new Number(editor.get('payAmount'));
                editor.set('payAmount',payAmount/100);
            } );
            editor.on( 'preSubmit', function ( e, json, action ) {
                var data = json.data;
                for(var k in data){
                    data[k].amount = data[k].amount*100;
                    data[k].payAmount = data[k].payAmount*100;
                }
            } );

            editor.on( 'submitSuccess', function ( e, json, data ) {
                if(data.logisticsSeqs){
                    var paramObj = {
                        orderId: data.id,
                        status: 3
                    };
                    var jsonObj = Invoker.post('/backend/order/updateStatus',paramObj);
                    if(jsonObj.success && jsonObj.data > 0){
                        alert('录入发货信息成功，订单已完成！');
                        DataTable.reload(table);
                    }else{
                        alert(jsonObj.errorMsg);
                    }
                }
            } );
        } );



    </script>


</@dt.init>