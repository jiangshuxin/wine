
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
            var genderDdic = Ddic.show('gender');

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
            }, {
                label: "密码:",
                name: "password"
            },{
                label: "性别:",
                name: "gender",
                type: 'radio',
                options:genderDdic
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
                { data: "balance",render: function ( data, type, row ) {
                    if(!isNaN(row.balance)){
                        return new Number(row.balance)/100;
                    }
                }},
                { data: "realName"},
                { data: "type"},
                { data: "status"}//不展示的枚举值，放列表最后，便于隐藏
            ],[
                { extend: "create", editor: editor },
                { extend: "edit",   editor: editor },
                { extend: "remove", editor: editor },
                {
                    extend: "selectedSingle",
                    text: "分销佣金打款",
                    action: function ( e, dt, node, config ) {
                        var selData = table.row('.selected').data();
                        var amount = window.prompt('请输入打款金额',0);

                        if(!amount){
                            return;
                        }
                        var paramObj = {
                            userId: selData.id,
                            amount: amount*100
                        };
                        var jsonObj = Invoker.post('/backend/user/updateBalance',paramObj);
                        if(jsonObj.success && jsonObj.data > 0){
                            alert('余额更新成功！');
                            DataTable.reload(table);
                        }else{
                            alert(jsonObj.errorMsg);
                        }
                    }
                },
                {
                    extend: "selectedSingle",
                    text: "查看用户地址",
                    action: function ( e, dt, node, config ) {
                        var selData = table.row('.selected').data();
                        location.href = "${rc.contextPath}/userAddress/list?userId="+selData.id;
                    }
                },
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
                { "visible": false, "targets": [7,8] }
            ]
                , order: [[ 0, 'desc' ]]
            }) );

            DataTable.enableColumnSearch(table);

            editor.on( 'open', function ( e, json, data ) {
                DataTable.Editor.readonly('balance');
                DataTable.Editor.readonly('parentId');

                var balance = new Number(editor.get('balance'));
                editor.set('balance',balance/100);
            } );
            editor.on( 'preSubmit', function ( e, json, action ) {
                var data = json.data;
                for(var k in data){
                    data[k].balance = data[k].balance*100;
                }
            } );
        } );



    </script>


</@dt.init>