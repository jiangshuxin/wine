geo.getOption = function (data, convertData) {
    return {
        backgroundColor: '#272c34',
        animation: true,
        animationDuration: 1000,
        animationEasing: 'cubicInOut',
        animationDurationUpdate: 1000,
        animationEasingUpdate: 'cubicInOut',
        title: [{
            text: '全国主要城市销售数据',
            subtext: '销售额-单位（万）',
            sublink: 'http://www.pm25.in',
            left: 'center',
            textStyle: {
                color: '#fff'
            }
        }, {
            id: 'statistic',
            right: 120,
            top: 40,
            width: 100,
            textStyle: {
                color: '#fff',
                fontSize: 16
            }
        }],
        toolbox: {
            show : true,
            feature : {
                myTool1: {
                    show: true,
                    title: '自定义扩展方法1',
                    icon: '123',
                    onclick: function (){
                        alert('myToolHandler1')
                    }
                },
                saveAsImage : {show: true}
                
            },
            iconStyle: {
                normal: {
                    borderColor: '#fff'
                },
                emphasis: {
                    borderColor: '#b1e4ff'
                }
            }
        },
        brush: {
            outOfBrush: {
                color: '#abc'
            },
            brushStyle: {
                borderWidth: 2,
                color: 'rgba(0,0,0,0.2)',
                borderColor: 'rgba(0,0,0,0.5)',
            },
            seriesIndex: [0, 1],
            throttleType: 'debounce',
            throttleDelay: 300,
            geoIndex: 0
        },
        geo: {
            map: 'china',
            left: '10',
            right: '35%',
            center: [117.98561551896913, 31.205000490896193],
            zoom: 2.5,
            label: {
                emphasis: {
                    show: false
                }
            },
            roam: true,
            itemStyle: {
                normal: {
                    areaColor: '#323c48',
                    borderColor: '#111'
                },
                emphasis: {
                    areaColor: '#2a333d'
                }
            }
        },
        tooltip: {
            trigger: 'item'
        },
        grid: {
            right: 40,
            top: 100,
            bottom: 40,
            width: '30%'
        },
        // legend: {
        //     type: 'scroll',
        //     orient: 'vertical',
        
        //     right: 1000,
        //     top: 20,
        //     bottom: 20,
        //     data: ['销售额', 'Top 5']
        // },
        xAxis: {
            type: 'value',
            scale: true,
            position: 'top',
            boundaryGap: false,
            splitLine: {
                show: false
            },
            axisLine: {
                show: false
            },
            axisTick: {
                show: false
            },
            axisLabel: {
                margin: 2,
                textStyle: {
                    color: '#aaa'
                }
            },
        },
        yAxis: {
            type: 'category',
            name: 'TOP 20',
            nameGap: 16,
            axisLine: {
                show: false,
                lineStyle: {
                    color: '#ddd'
                }
            },
            axisTick: {
                show: false,
                lineStyle: {
                    color: '#ddd'
                }
            },
            axisLabel: {
                interval: 0,
                textStyle: {
                    color: '#ddd'
                }
            },
            data: []
        },
        series: [{
            name: '销售额',
            type: 'scatter',
            coordinateSystem: 'geo',
            data: convertData(data),
            symbolSize: function(val) {
                return val[2] / 50;
            },
            label: {
                normal: {
                    formatter: '{b}',
                    position: 'right',
                    show: true
                },
                emphasis: {
                    show: true
                }
            },
            itemStyle: {
                normal: {
                    color: '#ddb926'
                }
            }
        }, {
            name: 'Top 5',
            type: 'effectScatter',
            coordinateSystem: 'geo',
            data: convertData(data.sort(function (a, b) {
                return b.value - a.value;
            })).slice(0, 6),
            symbolSize: function(val) {
                return val[2] / 40;
            },
            showEffectOn: 'render',
            rippleEffect: {
                brushType: 'stroke'
            },
            hoverAnimation: true,
            label: {
                normal: {
                    formatter: '{b}',
                    position: 'right',
                    show: true
                }
            },
            itemStyle: {
                normal: {
                    color: '#f4e925',
                    shadowBlur: 10,
                    shadowColor: '#333'
                }
            },
            zlevel: 1
        }, {
            id: 'bar',
            zlevel: 2,
            type: 'bar',
            symbol: 'none',
            itemStyle: {
                normal: {
                    color: '#ddb926'
                }
            },
            data: []
        }]
    };
}
