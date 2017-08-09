<@dt.init>

    <table id="dataTable" class="display" cellspacing="0" width="100%">
        <thead>
        <tr>
            <th>编号</th>
            <th>栏目名</th>
            <th>中文名</th>
            <th>描述</th>
        </tr>
        </thead>

        <tfoot>
        <tr>
            <th>编号</th>
            <th>栏目名</th>
            <th>中文名</th>
            <th>描述</th>
        </tr>
        </tfoot>
    </table>

    <script type="text/javascript" language="javascript" class="init">


        $(document).ready(function() {
            editor = new $.fn.dataTable.Editor( DataTable.editorConfig('${moduleName}',[ {
                label: "简称:",
                name: "name"
            }, {
                label: "中文名:",
                name: "cnName"
            }, {
                label: "描述:",
                name: "description"
            }
            ]) );


            table = $('#dataTable').DataTable( DataTable.dataTableConfig('${moduleName}',[
                { data: "id"},
                { data: "name" },
                { data: "cnName" },
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
                },
                {
                    extend: "selectedSingle",
                    text: "关联明细",
                    action: function ( e, dt, node, config ) {
                        var indexSel = table.row( {selected: true} ).index();
                        var cnName = table.row(indexSel).data().cnName;
                        location.href = DataTable.CONTEXT_PATH + "/sysDdicItem/list?cnName="+cnName+"&categoryId="+table.row('.selected').data().id;
                    }
                }
            ],{initComplete: function ()
            {
            }}) );

            DataTable.enableColumnSearch(table);
        } );

    </script>
</@dt.init>