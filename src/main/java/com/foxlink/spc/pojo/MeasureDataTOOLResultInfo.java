package com.foxlink.spc.pojo;

public class MeasureDataTOOLResultInfo {
    private String DATETIME;//日期時間
    private String INSPECTION_ITEM;//檢查項目
    private String DEVICE_NUM;//設備編號
    private String INSPECTION_RESULT;//測量結果
    private String PERSONNEL_ID;//測量人員
    private String BATCH_REMARK;//檢驗備注

    public String getDATETIME() {
        return DATETIME;
    }

    public void setDATETIME(String DATETIME) {
        this.DATETIME = DATETIME;
    }

    public String getINSPECTION_ITEM() {
        return INSPECTION_ITEM;
    }

    public void setINSPECTION_ITEM(String INSPECTION_ITEM) {
        this.INSPECTION_ITEM = INSPECTION_ITEM;
    }

    public String getDEVICE_NUM() {
        return DEVICE_NUM;
    }

    public void setDEVICE_NUM(String DEVICE_NUM) {
        this.DEVICE_NUM = DEVICE_NUM;
    }

    public String getINSPECTION_RESULT() {
        return INSPECTION_RESULT;
    }

    public void setINSPECTION_RESULT(String INSPECTION_RESULT) {
        this.INSPECTION_RESULT = INSPECTION_RESULT;
    }

    public String getPERSONNEL_ID() {
        return PERSONNEL_ID;
    }

    public void setPERSONNEL_ID(String PERSONNEL_ID) {
        this.PERSONNEL_ID = PERSONNEL_ID;
    }

    public String getBATCH_REMARK() {
        return BATCH_REMARK;
    }

    public void setBATCH_REMARK(String BATCH_REMARK) {
        this.BATCH_REMARK = BATCH_REMARK;
    }
}
