<%@ page isErrorPage="true" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="spform"
           uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<html>
<head>
    <link rel="stylesheet"
          href="https://unpkg.com/purecss@2.0.6/build/pure-min.css">
    <meta charset="UTF-8">
    <title>Fundstock Form</title>
    <style type="text/css">
        .error {
            color: #FF0000
        }
    </style>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/2.1.1/jquery.min.js"></script>
    <script src="${pageContext.request.contextPath}/js/util.js"></script>
    <script type="text/javascript" src="https://www.gstatic.com/charts/loader.js"></script>
    <script type="text/javascript">
        google.charts.load('current', {'packages': ['corechart']});
        google.charts.setOnLoadCallback(drawChart);

        function drawHistQuotesChart(symbol) {
            // Create data table
            let data = new google.visualization.DataTable();
            // Define columns
            data.addColumn('string', 'Date');
            data.addColumn('number', 'High');
            data.addColumn('number', 'Open');
            data.addColumn('number', 'Close');
            data.addColumn('number', 'Low');
            data.addColumn('number', 'AdjClose');
            data.addColumn('number', 'Volumn');
            // Fetch data
            $.get("${pageContext.request.contextPath}/mvc/coursework7/price/histquotes/" + symbol, (quotes) => {
                // Add data
                $.each(quotes, (i, item) => {
                    var array = [getMD(quotes[i].date), quotes[i].high, quotes[i].open, quotes[i].close, quotes[i].low, quotes[i].adjClose, quotes[i].volume];
                    data.addRow(array);
                })
                // 設定 chart 參數
                var options = {
                    title: symbol + ' 日K線圖',
                    legend: 'none',
                    vAxes: [
                        {},
                        {minValue: 1, maxValue: 6000000}
                    ],
                    series: {
                        1: {targetAxisIndex: 0, type: 'line', color: '#e7711b'},
                        2: {targetAxisIndex: 1, type: 'bars', color: '#cccccc'}
                    },
                    candlestick: {
                        fallingColor: {strokeWidth: 0, fill: '#0f9d58'}, // green
                        risingColor: {strokeWidth: 0, fill: '#a52714'}   // red
                    },
                    chartArea: {left: 50}
                };
                // 產生 chart 物件
                var chart = new google.visualization.CandlestickChart(document.getElementById('stockchart'));
                // 繪圖
                chart.draw(data, options);
            });
        }

        function drawChart() {
            drawChart(0)
        }

        function drawChart(chartType) {

            var data = google.visualization.arrayToDataTable([
                ['symbol', 'share'],
                <c:forEach var="map" items="${ groupMap }">
                ['${ map.key }', ${ map.value }],
                </c:forEach>
            ]);

            var options = {
                title: '各股分佈圖'
            };

            var chart = new google.visualization.PieChart(document.getElementById('piechart'));
            switch (chartType) {
                case 1:
                    chart = new google.visualization.ColumnChart(document.getElementById('piechart'));
                    break;
                case 2:
                    chart = new google.visualization.LineChart(document.getElementById('piechart'));
                    break;
                case 3:
                    chart = new google.visualization.BarChart(document.getElementById('piechart'));
                    break;
            }

            chart.draw(data, options);
        }
    </script>
</head>
<body style="padding: 15px">
<table>
    <tr>
        <!-- Fundstock Form -->
        <td valign="top">
            <spform:form class="pure-form" method="post"
                         modelAttribute="fundStock"
                         action="${ pageContext.request.contextPath }/mvc/coursework7/fundstock/${_method == 'PUT' ? fundstock.sid:''}">
                <fieldset>
                    <legend>
                        Fundstock Form&nbsp;|&nbsp;
                        <a href="${pageContext.request.contextPath}/html/coursework7/fund.html">Fund Form</a>
                    </legend>
                    <input type="hidden" id="_method" name="_method"
                           value="${ _method }">
                    序號：
                    <spform:input path="sid" disabled="true"/>
                    <spform:errors path="sid" cssClass="error"/>
                    <p/>
                    代號：
                    <spform:input path="symbol"/>
                    <spform:errors path="symbol" cssClass="error"/>
                    <p/>
                    數量：
                    <spform:input path="share"/>
                    <spform:errors path="share" cssClass="error"/>
                    <p/>
                    基金：
                    <spform:select path="fid">
                        <spform:option value="">請選擇</spform:option>
                        <spform:options items="${ funds }" itemValue="fid" itemLabel="fname"/>
                    </spform:select>
                    <spform:errors path="fid" cssClass="error"/>
                    <p/>
                    <button type="submit" class="pure-button pure-button-primary"
                        ${ _method=='POST'?'':'disabled' }>新增
                    </button>
                    <button type="submit" class="pure-button pure-button-primary"
                        ${ _method=='PUT'?'':'disabled' }>修改
                    </button>
                    <p/>
                    <spform:errors path="*" cssClass="error"/>
                </fieldset>
            </spform:form>
        </td>
        <!-- Fundstock List -->
        <td valign="top">
            <form class="pure-form">
                <fieldset>
                    <legend>
                        Fundstock List&nbsp;|&nbsp;
                        <a href="${ pageContext.request.contextPath }/mvc/coursework7/fundstock/page/0">全部</a>
                        &nbsp;|&nbsp;
                        <c:forEach var="num" begin="1" end="${ pageTotalCount + 1 }">
                            <a href="${ pageContext.request.contextPath }/mvc/coursework7/fundstock/page/${ num }">${ num }</a>
                        </c:forEach>
                    </legend>
                    <table class="pure-table pure-table-bordered">
                        <thead>
                        <tr>
                            <th>序號</th>
                            <th>代號</th>
                            <th>數量</th>
                            <th>基金</th>
                        </tr>
                        </thead>
                        <tbody>
                        <c:forEach var="fundstock" items="${ fundstocks }">
                            <tr>
                                <td><a
                                        href="${ pageContext.request.contextPath }/mvc/coursework7/fundstock/${ fundstock.sid }">${ fundstock.sid }</a>
                                </td>
                                <td>
                                    <a href="javascript:void(0)"
                                       onclick="drawHistQuotesChart('${ fundstock.symbol }')"> ${ fundstock.symbol }</a>
                                </td>
                                <td>${ fundstock.share }</td>
                                <td>${ fundstock.fund.fname }</td>
                            </tr>
                        </c:forEach>
                        </tbody>
                    </table>
                </fieldset>
            </form>
        </td>
        <!-- Fundstock chart -->
        <td valign="top">
            <form class="pure-form">
                <fieldset>
                    <legend>
                        Fundstock Chart
                        <a href="javascript:void(0)" onclick="drawChart(0)">Pie</a>
                        <a href="javascript:void(0)" onclick="drawChart(1)">Column</a>
                        <a href="javascript:void(0)" onclick="drawChart(2)">Line</a>
                        <a href="javascript:void(0)" onclick="drawChart(3)">Bar</a>
                    </legend>
                    <div id="piechart" style="width: 900px; height: 500px;"></div>
                </fieldset>
            </form>
        </td>
    </tr>
    <tr>
        <td colspan="3" valign="top">
            <form class="pure-form">
                <fieldset>
                    <legend>
                        Fundstock Chart | <a href="javascript:void(0)" onclick="drawHistQuotesChart('^TWII')">加權股價</a>
                    </legend>
                    <div id="stockchart" style="width: 1500px; height: 500px;"></div>
                </fieldset>
            </form>
        </td>
    </tr>
</table>


</body>
</html>