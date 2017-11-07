<@dt.init>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>Document</title>
    <link rel="stylesheet" href="/wine/charts/index.css">
    <link rel="stylesheet" href="/wine/charts/addUser/index.css">
    <link rel="stylesheet" href="/wine/charts/totalSale/index.css">
    <link rel="stylesheet" href="/wine/charts/buyingPower/index.css">
    <link rel="stylesheet" href="/wine/charts/singleGoods/index.css">
    <link rel="stylesheet" href="/wine/charts/todayOrder/index.css">
    <script src="/wine/charts/src/echarts.min.js"></script>
    <script src="/wine/charts/index.js"></script>
</head>
<body>
    <div class="wrapper">
        <div class="header">鹏胜酒庄</div>
        <div class="top-charts">
            <div class="top-charts-item">
                <div class="top-charts-item-top today-order-top">
                    <h4>今日订单数</h4>
                    <p>928,128<span>/日</span></p>
                </div>
                <div class="today-order"></div>
            </div>
            <div class="top-charts-item">
                <div class="top-charts-item-top add-user-top">
                    <h4>今日订单数</h4>
                    <p>928,128<span>/日</span></p>
                </div>
                <div id="add-user" class="add-user"></div>
            </div>
            <div class="top-charts-item">
                <div class="top-charts-item-top total-sale-top">
                    <h4>今日订单数</h4>
                    <p>928,128<span>/日</span></p>
                </div>
                <div id="total-sale" class="total-sale"></div>
            </div>
            <div class="top-charts-item">
                <div class="top-charts-item-top buying-power-top">
                    <h4>今日订单数</h4>
                    <p>928,128<span>/日</span></p>
                </div>
                <div id="buying-power" class="buying-power"></div>
            </div>
            <div class="top-charts-item">
                <div class="top-charts-item-top single-goods-top">
                    <h4>今日订单数</h4>
                    <p>928,128<span>/日</span></p>
                </div>
                <div id="single-goods" class="single-goods" style="height: 145px;"></div>
            </div>
        </div>
    </div>
    <script src="/wine/charts/todayOrder/index.js"></script>
    <script src="/wine/charts/addUser/index.js"></script>
    <script src="/wine/charts/totalSale/index.js"></script>
    <script src="/wine/charts/buyingPower/index.js"></script>
    <script src="/wine/charts/singleGoods/index.js"></script>
</body>
</html>
</@dt.init>