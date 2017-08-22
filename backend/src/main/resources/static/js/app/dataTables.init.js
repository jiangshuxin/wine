var DataTable = {
    CONTEXT_PATH : '/wine',
    /**
     * 编辑器配置
     * @param moduleId 模块编号
     * @param fields 字段定义
     * @returns {{idSrc: string, ajax: {create: {type: string, contentType: string, url: string, data: ajax.create.data}, edit: {type: string, contentType: string, url: string, data: ajax.edit.data}, remove: {type: string, contentType: string, url: string, data: ajax.remove.data}}, table: string, fields: *, i18n: {create: {button: string, title: string, submit: string}, edit: {button: string, title: string, submit: string}, remove: {button: string, title: string, submit: string, confirm: {_: string, 1: string}}, error: {system: string}, multi: {title: string, info: string, restore: string}, datetime: {previous: string, next: string, months: string[], weekdays: string[]}}}}
     */
    editorConfig : function(moduleId,fields,otherConfig){

        return $.extend({
            idSrc: "id",
            ajax: {
                create: {
                    type: 'POST',
                    contentType: "application/json",
                    url:  DataTable.CONTEXT_PATH+'/api/'+moduleId+'/create',
                    data: function ( d ) {
                        return JSON.stringify( d );
                    }
                },
                edit: {
                    type: 'PUT',
                    contentType: "application/json",
                    url:  DataTable.CONTEXT_PATH+'/api/'+moduleId+'/edit',
                    data: function ( d ) {
                        var id = editor.s.idSrc;
                        var data = d.data;
                        for(var k in data){
                            data[k][id] = k;
                        }
                        return JSON.stringify( d );
                    }
                },
                remove: {
                    type: 'POST',
                    contentType: "application/json",
                    url:  DataTable.CONTEXT_PATH+'/api/'+moduleId+'/remove',
                    data: function ( d ) {
                        var id = editor.s.idSrc;
                        var data = d.data;
                        for(var k in data){
                            data[k][id] = k;
                        }
                        return JSON.stringify( d );
                    }
                }
            },
            table: "#dataTable",
            fields: fields,
            i18n: {
                create: {
                    button: "新增",
                    title:  "新增条目",
                    submit: "保存"
                },
                edit: {
                    button: "编辑",
                    title:  "编辑条目",
                    submit: "更新"
                },
                remove: {
                    button: "删除",
                    title:  "删除条目",
                    submit: "删除",
                    confirm: {
                        _: "你确定删除这 %d 项吗?",
                        1: "你确定删除这1项吗?"
                    }
                },
                error: {
                    system: "系统出错,请联系管理员"
                },
                multi: {
                    title: "多个值",
                    info: "Les éléments sélectionnés contiennent des valeurs différentes pour cette entrée. Pour modifier et mettre tous les éléments pour cette entrée pour la même valeur, cliquez ou appuyez ici, sinon ils vont conserver leurs valeurs individuelles.",
                    restore: "Annuler les modifications"
                },
                datetime: {
                    previous: 'Précédent',
                    next:     'Premier',
                    months:   [ '一月', '二月', '三月', '四月', '五月', '六月', '七月', '八月', '九月', '十月', '十一月', '十二月' ],
                    weekdays: [ '周一', '周二', '周三', '周四', '周五', '周六', '周日' ]
                }
            }
        },otherConfig)
    },
    /**
     * 列表配置
     * @param moduleId 模块编号
     * @param columnArray 字段定义
     * @param buttonGroup 按钮定义
     * @param otherConfig 其他dataTable配置
     * @returns {*}
     */
    dataTableConfig : function(moduleId,columnArray,buttonGroup,otherConfig){
        //前端一般只配置data,默认情况下data=name,设置name可以让后续table.column('xx:name')方法生效
        for(var k in columnArray){
            var temp = columnArray[k];
            if(temp['data'] && !temp['name']){//data有值 name没值
                temp['name'] = temp['data'];
            }
        }

        return $.extend({
            module:moduleId,
            dom: "Brtlip",
            pageLength:100,
            lengthMenu: [ 10, 25, 50, 75, 100 ,2500],
            language: {
                "sProcessing":   "处理中...",
                "sLengthMenu":   "显示 _MENU_ 项结果",
                "sZeroRecords":  "没有匹配结果",
                "sInfo":         "显示第 _START_ 至 _END_ 项结果，共 _TOTAL_ 项",
                "sInfoEmpty":    "显示第 0 至 0 项结果，共 0 项",
                "sInfoFiltered": "(由 _MAX_ 项结果过滤)",
                "sInfoPostFix":  "",
                "sSearch":       "搜索:",
                "sUrl":          "",
                "sEmptyTable":     "表中数据为空",
                "sLoadingRecords": "载入中...",
                "sInfoThousands":  ",",
                "oPaginate": {
                    "sFirst":    "首页",
                    "sPrevious": "上页",
                    "sNext":     "下页",
                    "sLast":     "末页"
                },
                "oAria": {
                    "sSortAscending":  ": 以升序排列此列",
                    "sSortDescending": ": 以降序排列此列"
                }
            },
            ajax: {
                url: DataTable.CONTEXT_PATH+"/api/"+moduleId+"/list",
                contentType: "application/json",
                cache: false,
                type: "POST",
                data: function ( d ) {
                    if(typeof d == 'undefined')return '';
                    var orderArr = d.order;
                    for(var k in orderArr){
                        if(!isNaN(orderArr[k].column)){
                            orderArr[k].column = columnArray[orderArr[k].column].data;
                        }
                    }
                    //将searchValue初始化到请求参数中
                    for(var k in columnArray){
                        var searchValue = columnArray[k].searchValue;
                        var nullable = columnArray[k].nullable;
                        if(typeof searchValue != 'undefined' && typeof nullable != 'undefined'){
                            //不看searchValue的取值,只要定义即可
                            if(nullable){//后台:where column is null
                                d.columns[k].nullable = true;
                            }else{//后台:where column is not null
                                d.columns[k].nullable = false;
                            }
                            d.columns[k].search.value = searchValue;
                        }else if(typeof searchValue != 'undefined' && typeof nullable == 'undefined'){
                            d.columns[k].search.value = searchValue;
                        }
                    }
                    return JSON.stringify( d );
                }
            },
            processing: true,
            serverSide: true,
            columns: columnArray,
            select: true,
            //scrollX: true,
            buttons: buttonGroup
        },otherConfig)
    },
    reload : function(table){
        table.ajax.reload();
    },
    /**
     * 启用字段搜索功能
     * @param table
     */
    enableColumnSearch : function(table){
        $('#dataTable tfoot th').each( function (i) {
            var $that = $(this);
            var searchType = table.init().columns[i].searchType || 'text';
            var category = table.init().columns[i].ddic || '';

            if(searchType == 'select'){
                var data = Ddic.get(category);
                if(!data || data.length == 0)return true;
                var selectHtml = [];
                selectHtml.push('<select class="selectpicker">');
                selectHtml.push('<option value="">-----全部-----</option>');
                for(var k in data){
                    selectHtml.push('<option value="'+data[k]['itemKey']+'">'+data[k]['itemValue']+'</option>');
                }
                selectHtml.push('</select>');
                $that.html(selectHtml.join('\n'));

            }else{//text & date
                var title = $(this).text();
                $(this).html( '<input type="'+searchType+'" placeholder="搜索 '+title+'" />' );

            }

        } );
        DataTable._bindSearchEvent(table);

        $('tfoot').css('display', 'table-header-group');
    },
    _bindSearchEvent : function(table){
        table.columns().every( function (i) {
            var that = this;
            if(table.init().columns[i] && table.init().columns[i].ddicRef){
                that = table.column(table.init().columns[i].ddicRef+':name');
            }

            $( 'input', this.footer() ).on( 'blur keyup', function (e) {
                if(e.keyCode){
                    if(e.keyCode == 13) that.search( this.value ).draw();
                }else if (that.search() !== this.value ) {
                    that.search( this.value ).draw();
                }
            } );
            $( 'select.selectpicker', this.footer() ).on( 'change', function (e) {
                that.search( this.value ).draw();
            } );
        } );
    },
    search : function(table){
        table.columns().search().draw();
    },
    searchByColumn : function(table,columnName,value){
        var column = table.column(columnName+':name');
        if(column.length < 1){
            console.log('cannot find column named "'+columnName+'",please add column name in dataTableConfig');
            return ;
        }
        var index = column.index();
        $('#dataTable tfoot th').eq(index).find('input').val(value);
        column.search(value).draw();
    },
    Editor : {
        readonly : function(fieldId){
            $('#DTE_Field_'+fieldId).attr('readonly',true);
        },
        showMsgOnError : function(editor){
            editor.on( 'submitError', function ( event, response, type, message, data) {
                editor.error(response.responseJSON.message);
            } );
        },
        newInstance : function(moduleName,fields,otherConfig){
            var editor = new $.fn.dataTable.Editor( DataTable.editorConfig(moduleName,fields,otherConfig));
            DataTable.Editor.showMsgOnError(editor);
            return editor;
        }
    }
}

var Ddic = {
    get : function (name) {
        var allDdicStr = localStorage.getItem('ddic');
        var data = JSON.parse(allDdicStr);
        if(!name){
            return data;
        }
        return data[name];
    },
    /**
     * 生成dataTable识别的select组件数据源
     * @param name
     * @returns {Array}
     */
    show : function (name) {
        var objList = this.get(name);
        if(!objList || objList.length == 0)return;
        var result = new Array();
        result.push({label:'-----请选择-----',value:''});
        for(var k in objList){
            var temp = new Object();
            temp.label = objList[k].itemValue;
            temp.value = objList[k].itemKey;
            result.push(temp);
        }
        return result;
    },
    init : function () {
        $.post(DataTable.CONTEXT_PATH+"/api/all/ddic?r="+new Date().getTime(),
            function(result){
                if(!result.success){
                    return;
                }
                var data = result.data;
                localStorage.setItem('ddic',JSON.stringify(data));
            },'json');
    }
}

/**
 * 后台返回List<Select>，该方法用于产生dataTable识别的组件元素（select radio checkbox）
 * @type {{init: Select.init}}
 */
var Select = {
    init : function(path){
        var jsonStr = $.ajax({
            url: DataTable.CONTEXT_PATH + path,
            async: false,
            timeout:3000
        }).responseText;
        if(jsonStr){
            var jsonObj = $.parseJSON(jsonStr);
            if(jsonObj.success){
                return jsonObj.data;
            }
        }
        return false;
    }
};

var Invoker = {
    post : function (path,paramObj) {
        var jsonStr = $.ajax({
            url: DataTable.CONTEXT_PATH + path,
            async: false,
            contentType: 'application/json',
            type: 'POST',
            data: JSON.stringify(paramObj),
            timeout:3000
        }).responseText;
        if(jsonStr){
            return $.parseJSON(jsonStr);
        }
        return false;
    },
    get : function (path) {
        var jsonStr = $.ajax({
            url: DataTable.CONTEXT_PATH + path,
            async: false,
            type: 'GET',
            timeout:3000
        }).responseText;
        if(jsonStr){
            return $.parseJSON(jsonStr);
        }
        return false;
    }
};