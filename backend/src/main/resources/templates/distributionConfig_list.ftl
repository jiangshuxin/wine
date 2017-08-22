
<@dt.init>

    <table id="dataTable" class="display" cellspacing="0" width="100%">
        <thead>
        <tr>
            <th>酒庄编号</th>
            <th>商品编号</th>
            <#--<th>分销金额</th>-->
            <th>一级返佣比例</th>
            <th>二级返佣比例</th>
            <th>三级返佣比例</th>
        </tr>
        </thead>

        <tfoot>
        <tr>
            <th>酒庄编号</th>
            <th>商品编号</th>
            <#--<th>分销金额</th>-->
            <th>一级返佣比例</th>
            <th>二级返佣比例</th>
            <th>三级返佣比例</th>
        </tr>
        </tfoot>
    </table>
    <script type="text/javascript" language="javascript" class="init">
        var editor;
        var merchantData = Select.init('/backend/merchant/queryAll');

        $(document).ready(function() {
            editor = DataTable.Editor.newInstance('${moduleName}',[ {
                label: "酒庄编号:",
                name: "merchantId",
                type:  "select",
                options:merchantData
            }, {
                label: "商品编号，可不填，代表该酒庄的默认配置:",
                name: "mdseId"
            }/*, {
                label: "分销金额，单位分:",
                name: "amount"
            }*/, {
                label: "一级返佣比例，0~100:",
                name: "rebate1"
            }, {
                label: "二级返佣比例，0~100:",
                name: "rebate2"
            }, {
                label: "三级返佣比例，0~100:",
                name: "rebate3"
            }
            ],{

            });

            table = $('#dataTable').DataTable( DataTable.dataTableConfig('${moduleName}',[
                { data: "merchantId"},
                { data: "mdseId" },
                //{ data: "amount" },
                { data: "rebate1"},
                { data: "rebate2"},
                { data: "rebate3"}
            ],[
                { extend: "create", editor: editor },
                { extend: "edit",   editor: editor },
                { extend: "remove", editor: editor }
            ],{initComplete: function ()
            {

            }}) );

            DataTable.enableColumnSearch(table);

            editor.on( 'preSubmit', function ( e, json, action ) {
                var data = json.data;
                for(var k in data){
                    if(!data[k].merchantId){
                        alert('请选择酒庄');
                        return false;
                    }
                    if(!data[k].mdseId || isNaN(data[k].mdseId)){
                        alert('商品编号必须是数字');
                        return false;
                    }
                    if(!data[k].rebate1 || isNaN(data[k].rebate1)){
                        alert('一级返佣比例必须是数字');
                        return false;
                    }
                    if(!data[k].rebate2 || isNaN(data[k].rebate2)){
                        alert('二级返佣比例必须是数字');
                        return false;
                    }
                    if(!data[k].rebate3 || isNaN(data[k].rebate3)){
                        alert('三级返佣比例必须是数字');
                        return false;
                    }
                }
            } );
        } );



    </script>


</@dt.init>