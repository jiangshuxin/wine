
<@dt.init>

<table id="dataTable" class="display" cellspacing="0" width="100%">
    <thead>
    <tr>
        <th>编码</th>
        <th>任务类型</th>
        <th>任务目的</th>
        <th>执行方法</th>
        <th>当前执行周期</th>
        <th>部门</th>
        <th>目标对象</th>
        <th>是否成功</th>
        <th>错误信息</th>
        <th>placeholder1</th>
        <th>placeholder2</th>
        <th>操作</th>
    </tr>
    </thead>

    <tfoot>
    <tr>
        <th>编码</th>
        <th>任务类型</th>
        <th>任务目的</th>
        <th>执行方法</th>
        <th>当前执行周期</th>
        <th>部门</th>
        <th>目标对象</th>
        <th>是否成功</th>
        <th>错误信息</th>
        <th>placeholder1</th>
        <th>placeholder2</th>
        <th>操作</th>
    </tr>
    </tfoot>
</table>
<script type="text/javascript" language="javascript" class="init">


    $(document).ready(function() {
    
        editor = new $.fn.dataTable.Editor( DataTable.editorConfig('${moduleName}',[ {
                    label: "编码:",
                    name: "id"
                }, {
                    label: "任务类型:",
                    name: "taskTypeName"
                }, {
                    label: "任务执行方法:",
                    name: "targetMethod"
                }, {
                    label: "任务执行参数:",
                    name: "targetParameter"
                }, {
                    label: "部门:",
                    name: "department"
                }, {
                    label: "目标对象:",
                    name: "target"
                }, {
                    label: "是否成功:",
                    name: "status"
                }, {
                    label: "错误信息:",
                    name: "errorMessage"
                }, {
                    label: "任务目的:",
                    name: "taskPurpose"
                }, {
                    label: "任务类型:",
                    name: "isSingle"
                }
                ]
                ) );

        table = $('#dataTable').DataTable( DataTable.dataTableConfig('${moduleName}',[
            { data: "id"},
            { data: "taskTypeName",searchType:"select",ddicRef:"isSingle" },
            { data: "taskPurposeName",searchType:"select",ddicRef:"taskPurpose" },
            { data: "targetMethod" },
            { data: "targetParameter"},
            { data: "department" },
            { data: "target" },
            { data: "status" },
            { data: "errorMessage"},
            { data: "taskPurpose",name: "taskPurpose" },
            { data: "isSingle",name: "isSingle"}
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
        ],{initComplete: function () {

        },
        "columnDefs": [ 
        {
            "targets": 11,
            "data": null,
            "defaultContent": "<button>重新执行</button>"
        }, { "visible": false, "targets": [ 9, 10 ] }
        ], order: [[ 4, 'desc' ]]
        }) );
        
        $('#dataTable tbody').on( 'click', 'button', function () {
    		var url = '${rc.contextPath}' + "/report/task/";
	        var row = table.row( $(this).parents('tr') ).data();
	        var data = { 
	        	"targetMethod": row.targetMethod, 
	        	"targetParameter": row.targetParameter,
	        	"department": row.department,
	        	"target": row.target,
	        	"taskPurpose": row.taskPurpose,
	        	"isSingle": row.isSingle
	        };
	        $.ajax({
			  type: "POST",
			  url: url,
			  data: JSON.stringify(data),
			  contentType: 'application/json',
			  success: function(result) {
			      if(result.success==true) {
			      	alert("执行成功");
			      } else {
			        alert(result.success + ", errorMsg:" + result.errorMsg);
			      }
			  }
			});
	    } );

        DataTable.enableColumnSearch(table);
    } );



</script>


</@dt.init>