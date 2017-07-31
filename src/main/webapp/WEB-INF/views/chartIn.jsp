<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
    <title>Графики</title>
    <script src="https://code.highcharts.com/stock/highstock.js"></script>
    <script src="https://code.highcharts.com/stock/modules/exporting.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/highcharts/5.0.9/highcharts.js"></script>
    <script
            src="https://code.jquery.com/jquery-3.1.1.min.js"
            integrity="sha256-hVVnYaiADRTO2PzUGmuLJr8BLUSjGIZsDYGmIJLv2b8="
            crossorigin="anonymous"></script>

    <script src="https://cdnjs.cloudflare.com/ajax/libs/highcharts/5.0.9/highcharts.js"></script>
    <div id="container" style="height: 400px; min-width: 310px"></div>
    <script>
        Highcharts.setOptions({
            global: {
                useUTC: false
            }
        });

        // Create the chart
        Highcharts.stockChart('container', {
            chart: {
                events: {
                    load: function () {

                        // set up the updating of the chart each second
                        var series = this.series[0];
                        setInterval(function () {
                            $.ajax({
                                url: "/data.json",
                            })
                                .done(function (data) {
                                    var x = data.d2;
                                    series.addPoint([x, data.d3], true, true);
                                });
                        }, 1000);
                    }
                }
            },

            rangeSelector: {
                buttons: [{
                    count: 10,
                    type: 'ten',
                    text: '10'
                }, {
                    count: 100,
                    type: 'hundred',
                    text: '100'
                }, {
                    type: 'all',
                    text: 'All'
                }],
                inputEnabled: false,
                selected: 0
            },

            title: {
                text: 'Центральность по правому собственному вектору'
            },

            exporting: {
                enabled: false
            },

            series: [{
                name: 'Порядковый номер подразделения и значение',
                data: (function () {

                    var data = [],

                        i;

                    for (i = -19; i <= 0; i += 1) {
                        data.push({
                            x: i,
                            y: Math.random()
                        });
                    }
                    return data;
                }())
            }]
        });

    </script>


</head>
<body>
<style>
    table {
        width: 300px; /* Ширина таблицы */
        border: 5px solid #5c4980; /* Рамка вокруг таблицы */
        margin: auto; /* Выравниваем таблицу по центру окна  */
    }
    td {
        text-align: center;
    }
</style>
<table class="table">
    <tr>

        <div>
            <td><a href="/Out">Следующий график</a></td>
        </div>

</table>
<table class="table">
    <tr>

        <div>
            <td><a href="/chartF">Предыдущий график</a></td>
        </div>

</table>
<table class="table">
    <tr>

        <div>
            <td><a href="/chrOut">На главную</a></td>
        </div>

</table>

</body>
</html>