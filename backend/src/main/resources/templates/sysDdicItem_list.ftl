
<@dt.init titles=["条目名称:"+cnName!""]>

    <table id="dataTable" class="display" cellspacing="0" width="100%">
        <thead>
        <tr>
            <th>编号</th>
            <th>条目编码</th>
            <th>条目值</th>
            <th>排序号</th>
            <th>所属目录</th>
            <th>扩展属性1</th>
            <th>扩展属性2</th>
            <th>描述</th>
        </tr>
        </thead>

    </table>
    <script type="text/javascript" language="javascript" class="init">


        $(document).ready(function() {
            editor = new $.fn.dataTable.Editor( DataTable.editorConfig('${moduleName}',[ {
                label: "条目编码:",
                name: "itemKey"
            }, {
                label: "条目值:",
                name: "itemValue"
            }, {
                label: "排序号:",
                name: "sortNo"
            }, {
                label: "所属目录:",
                name: "categoryId",
                def:'${categoryId!""}',
                readonly:true
            }, {
                label: "扩展属性1:",
                name: "attribute1"
            }, {
                label: "扩展属性2:",
                name: "attribute2"
            }, {
                label: "描述:",
                name: "description"
            }
            ]) );


            table = $('#dataTable').DataTable( DataTable.dataTableConfig('${moduleName}',[
                { data: "id"},
                { data: "itemKey" },
                { data: "itemValue" },
                { data: "sortNo" },
                { data: "categoryId",searchValue: '${categoryId!""}' },
                { data: "attribute1"},
                { data: "attribute2"},
                { data: "description"}
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

            },columnDefs: [
                { "width": "7%", "targets": 0 }
            ]}) );


            editor.on( 'open', function ( e, json, data ) {
                console.log('open')
                DataTable.Editor.readonly('categoryId');
            } );
        } );



    </script>


</@dt.init>