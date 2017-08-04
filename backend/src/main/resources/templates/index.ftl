<@dt.init>


<div class="row">
    <div class="col-lg-6 col-md-6">
        <div class="panel-group" id="accordion1" role="tablist" aria-multiselectable="true">
            <div class="panel panel-default">
                <div class="panel-heading" role="tab" id="headingOne">
                    <h4 class="panel-title">
                        <a role="button" data-toggle="collapse" data-parent="#accordion1" href="#collapseOne" aria-expanded="true" aria-controls="collapseOne">
                            今日待处理
                        </a>
                    </h4>
                </div>
                <div id="collapseOne" class="panel-collapse collapse in" role="tabpanel" aria-labelledby="headingOne">
                    <div class="panel-body">
                        <div class="list-group">
                            <a id="waitUpload" href="#" class="list-group-item">
                                <span class="badge">0</span>
                                待上传
                            </a>
                            <a id="waitMatch" href="#" class="list-group-item">
                                <span class="badge">0</span>
                                待匹配
                            </a>
                            <a id="failUpload" href="${rc.contextPath}/uploadRecord/list?status=0" class="list-group-item">
                                <span class="badge">0</span>
                                上传失败
                            </a>
                            <a href="#" class="list-group-item">
                                <span class="badge">0</span>
                                未校验通过
                            </a>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <div class="col-lg-6 col-md-6">
        <div class="panel-group" id="accordion2" role="tablist" aria-multiselectable="true">
            <div class="panel panel-default">
                <div class="panel-heading" role="tab" id="headingThree">
                    <h4 class="panel-title">
                        <a role="button" data-toggle="collapse" data-parent="#accordion2" href="#collapseThree" aria-expanded="true" aria-controls="collapseThree">
                            历史待处理
                        </a>
                    </h4>
                </div>
                <div id="collapseThree" class="panel-collapse collapse in" role="tabpanel" aria-labelledby="headingThree">
                    <div class="panel-body">
                        <div class="list-group">
                            <a href="#" class="list-group-item" onclick="$('#unuploadModal').modal()">
                                <span class="badge">0</span>
                                待上传
                            </a>
                            <a href="#" class="list-group-item" onclick="$('#unuploadModal').modal()">
                                <span class="badge">0</span>
                                未校验通过
                            </a>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>


<div class="modal fade" id="waitUploadModal" tabindex="-1" role="dialog" aria-labelledby="waitUploadModalLabel">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                <h4 class="modal-title" id="waitUploadModalLabel">待上传</h4>
            </div>
            <div class="modal-body">
                <form id="upload-file-form">
                    <div class="row">
                        <div class="col-lg-4">
                            <select id="waitUploadSel" class="form-control">
                            </select>
                        </div>
                        <div class="col-lg-4">
                            <input id="upload-file-input" class="form-control" type="file" name="uploadFile" accept="*"/>
                            <span id="upload-file-message"></span>
                        </div>
                        <div class="col-lg-4">
                            <div class="input-group">
                              <span class="input-group-addon">
                                <input type="checkbox" checked aria-label="...">
                              </span>
                                <input type="text" class="form-control" aria-label="..." value="是否同步处理" readonly>
                            </div>
                        </div>
                    </div>
                </form>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
                <button type="button" class="btn btn-primary" onclick="uploadFile()">提交</button>
            </div>
        </div>
    </div>
</div>

<div class="modal fade" id="waitMatchModal" tabindex="-1" role="dialog" aria-labelledby="waitMatchModalLabel">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                <h4 class="modal-title" id="waitMatchModalLabel">待匹配</h4>
            </div>
            <div class="modal-body">
                <div class="panel-body">
                    <div class="list-group" id="waitMatchGroup">

                    </div>
                </div>
            </div>
        </div>
    </div>
</div>

<script>
    $(document).ready(function(){
        Ddic.init();

        $('#waitUpload').click(function(){
            $('#waitUploadModal').modal();
        });
        $.ajax({
            url: "${rc.contextPath}/workbench/waitUpload",
            type: "GET",
            contentType: 'application/json',
            processData: false,
            cache: false,
            success: function (result) {
                if(result.success){
                    $('#waitUpload span').text(result.data.count);
                    var modules = result.data.modules;
                    for(var k in modules){
                        $('#waitUploadSel').append(['<option value="',k,'">',modules[k],'</option>'].join(''));
                    }
                }
            },
            error: function () {
                console.log('error')
            }
        });

    });

    function uploadFile() {
        var moduleName = $('#waitUploadSel').val();
        $.ajax({
            url: "${rc.contextPath}/upload/"+moduleName,
            type: "POST",
            data: new FormData($("#upload-file-form")[0]),
            enctype: 'multipart/form-data',
            processData: false,
            contentType: false,
            cache: false,
            success: function (result) {
                // Handle upload success
                if(result.success){
                    $("#upload-file-message").text("文件上传成功!");
                    $('#waitUploadModal').modal('hide');
                }else{
                    $("#upload-file-message").text(result.errorMsg);
                }
            },
            error: function () {
                // Handle upload error
                $("#upload-file-message").text(
                        "File not uploaded (perhaps it's too much big)");
            }
        });
    }
</script>
</@dt.init>