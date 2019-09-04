$(document).ready(function () {
    // initMeasureDataResult();
    initMeasureDataOPTestRequire();
    getDateYear();

    function initMeasureDataOPTestResult(factory,link,partVerion,datetime,proName,testType) {
        if(testType=='KAPPA測試'){
           // alert("KAPPA測試");
            $.ajax({
                url: "../MeasureDataOPTest/ShowMeasureDataOPTestResult",
                type: 'post',
                async: false,
                data: {
                    Factory: factory,
                    LINE_NUMBER: link,
                    PART_NUMBER_V: partVerion,
                    DATETIME:datetime,
                    PROJECT_NAME:proName,
                    TestType:testType
                },
                success: function (result) {
                    var StatusCode = result.StatusCode;
                    var m = result.message;
                    if (StatusCode == "500") {
                        alert("查無數據！");
                        $('#linkManageTable tbody').html('');
                    } else if (StatusCode == "200") {
                        var obj = eval(m);
                        $('#linkManageTable tbody').html('');
                        $('#linkManageTable thead').html('');
                        var thead="\t\t<tr>\n" +
                            "\t\t\t<th>綫別</th>\n" +
                            "\t\t\t<th>測試階段</th>\n" +
                            "\t\t\t<th>OP人員</th>\n" +
                            "\t\t\t<th>品保人員</th>\n" +
                            "\t\t\t<th>測試時間</th>\n" +
                            "\t\t\t<th>測試結果</th>\n" +
                            "\t\t\t<th>良率</th>\n" +
                            "\t\t</tr>";
                        for (var i = 0; i < obj.length; i++) {
                            var testTypeResult="";
                            var tableContents = "";
                            if(parseInt(obj[i].YIELD_RATE.toString())>=90){
                                testTypeResult="Pass"
                            }else {
                                testTypeResult="Fail"
                            }
                            tableContents += '<tr>' +
                                '<td>' + obj[i].LINE_NUMBER + '</td>'
                                + '<td>' + obj[i].TEST_STATUS + '</td>'
                                + '<td>' + obj[i].OP_NAME + '</td>'
                                + '<td>' + obj[i].IPQC_NAME + '</td>'
                                + '<td>' + obj[i].DATETIME + '</td>'
                                + '<td>' + testTypeResult+ '</td>'
                                + '<td>' + obj[i].YIELD_RATE + '</td>';
                            $('#linkManageTable tbody').append(tableContents);
                        }
                        $('#linkManageTable thead').append(thead);
                    }
                },
                error: function (err) {
                    alert("日期下拉框廠區查詢異常(js)：" + err.status);
                }
            })
        }else{
           // alert("陷阱測試");
            $.ajax({
                url: "../MeasureDataOPTest/ShowMeasureDataOPTestResult1",
                type: 'post',
                async: false,
                data: {
                    Factory: factory,
                    LINE_NUMBER: link,
                    PART_NUMBER_V: partVerion,
                    DATETIME:datetime,
                    PROJECT_NAME:proName,
                    TestType:testType
                },
                success: function (result) {
                    var StatusCode = result.StatusCode;
                    var m = result.message;
                    if (StatusCode == "500") {
                        alert("查無數據！");
                        $('#linkManageTable tbody').html('');
                    } else if (StatusCode == "200") {
                        var obj = eval(m);
                        $('#linkManageTable tbody').html('');
                        $('#linkManageTable thead').html('');
                        var thead="\t\t<tr>\n" +
                            "\t\t\t<th>綫別</th>\n" +
                            "\t\t\t<th>測試階段</th>\n" +
                            "\t\t\t<th>測試工站</th>\n" +
                            "\t\t\t<th>測試項目</th>\n" +
                            "\t\t\t<th>OP人員</th>\n" +
                            "\t\t\t<th>測試結果</th>\n" +
                            "\t\t\t<th>綫長</th>\n" +
                            "\t\t\t<th>原因分析</th>\n" +
                            "\t\t\t<th>改善對策</th>\n" +
                            "\t\t</tr>";
                        for (var i = 0; i < obj.length; i++) {
                            var tableContents = "";
                            tableContents += '<tr>' +
                                '<td>' + obj[i].LINE_NUMBER + '</td>'
                                + '<td>' + obj[i].TEST_STATUS + '</td>'
                                + '<td>' + obj[i].Work_Station + '</td>'
                                + '<td>' + obj[i].Test_Content + '</td>'
                                + '<td>' + obj[i].OP_NAME + '</td>'
                                + '<td>' + obj[i].TEST_RESULT_1 + '</td>'
                                + '<td>' + obj[i].Line_Leader + '</td>'
                                + '<td>' + obj[i].Remark_1+ '</td>'
                                + '<td>' + obj[i].Remark_2 + '</td>';
                            $('#linkManageTable tbody').append(tableContents);
                        }
                        $('#linkManageTable thead').append(thead);
                    }
                },
                error: function (err) {
                    alert("日期下拉框廠區查詢異常(js)：" + err.status);
                }
            })
        }

    }

    function AllClear() {
        $("#fl-Factory").find("option:selected").text("");
        $("#fl-Factory").empty();
        $("#fl-Link").find("option:selected").text("");
        $("#fl-Link").empty();
        $("#pro-Name").find("option:selected").text("");
        $("#pro-Name").empty();
        $("#part-Verion").find("option:selected").text("");
        $("#part-Verion").empty();
        $("#measure-Status").find("option:selected").text("");
        $("#measure-Status").empty();
        $("#dpick1").find("option:selected").text("");
        $("#dpick1").empty();
        $("#testType").html('<option></option><option>KAPPA測試</option><option>陷阱測試</option>');
    }

    function initMeasureDataOPTestRequire() {
        AllClear();
        $.ajax({
            url: "../MeasureDataOPTest/ShowDropdownBoxFactory",
            type: 'post',
            success: function (result) {
                var StatusCode = result.StatusCode;
                var m = result.message;
                if (StatusCode == "500") {
                    alert(m);
                } else if (StatusCode == "200") {
                    var obj = eval(m);
                    $("#fl-Factory").append("<option></option>");
                    for (var i = 0; i < obj.length; i++) {
                        $("#fl-Factory").append("<option>" + obj[i].FACTORY + "</option>");
                    }
                }
            },
            error: function (err) {
                alert("加載下拉框廠區值失敗，失敗原因：" + err);
            }
        })//加載廠區下拉框值
        $.ajax({
            url: "../MeasureDataOPTest/ShowDropdownBoxLine",
            type: 'post',
            success: function (result) {
                var StatusCode = result.StatusCode;
                var m = result.message;
                if (StatusCode == "500") {
                    alert(m);
                } else if (StatusCode == "200") {
                    var obj = eval(m);
                    $("#fl-Link").append("<option></option>");
                    for (var i = 0; i < obj.length; i++) {
                        $("#fl-Link").append("<option>" + obj[i].LINE_NUMBER + "</option>");
                    }
                }
            },
            error: function (err) {
                alert("加載下拉框綫別值失敗，失敗原因：" + err);
            }
        })//加載綫別下拉框值
        $.ajax({
            url: "../MeasureDataOPTest/ShowDropdownBoxProjectName",
            type: 'post',
            success: function (result) {
                var StatusCode = result.StatusCode;
                var m = result.message;
                if (StatusCode == "500") {
                    alert(m);
                } else if (StatusCode == "200") {
                    var obj = eval(m);
                    $("#pro-Name").append("<option></option>");
                    for (var i = 0; i < obj.length; i++) {
                        $("#pro-Name").append("<option>" + obj[i].PROJECT_NAME + "</option>");
                    }
                }
            },
            error: function (err) {
                alert("加載下拉框專案名稱值失敗，失敗原因：" + err);
            }
        })//加載專案名稱下拉框值
        $.ajax({
            url: "../MeasureDataOPTest/ShowDropdownBoxPartNumber",
            type: 'post',
            success: function (result) {
                var StatusCode = result.StatusCode;
                var m = result.message;
                if (StatusCode == "500") {
                    alert(m);
                } else if (StatusCode == "200") {
                    var obj = eval(m);
                    $("#part-Verion").append("<option></option>");
                    for (var i = 0; i < obj.length; i++) {
                        $("#part-Verion").append("<option>" + obj[i].PART_NUMBER_V + "</option>");
                    }
                }
            },
            error: function (err) {
                alert("加載下拉框料號版本值失敗，失敗原因：" + err);
            }
        })//加載料號版本下拉框值
        var ButDate = false;//判斷日期按鈕是否點擊
        var year = "";
        var month = "";
        $("#fl-Factory").change(function () {
            var Factory = $("#fl-Factory").find("option:selected").text();
            $("#fl-Link").find("option:selected").text("");
            $("#fl-Link").empty();
            $("#pro-Name").find("option:selected").text("");
            $("#pro-Name").empty();
            $("#part-Verion").find("option:selected").text("");
            $("#part-Verion").empty();
            $("#dpick1").find("option:selected").text("");
            $("#dpick1").empty();
            $("#testType").html('<option></option><option>KAPPA測試</option><option>陷阱測試</option>');
            if (Factory == '') {
                alert("請選擇廠區！");
                return;
            }
            $.ajax({
                url: "../MeasureDataOPTest/FactoryDropdownBoxIf",
                type: 'post',
                async: false,
                data: {Factory: Factory},
                success: function (result) {
                    var StatusCode = result.StatusCode;
                    var m = result.message;
                    if (StatusCode == "500") {
                        alert(m);
                    } else if (StatusCode == "200") {
                        var obj = eval(m);
                        $("#fl-Link").append("<option></option>");
                        for (var i = 0; i < obj.length; i++) {
                            $("#fl-Link").append("<option>" + obj[i].LINE_NUMBER + "</option>");
                        }
                    }
                },
                error: function (err) {
                    alert("下拉框廠區查詢異常：" + err.status);
                }
            })
        })//廠區下拉框點擊事件
        $("#fl-Link").change(function () {
            var Link = $("#fl-Link").find("option:selected").text();
            var Factory = $("#fl-Factory").find("option:selected").text();
            if (Factory == '') {
                alert("請先選擇廠區！");
                return;
            }
            $("#pro-Name").find("option:selected").text("");
            $("#pro-Name").empty();
            $("#part-Verion").find("option:selected").text("");
            $("#part-Verion").empty();
            $("#measure-Status").find("option:selected").text("");
            $("#measure-Status").empty();
            $("#dpick1").find("option:selected").text("");
            $("#dpick1").empty();
            $("#testType").html('<option></option><option>KAPPA測試</option><option>陷阱測試</option>');
            if (Link == '') {
                alert("請選擇綫別！");
                return;
            }
            $.ajax({
                url: "../MeasureDataOPTest/ShowDropdownBoxProjectNames",
                type: 'post',
                async: false,
                data: {Factory: Factory, LINE_NUMBER: Link},
                success: function (result) {
                    var StatusCode = result.StatusCode;
                    var m = result.message;
                    if (StatusCode == "500") {
                        alert(m);
                    } else if (StatusCode == "200") {
                        var obj = eval(m);
                        $("#pro-Name").append("<option></option>");
                        for (var i = 0; i < obj.length; i++) {
                            $("#pro-Name").append("<option>" + obj[i].PROJECT_NAME + "</option>");
                        }
                    }
                },
                error: function (err) {
                    alert("獲取專案名稱下拉框值查詢異常：" + err.status);
                }
            })//獲取專案名稱下拉框值
            $.ajax({
                url: "../MeasureDataOPTest/ShowDropdownBoxPartNumbers",
                type: 'post',
                async: false,
                data: {Factory: Factory, LINE_NUMBER: Link},
                success: function (result) {
                    var StatusCode = result.StatusCode;
                    var m = result.message;
                    if (StatusCode == "500") {
                        alert(m);
                    } else if (StatusCode == "200") {
                        var obj = eval(m);
                        $("#part-Verion").append("<option></option>");
                        for (var i = 0; i < obj.length; i++) {
                            $("#part-Verion").append("<option>" + obj[i].PART_NUMBER_V + "</option>");
                        }
                    }
                },
                error: function (err) {
                    alert("獲取料號版本下拉框值查詢異常：" + err.status);
                }
            })//獲取料號版本下拉框值
        })//綫別點擊事件
        $("#DateOK").click(function () {
            year = $("#year").find("option:selected").text();
            month = $("#month").find("option:selected").text();
            var factory = $("#fl-Factory").find("option:selected").text();
            var link = $("#fl-Link").find("option:selected").text();
            var partVerion = $("#part-Verion").find("option:selected").text();
            var proName = $("#pro-Name").find("option:selected").text();
            $("#dpick1").find("option:selected").text("");
            $("#dpick1").empty();//清除日期下拉框裏面的值
            if (factory != '' && link != '' && partVerion != '' && proName!='') {
                ButDate = true;
                $.ajax({
                    url: "../MeasureDataOPTest/ShowDropdownBoxDay",
                    type: 'post',
                    async: false,
                    data: {
                        Factory: factory,
                        LINE_NUMBER: link,
                        PART_NUMBER_V: partVerion,
                        DATETIME: year + "-" + month,
                        PROJECT_NAME:proName
                    },
                    success: function (result) {
                        var StatusCode = result.StatusCode;
                        var m = result.message;
                        if (StatusCode == "500") {

                        } else if (StatusCode == "200") {
                            var obj = eval(m);
                            // $("#dpick1").append("<option></option>");
                            for (var i = 0; i < obj.length; i++) {
                                $("#dpick1").append("<option>" + obj[i].DATETIME + "</option>");
                            }
                        }
                    },
                    error: function (err) {
                        alert("日期下拉框廠區查詢異常(js)：" + err.status);
                    }
                })//加載天數下拉框值

                return;
            }
            alert("(*)處為必填項！");
        })//年份月份按鈕點擊事件
        $("#dpick1").click(function () {
            if (ButDate == false) {
                alert("請選擇上方的日期！");
                return;
            }
        })//日期下拉框點擊事件
        $("#QueryOK").click(function () {
            var factory = $("#fl-Factory").find("option:selected").text();
            var link = $("#fl-Link").find("option:selected").text();
            var partVerion = $("#part-Verion").find("option:selected").text();
            var datetime = $("#dpick1").find("option:selected").text();
            var proName = $("#pro-Name").find("option:selected").text();
            var testType = $("#testType").find("option:selected").text();
            if (factory != '' && link != '' && partVerion != '' && datetime!='' && proName!='' && testType!='') {
                initMeasureDataOPTestResult(factory,link,partVerion,datetime,proName,testType);
                return;
            }

            alert("(*)處不能爲空！");

        })//查詢按鈕點擊事件

    }

    function getDateYear() {
        var data = new Date();
        var year = data.getFullYear();
        $("#year").get(0).options[0].text = year - 3;
        $("#year").get(0).options[1].text = year - 2;
        $("#year").get(0).options[2].text = year - 1;
        $("#year").get(0).options[3].text = year;
        $("#year").get(0).options[4].text = year + 1;
        $("#year").get(0).options[5].text = year + 2;
        $("#year").get(0).options[6].text = year + 3;
        var month = data.getMonth() + 1;
        var option = $("#month").children();
        for (var i = 0; i < option.length; i++) {
            var time = "0" + month;
            var timeC = option.eq(i).text();
            if (time == timeC) {
                option[i].selected = true;
            }
        }
    }// 獲取下拉框日期年份，當前時間前3年和后3年

})
