(function (e) {
    var singleGoods = e.init(document.getElementById('buying-power'));
    var data = new Array(10).fill(1).map(function () {return (Math.random() * 10000 + 10).toFixed()});
    var data1 = new Array(10).fill(1).map(function () {return (Math.random() * 1000 + 10).toFixed()});
    var datacopy = [...data];
    var sortData = datacopy.sort();
    var options = {
        backgroundColor: '#2d323b',
        tooltip: {
            trigger: 'axis'
        },
        grid: {
            top: '12%',
            left: '2%',
            right: '3%',
            bottom: '3%',
            containLabel: true
        },
        xAxis: {
            type: 'category',
            boundaryGap: false,
            data: data.sort(),
            axisLabel: {
                formatter: '{value} 人',
            },
            axisLine: {
                lineStyle: {
                    color: '#eee'
                }
            }
        },
        yAxis: {
            type: 'value',
            axisLabel: {
                formatter: '{value} 元',
            },
            axisLine: {
                lineStyle: {
                    color: '#eee'
                }
            }
        },
        series: [
            {
                name:'价格',
                type:'line',
                stack: '总量',
                data: data1.sort(),
                markLine : {
                    symbol: 'arrow',
                    symbolSize: 5,
                    precision: 0,
                    label: {
                        normal: {position: 'middle'}
                    },
                    data : [
                        {type : 'average', name : '平均值'}
                    ]
                }
            },
        ],
        itemStyle: {
            normal: {
                color: '#f7d66c',
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
    singleGoods.setOption(options);
})(echarts);
