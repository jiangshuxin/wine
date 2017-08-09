
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
                name: "status"
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
                { data: "amount"},
                { data: "payAmount"},
                { data: "status"},
                { data: "payTime"},
                { data: "logisticsCompany"}
            ],[
                { extend: "create", editor: editor },
                { extend: "edit",   editor: editor },
                { extend: "remove", editor: editor },
                {
                    extend: "selectedSingle",
                    text: "订单",
                    action: function ( e, dt, node, config ) {
                        var selData = table.row('.selected').data();
                        var amount = window.prompt('请输入打款金额',0);
                        //location.href = "${rc.contextPath}/uploadResult/list?refModuleName="+selData.moduleName+"&uploadId="+selData.id;
                        alert(amount);
                    }
                }
            ],{initComplete: function ()
            {

            }}) );

            DataTable.enableColumnSearch(table);

            editor.on( 'submitSuccess', function ( e, json, data ) {
                if(data.logisticsSeqs){
                    alert('触发订单完成');
                }
            } );
        } );



    </script>


</@dt.init>