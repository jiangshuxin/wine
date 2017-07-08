
<@dt.init>

    <table id="dataTable" class="display" cellspacing="0" width="100%">
        <thead>
        <tr>
            <th>编号</th>
            <th>部门</th>
            <th>供应商编码</th>
            <th>供应商名称</th>
            <th>账号编码</th>
            <th>账号名称</th>
            <th>交易流水号</th>
            <th>交易时间</th>
            <th>交易类型</th>
            <th>金额</th>
            <th>备注信息</th>
            <th>上传记录编号</th>
        </tr>
        </thead>

        <tfoot>
        <tr>
            <th>编号</th>
            <th>部门</th>
            <th>供应商编码</th>
            <th>供应商名称</th>
            <th>账号编码</th>
            <th>账号名称</th>
            <th>交易流水号</th>
            <th>交易时间</th>
            <th>交易类型</th>
            <th>金额</th>
            <th>备注信息</th>
            <th>上传记录编号</th>
        </tr>
        </tfoot>
    </table>
    <script type="text/javascript" language="javascript" class="init">


        $(document).ready(function() {
            editor = new $.fn.dataTable.Editor( DataTable.editorConfig('supplierAccountChange',[ {
			    label: "部门:",
			    name: "department"
			}, {
			    label: "供应商编码:",
			    name: "supplierId"
			}, {
			    label: "供应商名称:",
			    name: "supplierName"
			}, {
			    label: "供应商账号编码:",
			    name: "accountId"
			}, {
			    label: "供应商账号名称:",
			    name: "accountName"
			}, {
			    label: "交易流水号:",
			    name: "tradingSerialNo"
			}, {
			    label: "交易时间:",
			    name: "tradingTime"
			}, {
			    label: "交易类型:",
			    name: "tradingType"
			}, {
			    label: "交易金额:",
			    name: "changeAmount"
			}, {
			    label: "备注:",
			    name: "memo"
			} 
            ]) );


            table = $('#dataTable').DataTable( DataTable.dataTableConfig('supplierAccountChange',[
                { data: "id"},
                { data: "department" },
				{ data: "supplierId" },
				{ data: "supplierName" },
				{ data: "accountId" },
				{ data: "accountName" },
				{ data: "tradingSerialNo" },
				{ data: "tradingTime" },
				{ data: "tradingType" },
				{ data: "changeAmount" },
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