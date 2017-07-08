
<@dt.init>

    <@dt.uploadFile moduleName=moduleName/>
    <@dt.buildRelationship moduleName=moduleName/>

    <table id="dataTable" class="display" cellspacing="0" width="100%">
        <thead>
        <tr>
            <th>编号</th>
            <th>名称</th>
            <th>状态</th>
            <th>描述</th>
            <th>统一编码</th>
            <th>统一名称</th>
            <th>上传记录编号</th>
        </tr>
        </thead>

        <tfoot>
        <tr>
            <th>编号</th>
            <th>名称</th>
            <th>状态</th>
            <th>描述</th>
            <th>统一编码</th>
            <th>统一名称</th>
            <th>上传记录编号</th>
        </tr>
        </tfoot>
    </table>
    <script type="text/javascript" language="javascript" class="init">


        $(document).ready(function() {
            editor = DataTable.Editor.newInstance('${moduleName}',[ {
                label: "编码:",
                name: "id"
            }, {
                label: "名称:",
                name: "name"
            }, {
                label: "状态:",
                name: "status"
            }, {
                label: "描述:",
                name: "description"
            }, {
                label: "统一编号:",
                name: "unifiedId"
            }
            ]);


            table = $('#dataTable').DataTable( DataTable.dataTableConfig('${moduleName}',[
                { data: "id"},
                { data: "name" },
                { data: "status" },
                { data: "description"},
                { data: "unifiedId",name:"unifiedId"<#if unifiedId??>,searchValue:"${unifiedId?trim}"</#if><#if nullable??>,nullable:${nullable?trim}</#if> },
                { data: "unifiedName"},
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
                },
                {
                    extend: "create",
                    text: "数据文件上传",
                    action: function ( e, dt, node, config ) {
                        $('#uploadModal').modal();
                    }
                },
                {
                    extend: "edit",
                    text: "关联财务供应商",
                    action: function ( e, dt, node, config ) {
                        $('#financeModal').modal();
                        window.open('${rc.contextPath}/financeSupplier/list?mode=simple','targetWindow');
                    }
                }
                ,{
                    extend: "create",
                    text: "查看未关联财务供应商",
                    action: function ( e, dt, node, config ) {
                        window.open('${rc.contextPath}/baseSupplier/list?unifiedId=&nullable=true','_self');
                    }
                }
                ,{
                    extend: "create",
                    text: "查看已关联财务供应商",
                    action: function ( e, dt, node, config ) {
                        window.open('${rc.contextPath}/baseSupplier/list?unifiedId=&nullable=false','_self');
                    }
                }
            ],{initComplete: function ()
            {
            }}) );

            DataTable.enableColumnSearch(table);

            editor.on( 'open', function ( e, json, data ) {
                editor.hide('unifiedId');
            } );

            <#if unifiedId??>
                DataTable.searchByColumn(table,'unifiedId','${unifiedId}');
            </#if>
            <#if uploadId??>
                DataTable.searchByColumn(table,'uploadId','${uploadId}');
            </#if>
        } );



    </script>


</@dt.init>