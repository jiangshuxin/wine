
<@dt.init>

<table id="dataTable" class="display" cellspacing="0" width="100%">
    <thead>
    <tr>
        <th>编码</th>
        <th>部门</th>
        <th>分类</th>
        <th>财务编码</th>
        <th>财务名称</th>
        <th>期末时间点</th>
        <th>期末值</th>
    </tr>
    </thead>

    <tfoot>
    <tr>
        <th>编码</th>
        <th>部门</th>
        <th>分类</th>
        <th>财务编码</th>
        <th>财务名称</th>
        <th>期末时间点</th>
        <th>期末值</th>
    </tr>
    </tfoot>
</table>

<div class="modal fade" id="uploadModal" tabindex="-1" role="dialog" aria-labelledby="uploadModalLabel">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                <h4 class="modal-title" id="uploadModalLabel">初始值上传</h4>
            </div>
            <div class="modal-body">
                <form id="upload-file-form">
                    <div class="row">
                        <div class="col-lg-6">
                            <input id="upload-file-input" class="form-control" type="file" name="uploadFile" accept="*"/>
                            <span id="upload-file-message"></span>
                        </div><!-- /.col-lg-6 -->
                        <div class="col-lg-6">
                            <div class="input-group">
                              <span class="input-group-addon">
                                <input type="checkbox" aria-label="...">
                              </span>
                              <input type="text" name="override" class="form-control" aria-label="..." value="是否覆盖" readonly>
                            </div><!-- /input-group -->
                        </div><!-- /.col-lg-6 -->
                    </div><!-- /.row -->
                </form>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
                <button type="button" class="btn btn-primary" id="uploadBtn">提交</button>
            </div>
        </div>
    </div>
</div>
<script type="text/javascript" language="javascript" class="init">


    $(document).ready(function() {
    	
    	$('#uploadBtn').click(function(){
    	    $.ajax({
	            url: "${rc.contextPath}/excel/upload/period",
	            type: "POST",
	            data: new FormData($("#upload-file-form")[0]),
	            enctype: 'multipart/form-data',
	            processData: false,
	            contentType: false,
	            cache: false,
	            success: function (result) {
	            	$("#upload-file-message").text("File succesfully uploaded");
	            	setTimeout(function (){
					  $('#uploadModal').modal('hide');
					}, 2000);
	            },
	            error: function () {
	                $("#upload-file-message").text("后台系统发生未处理异常,请查看日志");
	            }
            });
    	});
    	
        editor = new $.fn.dataTable.Editor( DataTable.editorConfig('${moduleName}',[ {
                    label: "编码:",
                    name: "id"
                }, {
                    label: "部门:",
                    name: "department"
                }, {
                    label: "分类:",
                    name: "endTypeName"
                }, {
                    label: "财务编码:",
                    name: "unifiedId"
                }, {
                    label: "财务名称:",
                    name: "unifiedName"
                }, {
                    label: "期末时间点:",
                    name: "period"
                }, {
                    label: "期末值:",
                    name: "endValue"
                }
                ]
                ) );

        table = $('#dataTable').DataTable( DataTable.dataTableConfig('${moduleName}',[
            { data: "id"},
            { data: "department" },
            { data: "endTypeName" },
            { data: "unifiedId"},
            { data: "unifiedName" },
            { data: "period"},
            { data: "endValue"}
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
                text: "初始值上传",
                action: function ( e, dt, node, config ) {
                    $('#uploadModal').modal();
                }
            }
        ],{initComplete: function () {

        }
        }) );
        
        DataTable.enableColumnSearch(table);
    } );



</script>


</@dt.init>