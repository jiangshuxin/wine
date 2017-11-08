geo.renderBrushed = function (params, data, convertData) {
    var mainSeries = params.batch[0].selected[0];

    var selectedItems = [];
    var categoryData = [];
    var barData = [];
    var maxBar = 30;
    var sum = 0;
    var count = 0;

    for (var i = 0; i < mainSeries.dataIndex.length; i++) {
        var rawIndex = mainSeries.dataIndex[i];
        var dataItem = convertData(data)[rawIndex];
        var pmValue = dataItem.value[2];
        if (i < 10) {
            sum += +pmValue;
            count++;
        }


        selectedItems.push(dataItem);
    }

    selectedItems.sort(function(a, b) {
        return a.value[2] - b.value[2];
    });

    for (var i = 0; i < Math.min(selectedItems.length, maxBar); i++) {
        categoryData.push(selectedItems[i].name);
        barData.push(selectedItems[i].value[2]);
    }
    console.log(this);
    this.setOption({
        yAxis: {
            data: categoryData
        },
        xAxis: {
            axisLabel: {
                show: !!count
            }
        },
        title: {
            id: 'statistic',
            text: count ? '平均: ' + (sum / count).toFixed(0) + '万' : '',
            right: 320
        },
        series: {
            id: 'bar',
            data: barData
        }
    });
}
