(function (e) {
    $.get('src/china.json', function (chinaJson) {
        e.registerMap('china', chinaJson); // 注册地图
        var nationData = e.init(document.getElementById('nation-data'));

        // 注册范围选择器事件
        nationData.on('brushselected', function (params) {
            geo.renderBrushed.apply(this, [params, geo.data, geo.convertData]);
        });

        // 获取配置
        var option = geo.getOption(geo.data, geo.convertData);

        // 手动触发地图选择范围
        setTimeout(function () {
            geo.brushDispatch(nationData);
        }, 1000);

        // set配置信息
        nationData.setOption(option);
    });
})(echarts);
