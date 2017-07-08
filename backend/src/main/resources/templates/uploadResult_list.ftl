
<@dt.init titles=["校验结果","上传记录编号:"+uploadId!""]>

    <table id="dataTable" class="display" cellspacing="0" width="100%">
        <thead>
        <tr>
            <th>编号</th>
            <th>是否成功</th>
            <th>原始值</th>
            <th>关联校验规则编号</th>
            <th>关联上传记录编号</th>
        </tr>
        </thead>

        <tfoot>
        <tr>
            <th>编号</th>
            <th>是否成功</th>
            <th>原始值</th>
            <th>关联校验规则编号</th>
            <th>关联上传记录编号</th>
        </tr>
        </tfoot>
    </table>
    <script type="text/javascript" language="javascript" class="init">


        $(document).ready(function() {


            table = $('#dataTable').DataTable( DataTable.dataTableConfig('${moduleName}',[
                { data: "id"},
                { data: "success" },
                { data: "originalValue" },
                { data: "refRuleConfigId"},
                { data: "uploadId",searchValue: '${uploadId!""}' }
            ],[
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
                },
                {
                    extend: "selectedSingle",
                    text: "查看规则明细",
                    action: function ( e, dt, node, config ) {
                        var selData = table.row('.selected').data();
                        //location.href = "/uploadResult/list?refModuleName="+selData.moduleName+"&uploadId="+selData.id;
                    }
                }
            ],{initComplete: function ()
            {

            }}) );

            DataTable.enableColumnSearch(table);
        } );



    </script>


</@dt.init>