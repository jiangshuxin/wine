
<@dt.init>

    <table id="dataTable" class="display" cellspacing="0" width="100%">
        <thead>
        <tr>
            <th>编号</th>
            <th>名称</th>
            <th>报表分类名称</th>
            <th>部门</th>
            <th>报表周期</th>
            <th>时间</th>
            <th>文件相对路径</th>
<#--            <th>生成时间</th>
-->
			<th>placeholder1</th>
			<th>placeholder2</th>
            <th>操作</th>
        </tr>
        </thead>

        <tfoot>
        <tr>
            <th>编号</th>
            <th>名称</th>
            <th>报表分类名称</th>
            <th>部门</th>
            <th>报表周期</th>
            <th>时间</th>
            <th>文件相对路径</th>
<#--            <th>生成时间</th>
-->
            <th>placeholder1</th>
			<th>placeholder2</th>
            <th>操作</th>
        </tr>
        </tfoot>
    </table>
    <script type="text/javascript" language="javascript" class="init">


        $(document).ready(function() {
            editor = new $.fn.dataTable.Editor( DataTable.editorConfig('${moduleName}',[ 
            {
                label: "名称:",
                name: "name"
            }, {
                label: "报表分类名称:",
                name: "reportName"
            }, {
                label: "部门:",
                name: "department"
            }, {
                label: "周期类型:",
                name: "periodTypeName"
            }, {
                label: "时间:",
                name: "period"
            }, {
                label: "文件相对路径:",
                name: "relativeFilePath"
            }
//            , {
//                label: "生成时间:",
//                name: "generateTime"
//            }
			, {
                    label: "excel名称:",
                    name: "moduleName"
             }, {
                    label: "周期类型:",
                    name: "periodType"
             }
            ]) );
            
            table = $('#dataTable').DataTable( DataTable.dataTableConfig('${moduleName}',[
                { data: "id"},
                { data: "simpleName" },
                { data: "reportName",searchType:"select",ddicRef:"moduleName" },
                { data: "department"},
                { data: "periodTypeName",searchType:"select",ddicRef:"periodType" },
                { data: "period",searchType:"date"},
                { data: "relativeFilePath" },
                { data: "moduleName", name: "moduleName" },
            	{ data: "periodType", name: "periodType" },
//                { data: "generateTime"},
                { data: null, render: function ( data, type, row ) {
                    var url = '${webPath}'+data.relativeFilePath+data.name;
                    return '<a href="'+url+'">下载</a>';
                } }
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

            }, "columnDefs": [ 
	         { "visible": false, "targets": [6, 7, 8] }
	        ]
            , order: [[ 0, 'desc' ]]
            }) );

            DataTable.enableColumnSearch(table);
        } );



    </script>


</@dt.init>