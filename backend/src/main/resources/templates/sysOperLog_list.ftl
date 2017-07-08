<@dt.init titles=["操作日志管理", "列表页", "列表页"]>

    <table id="dataTable" class="display" cellspacing="0" width="100%">
        <thead>
        <tr>
            <th>编号</th>
            <th>表名</th>
            <th>操作类型</th>
            <th>操作人</th>
            <th>操作时间</th>
            <th>操作前记录</th>
            <th>操作后记录</th>
            <th>备注</th>
        </tr>
        </thead>

        <tfoot>
        <tr>
            <th>编号</th>
            <th>表名</th>
            <th>操作类型</th>
            <th>操作人</th>
            <th>操作时间</th>
            <th>操作前记录</th>
            <th>操作后记录</th>
            <th>备注</th>
        </tr>
        </tfoot>
    </table>
    <script type="text/javascript" language="javascript" class="init">


        $(document).ready(function() {

            table = $('#dataTable').DataTable( 
            	DataTable.dataTableConfig('operationLog',
	            	[
		                { data: "id"},
		                { data: "operateTable" },
		                { data: "operateType" },
		                { data: "operator" },
		                { data: "operateTime" },
		                { data: "prePersist"},
		                { data: "postPersist" },
		                { data: "memo"}
	            	],
	            	[{
	                    extend: 'collection',
	                    text: '导出',
	                    buttons: [
	                        'copy',
	                        'excel',
	                        'csv',
	                        'pdf',
	                        'print'
	                    ]
	                }],
	                { 
	                	initComplete: function () {
			                $('tfoot').css('display', 'table-header-group');
			                //第二个字段下拉框查询
			                this.api().column(2).every( function () {
			                    var column = this;
			                    var select = $('<select><option value="">---请选择---</option></select>')
			                            .appendTo( $(column.footer()).empty() )
			                            .on( 'change', function () {
			                                /*var val = $.fn.dataTable.util.escapeRegex(
			                                 $(this).val()
			                                 );*/
			                                //column.search( val ? '^'+val+'$' : '', true, false ).draw();
			                                column.search( $(this).val() , false, true ).draw();
			                            } );
			
			                    column.data().unique().sort().each( function ( d, j ) {
			                        select.append( '<option value="'+d+'">'+d+'</option>' )
			                    } );
                			} );
                		},
                		columnDefs: [
                		    { "width": "3%", "targets": [ 0, 2 ] },
			                { "width": "5%", "targets": [ 1, 3, 7 ] },
			                { "width": "20%", "targets": [ 4, 7 ] },
			                { "width": "30%", "targets": [ 5, 6 ] }
			            ],
			            scrollY: false
                	}
            	) 
            );

            //DataTable.enableColumnSearch(table);
        } );

    </script>

</@dt.init>