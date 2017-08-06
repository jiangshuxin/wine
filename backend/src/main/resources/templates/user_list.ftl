
<@dt.init>

    <table id="dataTable" class="display" cellspacing="0" width="100%">
        <thead>
        <tr>
            <th>编号</th>
            <th>推荐人编号</th>
            <th>用户名(手机号)</th>
            <th>状态名称</th>
            <th>类型</th>
            <th>余额</th>
            <th>姓名</th>
            <th>性别</th>
            <th>生日</th>
            <th>状态</th>
            <th>类型</th>
        </tr>
        </thead>

        <tfoot>
        <tr>
            <th>编号</th>
            <th>推荐人编号</th>
            <th>用户名(手机号)</th>
            <th>状态名称</th>
            <th>类型</th>
            <th>余额</th>
            <th>姓名</th>
            <th>性别</th>
            <th>生日</th>
            <th>状态</th>
            <th>类型</th>
        </tr>
        </tfoot>
    </table>
    <script type="text/javascript" language="javascript" class="init">
        var editor;

        $(document).ready(function() {
            var userStatusDdic = Ddic.show('userStatus');
            var userTypeDdic = Ddic.show('userType');

            editor = DataTable.Editor.newInstance('${moduleName}',[ {
                label: "编号:",
                name: "id",
            }, {
                label: "推荐人编号:",
                name: "parentId"
            }, {
                label: "用户名(手机号):",
                name: "userName"
            }, {
                label: "状态:",
                name: "status",
                type:  "select",
                options:userStatusDdic,
                def: 1
            }, {
                label: "类型:",
                name: "type",
                type:  "radio",
                options: userTypeDdic
                //def: 0
            }, {
                label: "余额:",
                name: "balance"
            }, {
                label: "姓名:",
                name: "realName"
            },{
                label: "性别:",
                name: "gender"
            }, {
                label: "生日:",
                name: "birthday"
            }
            ],{

            });

            table = $('#dataTable').DataTable( DataTable.dataTableConfig('${moduleName}',[
                { data: "id"},
                { data: "parentId" },
                { data: "userName" },
                { data: "statusName",searchType:"select",ddic:"userStatus",ddicRef:"status"},
                { data: "typeName",searchType:"select",ddic:"userType",ddicRef:"type"},
                { data: "balance"},
                { data: "realName"},
                { data: "gender"},
                { data: "birthday"},
                { data: "type"},
                { data: "status"}//不展示的枚举值，放列表最后，便于隐藏
            ],[
                { extend: "create", editor: editor },
                { extend: "edit",   editor: editor },
                { extend: "remove", editor: editor }
            ],{initComplete: function ()
            {

            }, "columnDefs": [
                { "visible": false, "targets": [9,10] }
            ]
                , order: [[ 0, 'desc' ]]
            }) );

            DataTable.enableColumnSearch(table);
        } );



    </script>


</@dt.init>