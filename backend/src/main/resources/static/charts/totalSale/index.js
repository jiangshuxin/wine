(function (e) {
    var totalSale = e.init(document.getElementById('total-sale'), {
        // width: '100%',
        // height: '50%',
        top: 'middle',
        left: 'center'
    });
    var options = {
        backgroundColor: '#2d323b',
        grid: {
            top: '12%',
            left: '4%',
            right: '10%',
            bottom: '10%',
            containLabel: true
        },
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
                },
                nextTextStyle: {
                    align: 'center'
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
        series : [
            {
                name:'新增用户数',
                type:'bar',
                barGap: '50%',
                barWidth: 15,
                data:[13230, 18323, 33281, 24023, 11237, 15831, 14212, 21221, 29201, 14821, 22381, 44281],
                markPoint : {
                    data : [
                        {name : '年最高', value : 9201, xAxis: 11, yAxis: 44281},
                        {name : '年最低', value : 1030, xAxis: 4, yAxis: 11237}
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
                color: '#50acfe',
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
    totalSale.setOption(options);
})(echarts);
