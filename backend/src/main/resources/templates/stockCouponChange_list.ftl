
<@dt.init>

    <table id="dataTable" class="display" cellspacing="0" width="100%">
        <thead>
        <tr>
            <th>编号</th>
            <th>部门</th>
            <th>变动时间</th>
            <th>优惠券编码</th>
            <th>优惠券名称</th>
            <th>项目编号</th>
            <th>变动类型</th>
            <th>变动数量</th>
            <th>99成本</th>
            <th>客户成本</th>
            <th>客户编码</th>
            <th>备注</th>
            <th>上传记录编号</th>
        </tr>
        </thead>

        <tfoot>
        <tr>
            <th>编号</th>
            <th>部门</th>
            <th>变动时间</th>
            <th>优惠券编码</th>
            <th>优惠券名称</th>
            <th>项目编号</th>
            <th>变动类型</th>
            <th>变动数量</th>
            <th>99成本</th>
            <th>客户成本</th>
            <th>客户编码</th>
            <th>备注</th>
            <th>上传记录编号</th>
        </tr>
        </tfoot>
    </table>
    <script type="text/javascript" language="javascript" class="init">


        $(document).ready(function() {
            editor = new $.fn.dataTable.Editor( DataTable.editorConfig('${moduleName}',[ {
			    label: "部门:",
			    name: "department"
			}, {
			    label: "时间:",
			    name: "changeTime"
			}, {
			    label: "优惠券编码:",
			    name: "couponId"
			}, {
			    label: "优惠券名称:",
			    name: "couponName"
			}, {
			    label: "项目编号:",
			    name: "projectId"
			}, {
			    label: "变动类型:",
			    name: "changeType"
			}, {
			    label: "变动数量:",
			    name: "changeNumber"
			}, {
			    label: "99成本:",
			    name: "cost99"
			}, {
			    label: "客户成本:",
			    name: "costCustomer"
			}, {
			    label: "客户编码:",
			    name: "customerId"
			}, {
			    label: "备注:",
			    name: "memo"
			}
            ]) );


            table = $('#dataTable').DataTable( DataTable.dataTableConfig('${moduleName}',[
                { data: "id"},
                { data: "department" },
				{ data: "changeTime" },
				{ data: "couponId" },
				{ data: "couponName" },
				{ data: "projectId" },
				{ data: "changeType" },
				{ data: "changeNumber" },
				{ data: "cost99" },
				{ data: "costCustomer" },
				{ data: "customerId" },
				{ data: "memo" },
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
                }
            ],{initComplete: function ()
            {
            }}) );

            DataTable.enableColumnSearch(table);

            <#if uploadId??>
                DataTable.searchByColumn(table,'uploadId','${uploadId}');
            </#if>
        } );



    </script>


</@dt.init>