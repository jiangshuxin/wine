
<@dt.init>

    <table id="dataTable" class="display" cellspacing="0" width="100%">
        <thead>
        <tr>
            <th>编号</th>
            <th>上传时间</th>
            <th>接口名称</th>
            <th>状态</th>
            <th>成功总数</th>
            <th>失败总数</th>
            <th>错误信息</th>
            <th>错误行号(参考值)</th>
            <th>路径</th>
            <th>文件名称</th>
        </tr>
        </thead>

        <tfoot>
        <tr>
            <th>编号</th>
            <th>上传时间</th>
            <th>接口名称</th>
            <th>状态</th>
            <th>成功总数</th>
            <th>失败总数</th>
            <th>错误信息</th>
            <th>错误行号</th>
            <th>路径</th>
            <th>文件名称</th>
        </tr>
        </tfoot>
    </table>
    <script type="text/javascript" language="javascript" class="init">


        $(document).ready(function() {


            table = $('#dataTable').DataTable( DataTable.dataTableConfig('${moduleName}',[
                { data: "id",width: "2%"},
                { data: "uploadTime"},
                { data: "moduleName" },
                { data: "status"<#if status??>,searchValue:"${status?trim}"</#if> },
                { data: "okSum",render: function ( data, type, row ) {
                    if(row.okSum == 0){
                        return row.okSum;
                    }
                    var url = '${rc.contextPath}/'+row.moduleName+'/list?uploadId='+row.id;
                    return '<a href="'+url+'" title="查看该批次数据">'+row.okSum+'</a>';
                } },
                { data: "failSum"},
                { data: "errMsg"},
                { data: "errLine"},
                { data: "path"},
                { data: "fileName" }
            ],[
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
                    text: "查询校验结果",
                    action: function ( e, dt, node, config ) {
                        var selData = table.row('.selected').data();
                        location.href = "${rc.contextPath}/uploadResult/list?refModuleName="+selData.moduleName+"&uploadId="+selData.id;
                    }
                },
                {
                    extend: "selectedSingle",
                    text: "重新上传",
                    action: function ( e, dt, node, config ) {
                        alert('暂未实现');
                        //var selData = table.row('.selected').data();
                        //location.href = "/uploadResult/list?refModuleName="+selData.moduleName+"&uploadId="+selData.id;
                    }
                }
            ],{initComplete: function ()
            {

            },columnDefs: [
                { "width": "2%", "targets": 0 },
                { "width": "5%", "targets": 3 },
                { "width": "2%", "targets": 4 },
                { "width": "2%", "targets": 5 }
                //{ "visible": false, "targets": 8 },
            ],order: [[ 1, 'desc' ]],autoWidth: false
            }) );

            DataTable.enableColumnSearch(table);
        } );



    </script>


</@dt.init>