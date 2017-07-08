
<@dt.init>

    <table id="dataTable" class="display" cellspacing="0" width="100%">
        <thead>
        <tr>
            <th>编号</th>
            <th>模块名称</th>
            <th>规则类型</th>
            <th>内容</th>
            <th>描述</th>
        </tr>
        </thead>

        <tfoot>
        <tr>
            <th>编号</th>
            <th>模块名称</th>
            <th>内容</th>
            <th>描述</th>
            <th>规则类型</th>
        </tr>
        </tfoot>
    </table>
    <script type="text/javascript" language="javascript" class="init">


        $(document).ready(function() {
            editor = new $.fn.dataTable.Editor( DataTable.editorConfig('${moduleName}',[ {
                label: "模块名称:",
                name: "moduleName"
            }, {
                label: "规则内容:",
                name: "content",
                type: "textarea"
            }, {
                label: "规则类型:",
                name: "ruleType",
                type:  "select",
                options: [
                    { label: "Spring EL", value: "SpEL" },
                    { label: "Spring Bean",  value: "SpBean" }
                ]
            }, {
                label: "描述:",
                name: "desc",
                type: "textarea"
            }
            ]
            ) );

            table = $('#dataTable').DataTable( DataTable.dataTableConfig('${moduleName}',[
                { data: "id"},
                { data: "moduleName"},
                { data: "ruleType" },
                { data: "content" },
                { data: "desc"}
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
        } );



    </script>


</@dt.init>