
<@dt.init>

    <table id="dataTable" class="display" cellspacing="0" width="100%">
        <thead>
        <tr>
            <th>编号</th>
            <th>酒庄编码</th>
            <th>酒庄名称</th>
            <th>状态</th>
            <th>庄主</th>
            <th>创建时间</th>
            <th>图片上传编号</th>
            <th>图片上传编号</th>
            <th>图片上传编号</th>
            <th>图片上传编号</th>
            <th>图片上传编号</th>
            <th>图片上传编号</th>
            <th>图片上传编号</th>
            <th>图片上传编号</th>
            <th>图片上传编号</th>
            <th>图片上传编号</th>
            <th>图片上传编号</th>
            <th>图片上传编号</th>
            <th>图片上传编号</th>
            <th>图片上传编号</th>
            <th>图片上传编号</th>
            <th>图片上传编号</th>
        </tr>
        </thead>

        <tfoot>
        <tr>
            <th>编号</th>
            <th>酒庄编码</th>
            <th>酒庄名称</th>
            <th>状态</th>
            <th>庄主</th>
            <th>创建时间</th>
            <th>图片上传编号</th>
            <th>图片上传编号</th>
            <th>图片上传编号</th>
            <th>图片上传编号</th>
            <th>图片上传编号</th>
            <th>图片上传编号</th>
            <th>图片上传编号</th>
            <th>图片上传编号</th>
            <th>图片上传编号</th>
            <th>图片上传编号</th>
            <th>图片上传编号</th>
            <th>图片上传编号</th>
            <th>图片上传编号</th>
            <th>图片上传编号</th>
            <th>图片上传编号</th>
            <th>图片上传编号</th>
        </tr>
        </tfoot>
    </table>
    <script type="text/javascript" language="javascript" class="init">
        var editor;
        var merchantStatusDdic = Ddic.show('merchantStatus');

        $(document).ready(function() {
            editor = DataTable.Editor.newInstance('${moduleName}',[ {
                label: "编号:",
                name: "id",
                type: "hidden"
            },{
                label: "酒庄编码(建议M+4位数字):",
                name: "merchantId",
            }, {
                label: "后台登录密码:",
                name: "password"
            }, {
                label: "酒庄名称:",
                name: "name"
            }, {
                label: "酒庄英文名称:",
                name: "nameEn"
            }, {
                label: "状态:",
                name: "status",
                type:'select',
                options:merchantStatusDdic
            }, {
                label: "庄主:",
                name: "master"
            }, {
                label: "创建时间:",
                name: "createYear",
                type: "datetime"
            }, {
                label: "种植面积:",
                name: "acreage"
            },{
                label: "葡萄种植种类，多个逗号隔开:",
                name: "grapeType"
            },{
                label: "平均树龄:",
                name: "treeAge"
            },{
                label: "酿酒师及介绍:",
                name: "wineMaker",
                type: "textarea"
            },{
                label: "年产量:",
                name: "output"
            },{
                label: "酒庄级别和级别介绍:",
                name: "level",
                type: "textarea"
            },{
                label: "酒庄文字简介:",
                name: "description",
                type: "textarea"
            },{
                label: "实时土壤和气候信息:",
                name: "soilAndClimate"
            },{
                label: "酒庄视频链接:",
                name: "videoLink"
            },{
                label: "参观预约链接:",
                name: "tourismLink"
            },{
                label: "酒庄照片1:",
                name: "chateauPic1Ref",
                type: "upload",
                display: function ( file_id ) {
                    return '<img src="'+editor.file( 'files', file_id ).web_path+'"/>';
                },
                clearText: "清除",
                noImageText: '请选择图片'
            },{
                label: "酒庄照片2:",
                name: "chateauPic2Ref",
                type: "upload",
                display: function ( file_id ) {
                    return '<img src="'+editor.file( 'files', file_id ).web_path+'"/>';
                },
                clearText: "清除",
                noImageText: '请选择图片'
            },{
                label: "酒庄照片3:",
                name: "chateauPic3Ref",
                type: "upload",
                display: function ( file_id ) {
                    return '<img src="'+editor.file( 'files', file_id ).web_path+'"/>';
                },
                clearText: "清除",
                noImageText: '请选择图片'
            },{
                label: "酒庄照片4:",
                name: "chateauPic4Ref",
                type: "upload",
                display: function ( file_id ) {
                    return '<img src="'+editor.file( 'files', file_id ).web_path+'"/>';
                },
                clearText: "清除",
                noImageText: '请选择图片'
            },{
                label: "种植地照片1:",
                name: "landPic1Ref",
                type: "upload",
                display: function ( file_id ) {
                    return '<img src="'+editor.file( 'files', file_id ).web_path+'"/>';
                },
                clearText: "清除",
                noImageText: '请选择图片'
            },{
                label: "种植地照片2:",
                name: "landPic2Ref",
                type: "upload",
                display: function ( file_id ) {
                    return '<img src="'+editor.file( 'files', file_id ).web_path+'"/>';
                },
                clearText: "清除",
                noImageText: '请选择图片'
            },{
                label: "种植地照片3:",
                name: "landPic3Ref",
                type: "upload",
                display: function ( file_id ) {
                    return '<img src="'+editor.file( 'files', file_id ).web_path+'"/>';
                },
                clearText: "清除",
                noImageText: '请选择图片'
            },{
                label: "种植地照片4:",
                name: "landPic4Ref",
                type: "upload",
                display: function ( file_id ) {
                    return '<img src="'+editor.file( 'files', file_id ).web_path+'"/>';
                },
                clearText: "清除",
                noImageText: '请选择图片'
            },{
                label: "部分证照1:",
                name: "certPic1Ref",
                type: "upload",
                display: function ( file_id ) {
                    return '<img src="'+editor.file( 'files', file_id ).web_path+'"/>';
                },
                clearText: "清除",
                noImageText: '请选择图片'
            },{
                label: "部分证照2:",
                name: "certPic2Ref",
                type: "upload",
                display: function ( file_id ) {
                    return '<img src="'+editor.file( 'files', file_id ).web_path+'"/>';
                },
                clearText: "清除",
                noImageText: '请选择图片'
            },{
                label: "部分证照3:",
                name: "certPic3Ref",
                type: "upload",
                display: function ( file_id ) {
                    return '<img src="'+editor.file( 'files', file_id ).web_path+'"/>';
                },
                clearText: "清除",
                noImageText: '请选择图片'
            },{
                label: "部分证照4:",
                name: "certPic4Ref",
                type: "upload",
                display: function ( file_id ) {
                    return '<img src="'+editor.file( 'files', file_id ).web_path+'"/>';
                },
                clearText: "清除",
                noImageText: '请选择图片'
            },{
                label: "得奖照片1:",
                name: "prizePic1Ref",
                type: "upload",
                display: function ( file_id ) {
                    return '<img src="'+editor.file( 'files', file_id ).web_path+'"/>';
                },
                clearText: "清除",
                noImageText: '请选择图片'
            },{
                label: "得奖照片2:",
                name: "prizePic2Ref",
                type: "upload",
                display: function ( file_id ) {
                    return '<img src="'+editor.file( 'files', file_id ).web_path+'"/>';
                },
                clearText: "清除",
                noImageText: '请选择图片'
            },{
                label: "得奖照片3:",
                name: "prizePic3Ref",
                type: "upload",
                display: function ( file_id ) {
                    return '<img src="'+editor.file( 'files', file_id ).web_path+'"/>';
                },
                clearText: "清除",
                noImageText: '请选择图片'
            },{
                label: "得奖照片4:",
                name: "prizePic4Ref",
                type: "upload",
                display: function ( file_id ) {
                    return '<img src="'+editor.file( 'files', file_id ).web_path+'"/>';
                },
                clearText: "清除",
                noImageText: '请选择图片'
            }
            ],{
                ajax:"${rc.contextPath}/api/upload/${moduleName}"
            });

            table = $('#dataTable').DataTable( DataTable.dataTableConfig('${moduleName}',[
                { data: "id"},
                { data: "merchantId"},
                { data: "name" },
                { data: "statusName" ,searchType:"select",ddic:"merchantStatus",ddicRef:"status"},
                { data: "master"},
                { data: "createYear"},
                { data: "chateauPic1"},
                { data: "chateauPic2"},
                { data: "chateauPic3"},
                { data: "chateauPic4"},
                { data: "landPic1"},
                { data: "landPic2"},
                { data: "landPic3"},
                { data: "landPic4"},
                { data: "certPic1"},
                { data: "certPic2"},
                { data: "certPic3"},
                { data: "certPic4"},
                { data: "prizePic1"},
                { data: "prizePic2"},
                { data: "prizePic3"},
                { data: "prizePic4"},
                { data: "status" }
            ],DataTable.buttonGroupDef(editor),{initComplete: function ()
            {

            },columnDefs: [
                { "visible": false, "targets": [6,7,8,9,10,11,12,13,14,15,16,17,18,19,20,21,22] }
            ]}) );

            DataTable.enableColumnSearch(table);

            editor.on( 'open', function ( e, json, eventType ) {
                if(eventType == 'edit'){
                    DataTable.Editor.readonly('merchantId');
                }
                if(eventType == 'create'){
                    DataTable.Editor.removeReadonly('merchantId');
                }
            } );

            editor.on( 'preSubmit', function ( e, json, eventType ) {
                if(!editor.val('merchantId')){
                    alert('编号不能为空');
                    return false;
                }
            } );

        } );



    </script>


</@dt.init>