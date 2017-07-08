
<@dt.init mode=mode!'normal'>
    <@dt.uploadFile moduleName=moduleName/>

    <table id="dataTable" class="display" cellspacing="0" width="100%">
        <thead>
        <tr>
        	<th>序号</th>
            <th>编码</th>
            <th>名称</th>
            <th>项目负责人</th>
            <th>立项日期</th>
            <th>部门分类</th>
            <th>立项规则</th>
            <th>备注发票类型</th>
        </tr>
        </thead>

        <tfoot>
        <tr>
        	<th>序号</th>
            <th>编码</th>
            <th>名称</th>
            <th>项目负责人</th>
            <th>立项日期</th>
            <th>部门分类</th>
            <th>立项规则</th>
            <th>备注发票类型</th>
        </tr>
        </tfoot>
    </table>
    <script type="text/javascript" language="javascript" class="init">


        $(document).ready(function() {
            editor = DataTable.Editor.newInstance('${moduleName}',[ {
                label: "序号:",
                name: "sortNo"
            }, {
                label: "编码:",
                name: "id"
            }, {
                label: "名称:",
                name: "name"
            }, {
                label: "项目负责人:",
                name: "principal"
            }, {
                label: "立项日期:",
                name: "setupDate"
            }, {
                label: "部门分类:",
                name: "depPartition"
            }, {
                label: "立项规则:",
                name: "setupRule"
            }, {
                label: "备注发票类型:",
                name: "memo"
            }
            ]);

            table = $('#dataTable').DataTable( DataTable.dataTableConfig('${moduleName}',[
                { data: "sortNo"},
                { data: "id"},
                { data: "name" },
                { data: "principal"},
                { data: "setupDate" },
                { data: "depPartition" },
                { data: "setupRule"},
                { data: "memo" }
            ],[
            <#if !mode??>
                <#assign mode="normal">
            </#if>
            <#if mode=='normal'>
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
                },
                {
                    extend: "create",
                    text: "数据文件上传",
                    action: function ( e, dt, node, config ) {
                        $('#uploadModal').modal();
                    }
                }
            </#if>
            ],{initComplete: function ()
            {
            },columnDefs: [
                { "width": "7%", "targets": 0 }
            ]}) );

            DataTable.enableColumnSearch(table);

        } );



    </script>


</@dt.init>