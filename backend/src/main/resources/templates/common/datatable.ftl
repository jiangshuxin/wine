<#macro init titles=[] mode='normal'>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <link rel="shortcut icon" type="image/ico" href="${rc.contextPath}/wine.ico">
    <meta name="viewport" content="initial-scale=1.0, maximum-scale=2.0">
    <title>
        <#if signinUser??>
            <#if signinUser.department??>
                ${signinUser.department} -
            </#if>
        </#if>

        <#if targetMenu??>
            <#if targetMenu.parent??>
                ${targetMenu.parent.name} -
            </#if>
            ${targetMenu.name}
        </#if>

        <#if titles??>
            <#list titles as title>
                - ${title}
            </#list>
        </#if>
    </title>
    <link rel="stylesheet" type="text/css" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
    <link rel="stylesheet" type="text/css" href="https://cdn.datatables.net/1.10.13/css/jquery.dataTables.min.css">
    <link rel="stylesheet" type="text/css" href="https://cdn.datatables.net/buttons/1.2.4/css/buttons.dataTables.min.css">
    <link rel="stylesheet" type="text/css" href="https://cdn.datatables.net/select/1.2.1/css/select.dataTables.min.css">
    <link rel="stylesheet" type="text/css" href="${rc.contextPath}/editor/css/editor.dataTables.css">
    <link rel="stylesheet" type="text/css" href="${rc.contextPath}/bootstrap-select-1.12.4/dist/css/bootstrap-select.min.css">
    <!-- IE10 viewport hack for Surface/desktop Windows 8 bug -->
    <link href="${rc.contextPath}/bootstrap/assets/css/ie10-viewport-bug-workaround.css" rel="stylesheet">

    <!-- Custom styles for this template -->
    <#if mode=='normal'>
        <link href="${rc.contextPath}/bootstrap/examples/dashboard/dashboard.css" rel="stylesheet">
    </#if>
    <style type="text/css" class="init">

    </style>
    <script type="text/javascript" language="javascript" src="//code.jquery.com/jquery-1.12.4.js">
    </script>
    <script type="text/javascript" language="javascript" src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js">
    </script>
    <script type="text/javascript" language="javascript" src="https://cdn.datatables.net/1.10.15/js/jquery.dataTables.min.js">
    </script>
    <script type="text/javascript" language="javascript" src="https://cdn.datatables.net/buttons/1.3.1/js/dataTables.buttons.min.js">
    </script>
    <script type="text/javascript" language="javascript" src="http://cdnjs.cloudflare.com/ajax/libs/jszip/2.5.0/jszip.min.js">
    </script>
    <script type="text/javascript" language="javascript" src="${rc.contextPath}/js/3rd/pdfmake.min.js">
    </script>
    <script type="text/javascript" language="javascript" src="${rc.contextPath}/js/3rd/vfs_fonts.js">
    </script>
    <script type="text/javascript" language="javascript" src="https://cdn.datatables.net/buttons/1.2.4/js/buttons.html5.min.js">
    </script>
    <script type="text/javascript" language="javascript" src="http://cdn.datatables.net/buttons/1.2.4/js/buttons.print.min.js">
    </script>
    <script type="text/javascript" language="javascript" src="https://cdn.datatables.net/select/1.2.2/js/dataTables.select.min.js">
    </script>
    <script type="text/javascript" language="javascript" src="${rc.contextPath}/editor/js/dataTables.editor.min.js">
    </script>
    <script type="text/javascript" language="javascript" src="${rc.contextPath}/js/app/dataTables.init.js">
    </script>
    <script type="text/javascript" language="javascript" src="${rc.contextPath}/bootstrap-select-1.12.4/dist/js/bootstrap-select.min.js">
    </script>
    <script type="text/javascript" language="javascript" src="${rc.contextPath}/bootstrap-select-1.12.4/dist/js/i18n/defaults-zh_CN.min.js">
    </script>
    <script type="text/javascript" language="javascript">
        DataTable.CONTEXT_PATH = '${rc.contextPath}';
    </script>

</head>
<body class="dt-example net">
<!-- Fixed navbar -->
<#if mode=='normal'>
<nav class="navbar navbar-inverse navbar-fixed-top">
    <div class="container">
        <div class="navbar-header">
            <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#navbar" aria-expanded="false" aria-controls="navbar">
                <span class="sr-only">Toggle navigation</span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
            </button>
            <a class="navbar-brand" target="_blank" href="${rc.contextPath}/menuRedirect/workbenchIndex?redirect=/charts/index.html">财富酒庄后台管理系统</a>
        </div>
        <div id="navbar" class="navbar-collapse collapse">
            <#list menuList as menu>
                <#if !menu.hidden>
                    <ul class="nav navbar-nav">
                        <li class="dropdown">
                            <a href="${menu.uri!'#'}" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false">${menu.name} <span class="caret"></span></a>
                            <ul class="dropdown-menu">
                                <#if menu.children??>
                                    <#list menu.children as child>
                                        <#if !child.hidden>
                                            <li><a href="${child.uri}">${child.name}</a></li>
                                        </#if>
                                    </#list>
                                </#if>
                            </ul>
                        </li>
                    </ul>
                </#if>
            </#list>

            <ul class="nav navbar-nav navbar-right">
                <li class="dropdown">
                    <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false">${signinUser.name!"设置"} <span class="caret"></span></a>
                    <ul class="dropdown-menu">
                        <li><a href="#">个人资料</a></li>
                        <li><a href="${rc.contextPath}/signout">退出系统</a></li>
                    </ul>
                </li>
            </ul>


        </div><!--/.nav-collapse -->
    </div>
</nav>
</#if>

<div class="container-fluid">

        <div class="row">
            <#if moduleName?? && moduleName=='index'>
                <div class="col-sm-12 col-md-12 main" style="padding-right:0px;padding-left:0px;">
            <#else>
                <div class="col-sm-12 col-md-12 main">
            </#if>
                <#if mode=='normal'>
                <ol class="breadcrumb">
                    <#if signinUser??>
                        <#if signinUser.department??>
                            <li>${signinUser.department}</li>
                        </#if>
                    </#if>

                    <#if targetMenu??>
                        <li>${targetMenu.parent.name}</li>
                        <li>${targetMenu.name}</li>
                    </#if>

                    <#if titles??>
                        <#list titles as title>
                            <li>${title}</li>
                        </#list>
                    </#if>
                </ol>
                </#if>

                <#if moduleName?? && moduleName=='index'>
                    <div class="col-lg-12" style="padding-right:0px;padding-left:0px;">
                <#else>
                    <div class="col-lg-12">
                </#if>
                    <#nested>
                </div>
            </div>
        </div>


</div>
</body>
</html>
</#macro>


<#macro uploadFile moduleName>
<!-- Modal -->
<div class="modal fade" id="uploadModal" tabindex="-1" role="dialog" aria-labelledby="uploadModalLabel">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                <h4 class="modal-title" id="uploadModalLabel">数据文件上传</h4>
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
                                <input type="text" name="override" class="form-control" aria-label="..." value="是否覆盖">
                            </div><!-- /input-group -->
                        </div><!-- /.col-lg-6 -->
                    </div><!-- /.row -->
                </form>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
                <button type="button" class="btn btn-primary" onclick="uploadFile()">提交</button>
            </div>
        </div>
    </div>
</div>
<script>
    function uploadFile() {
        $.ajax({
            url: "${rc.contextPath}/upload/${moduleName}",
            type: "POST",
            data: new FormData($("#upload-file-form")[0]),
            enctype: 'multipart/form-data',
            processData: false,
            contentType: false,
            cache: false,
            success: function (result) {
                //result = $.parseJSON(result);
                if(result.success){
                    $("#upload-file-message").text("File succesfully uploaded");
                }else{
                    $("#upload-file-message").text(result.errorMsg);
                }
            },
            error: function () {
                $("#upload-file-message").text(
                        "后台系统发生未处理异常,请查看日志");
            }
        });
    }
</script>

</#macro>

<#macro buildRelationship moduleName>
    <div class="modal fade" id="financeModal" tabindex="-1" role="dialog" aria-labelledby="financeModalLabel">
        <div class="modal-dialog modal-lg" role="document">
            <div class="modal-content">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                    <h4 class="modal-title" id="financeModalLabel">匹配财务数据</h4>
                </div>
                <div class="modal-body">
                    <iframe name="targetWindow" width="100%" height="450px" frameborder="no"></iframe>
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
                    <button type="button" class="btn btn-primary" onclick="buildRelationship()">提交</button>
                </div>
            </div>
        </div>
    </div>

    <script>
        function buildRelationship(){
            var selArray = table.rows( {selected: true} ).data();
            var baseData = new Array();
            for(var k=0;k<selArray.length;k++){
                baseData.push(selArray[k]);
            }
            if(self.frames['targetWindow']){
                var financeObj = self.frames['targetWindow'].table.row({selected: true}).data();
                if(financeObj == 'undefined'){
                    alert('请选择一条财务数据');
                    return;
                }
                $.ajax({
                    url: "${rc.contextPath}/api/${moduleName}/buildRelationship",
                    type: "POST",
                    data: JSON.stringify({baseData:baseData,financeData:financeObj}),
                    contentType: 'application/json',
                    processData: false,
                    cache: false,
                    success: function (result) {
                        if(result.success){
                            alert('操作成功');
                            //window.location.reload(true);
                            if(table && table.ajax){
                                $('#financeModal').modal('hide');
                                table.ajax.reload( null, false );
                            }
                        }
                    },
                    error: function () {
                        console.log('error')
                    }
                });
            }

        }
    </script>
</#macro>