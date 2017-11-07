(function (e) {
    var addUser = e.init(document.getElementById('add-user'));
    var options = {
        backgroundColor: '#2d323b',
        tooltip : {
            trigger: 'axis'
        },
        calculable : true,
        xAxis : [
            {
                type : 'category',
                data : ['1月','2月','3月','4月','5月','6月','7月','8月','9月','10月','11月','12月'],
                axisLine: {
                    lineStyle: {
                        color: '#eee'
                    }
                }
            }
        ],
        yAxis : [
            {
                type : 'value',
                axisLine: {
                    lineStyle: {
                        color: '#eee'
                    }
                }
            }
        ],
        grid: {
            top: '12%',
            left: '4%',
            right: '10%',
            bottom: '10%',
            containLabel: true
        },
        series : [
            {
                name:'新增用户数',
                type:'bar',
                barWidth: 15,
                data:[1030, 1823, 3281, 4023, 1237, 5831, 4212, 3221, 9201, 4821, 2381, 4281],
                markPoint : {
                    data : [
                        {name : '年最高', value : 9201, xAxis: 8, yAxis: 9201},
                        {name : '年最低', value : 1030, xAxis: 0, yAxis: 1030}
                    ]
                },
                markLine : {
                    data : [
                        {type : 'average', name : '平均值'}
                    ]
                }
            }
        ],
        itemStyle: {
            normal: {
                color: '#df5a5a',
                // 阴影的大小
                shadowBlur: 10,
                // 阴影水平方向上的偏移
                shadowOffsetX: 0,
                // 阴影垂直方向上的偏移
                shadowOffsetY: 0,
                // 阴影颜色
                shadowColor: 'rgba(0, 0, 0, 0.4)'
            }
        }
    };
    addUser.setOption(options);
})(echarts);
