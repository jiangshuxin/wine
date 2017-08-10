<@dt.init>

<div class="row">
    <div class="col-lg-6 col-md-6">
        <div id="main1" style="height:700px"></div>
    </div>
    <div class="col-lg-6 col-md-6">
        <div id="main2" style="height:700px"></div>
    </div>
</div>

<script type="text/javascript" language="javascript" src="${rc.contextPath}/js/echarts.min.js">
</script>

<script>
    $(document).ready(function(){
        chartInit();
    });

    function chartInit(){
        // 基于准备好的dom，初始化echarts图表
        myChart1 = echarts.init(document.getElementById('main1'));
        myChart2 = echarts.init(document.getElementById('main2'));

        option1 = {
            title: {
                text: '近7日用户增长量'
            },
            tooltip : {
                trigger: 'axis'
            },
            legend: {
                data:['普通用户','销售']
            },
            legend: {
                data:[]
            },
            toolbox: {
                feature: {
                    saveAsImage: {}
                }
            },
            grid: {
                left: '3%',
                right: '4%',
                bottom: '3%',
                containLabel: true
            },
            xAxis : [
                {
                    type : 'category',
                    boundaryGap : false,
                    data : ['周一','周二','周三','周四','周五','周六','周日']
                }
            ],
            yAxis : [
                {
                    type : 'value'
                }
            ],
            series : [
                {
                    name:'普通用户',
                    type:'line',
                    stack: '总量',
                    areaStyle: {normal: {}},
                    data:[120, 132, 101, 134, 90, 230, 210]
                },
                {
                    name:'销售',
                    type:'line',
                    stack: '总量',
                    areaStyle: {normal: {}},
                    data:[220, 182, 191, 234, 290, 330, 310]
                }
            ]
        };
        option2 = {
            title: {
                text: '近7日订单增长量'
            },
            tooltip : {
                trigger: 'axis'
            },
            legend: {
                data:['未支付订单','已支付订单','已完成订单']
            },
            legend: {
                data:[]
            },
            toolbox: {
                feature: {
                    saveAsImage: {}
                }
            },
            grid: {
                left: '3%',
                right: '4%',
                bottom: '3%',
                containLabel: true
            },
            xAxis : [
                {
                    type : 'category',
                    boundaryGap : false,
                    data : ['周一','周二','周三','周四','周五','周六','周日']
                }
            ],
            yAxis : [
                {
                    type : 'value'
                }
            ],
            series : [
                {
                    name:'未支付订单',
                    type:'line',
                    stack: '总量',
                    areaStyle: {normal: {}},
                    data:[120, 132, 101, 134, 90, 230, 210]
                },
                {
                    name:'已支付订单',
                    type:'line',
                    stack: '总量',
                    areaStyle: {normal: {}},
                    data:[220, 182, 191, 234, 290, 330, 310]
                },
                {
                    name:'已完成订单',
                    type:'line',
                    stack: '总量',
                    areaStyle: {normal: {}},
                    data:[150, 232, 201, 154, 190, 330, 410]
                }
            ]
        };
        myChart1.setOption(option1);
        myChart2.setOption(option2);
    }

</script>
</@dt.init>