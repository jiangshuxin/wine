
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

        $(document).ready(function() {
            editor = DataTable.Editor.newInstance('${moduleName}',[ {
                label: "酒庄编号:",
                name: "merchantId",
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
        } );



    </script>


</@dt.init>