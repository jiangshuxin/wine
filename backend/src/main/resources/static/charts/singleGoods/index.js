(function (e) {
    var buyingPower = e.init(document.getElementById('single-goods'));
    var buyingPowerData = new Array(10).fill(1).map(function () {return (Math.random() * 100000 + 10).toFixed()});
    var buyingPowerDatacopy = [...buyingPowerData];
    var buyingPowerSortData = buyingPowerDatacopy.sort();
    var options = {
        backgroundColor: '#2d323b',
        tooltip : {
            trigger: 'axis'
        },
        grid: {
            top: '12%',
            left: '4%',
            right: '10%',
            bottom: '10%',
            containLabel: true
        },
        calculable : true,
        xAxis : [
            {
                type : 'category',
                data : ['千红裕·钻石级','贝娜尼','千红裕·东一区','陇尚红','贝乐丝','千红裕·东二区','千红裕·东二区','泽风','千红裕·黄金级','千红裕·铂金级'],
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
                data: buyingPowerData,
                markPoint : {
                    data : [
                        {name : '年最高', value : 9201, xAxis: buyingPowerData.findIndex(function (item) {return item === buyingPowerSortData[buyingPowerData.length - 1]}), yAxis: buyingPowerSortData[buyingPowerData.length - 1] },
                        {name : '年最低', value : 1030, xAxis: buyingPowerData.findIndex(function (item) {return item === buyingPowerSortData[0]}), yAxis: buyingPowerSortData[0]}
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
                color: '#7dd899',
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
    buyingPower.setOption(options);
})(echarts);
