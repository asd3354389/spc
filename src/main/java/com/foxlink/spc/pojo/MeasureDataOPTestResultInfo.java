package com.foxlink.spc.pojo;

public class MeasureDataOPTestResultInfo {
    private String LINE_NUMBER;//綫別
    private String TEST_STATUS;//測試階段
    private String OP_NAME;//OP人員
    private String IPQC_NAME;//品保人員
    private String DATETIME;//測試時間
    private String TEST_RESULT_1;//測試結果
    private String YIELD_RATE;//良率
    private String Work_Station;//測試工站
    private String Test_Content;//測試項目
    private String Line_Leader;//綫長
    private String Remark_1;//原因分析
    private String Remark_2;//改善措施


    public String getLINE_NUMBER() {
        return LINE_NUMBER;
    }

    public void setLINE_NUMBER(String LINE_NUMBER) {
        this.LINE_NUMBER = LINE_NUMBER;
    }

    public String getTEST_STATUS() {
        return TEST_STATUS;
    }

    public void setTEST_STATUS(String TEST_STATUS) {
        this.TEST_STATUS = TEST_STATUS;
    }

    public String getOP_NAME() {
        return OP_NAME;
    }

    public void setOP_NAME(String OP_NAME) {
        this.OP_NAME = OP_NAME;
    }

    public String getIPQC_NAME() {
        return IPQC_NAME;
    }

    public void setIPQC_NAME(String IPQC_NAME) {
        this.IPQC_NAME = IPQC_NAME;
    }

    public String getDATETIME() {
        return DATETIME;
    }

    public void setDATETIME(String DATETIME) {
        this.DATETIME = DATETIME;
    }

    public String getTEST_RESULT_1() {
        return TEST_RESULT_1;
    }

    public void setTEST_RESULT_1(String TEST_RESULT_1) {
        this.TEST_RESULT_1 = TEST_RESULT_1;
    }

    public String getYIELD_RATE() {
        return YIELD_RATE;
    }

    public void setYIELD_RATE(String YIELD_RATE) {
        this.YIELD_RATE = YIELD_RATE;
    }

    public String getWork_Station() {
        return Work_Station;
    }

    public void setWork_Station(String work_Station) {
        Work_Station = work_Station;
    }

    public String getTest_Content() {
        return Test_Content;
    }

    public void setTest_Content(String test_Content) {
        Test_Content = test_Content;
    }

    public String getLine_Leader() {
        return Line_Leader;
    }

    public void setLine_Leader(String line_Leader) {
        Line_Leader = line_Leader;
    }

    public String getRemark_1() {
        return Remark_1;
    }

    public void setRemark_1(String remark_1) {
        Remark_1 = remark_1;
    }

    public String getRemark_2() {
        return Remark_2;
    }

    public void setRemark_2(String remark_2) {
        Remark_2 = remark_2;
    }
}
