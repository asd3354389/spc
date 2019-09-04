package com.foxlink.spc.dao;

import com.foxlink.spc.pojo.MeasureDataOPTestRequireInfo;
import com.foxlink.spc.pojo.MeasureDataOPTestResultInfo;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository("MeasureDataOPTestDao")
public class MeasureDataOPTestDao {
    private static Logger logger = Logger.getLogger(MeasureDataOPTestDao.class);
    private JdbcTemplate jdbcTemplate;

    @Autowired
    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }


    public List<MeasureDataOPTestResultInfo> ShowMeasureDataOPTestResult() {
        String sql="SELECT DATETIME,INSPECTION_ITEM,DEVICE_NUM,INSPECTION_RESULT,PERSONNEL_ID,BATCH_REMARK FROM SPC.MEASURE_DATA_TOOL";
        List<MeasureDataOPTestResultInfo> list_dcResult = new ArrayList<>();
        try {

            RowMapper<MeasureDataOPTestResultInfo> mapper = new BeanPropertyRowMapper<>(MeasureDataOPTestResultInfo.class);
            list_dcResult = jdbcTemplate.query(sql, mapper);

        } catch (Exception e) {
            e.printStackTrace();
            logger.error("MeasureDataOPTestDao-->ShhowMeasureDataOPTestResult方法異常：" + e);
        }
        return list_dcResult;
    }
    public List<MeasureDataOPTestResultInfo> ShowMeasureDataOPTestResult(String StrFactory,String StrLine,String StrPartNumberV,String date,String StrProjectName,String StrTestType) {
        String sql = "SELECT LINE_NUMBER,TEST_STATUS,OP_NAME,IPQC_NAME,DATETIME,TEST_RESULT_1,YIELD_RATE  FROM SPC.MEASURE_DATA_OPTEST\n" +
                "WHERE FACTORY='"+StrFactory+"' AND LINE_NUMBER='"+StrLine+"' AND PROJECT_NAME='"+StrProjectName+"' AND PART_NUMBER_V='"+StrPartNumberV+"' AND DATETIME='"+date+"' AND TEST_CLASS='"+StrTestType+"'";


        List<MeasureDataOPTestResultInfo> list_dcResult = new ArrayList<>();
        try {

            RowMapper<MeasureDataOPTestResultInfo> mapper = new BeanPropertyRowMapper<>(MeasureDataOPTestResultInfo.class);
            list_dcResult = jdbcTemplate.query(sql, mapper);

        } catch (Exception e) {
            e.printStackTrace();
            logger.error("MeasureDataOPTestDao-->ShhowMeasureDataOPTestResult方法異常：" + e);
        }
        return list_dcResult;
    } //KAPPA測試

    public List<MeasureDataOPTestResultInfo> ShowMeasureDataOPTestResult1(String StrFactory,String StrLine,String StrPartNumberV,String date,String StrProjectName,String StrTestType) {
        String sql ="SELECT\n" +
                "mdo.Line_Number Line_Number,\n" +
                "mdo.Test_Status Test_Status,\n" +
                "so.Work_Station Work_Station,\n" +
                "so.Test_Content Test_Content,\n" +
                "mdo.OP_Name OP_Name,\n" +
                "mdo.Test_Result_1 Test_Result_1,\n" +
                "mdo.Line_Leader Line_Leader,\n" +
                "mdo.Remark_1 Remark_1,\n" +
                "mdo.Remark_2 Remark_2\n" +
                "FROM SPC.MEASURE_DATA_OPTEST mdo\n" +
                "join SPC.spec_optest so on\n" +
                "mdo.test_item=so.test_item\n" +
                "WHERE 1=1 AND mdo.FACTORY='"+StrFactory+"' AND mdo.LINE_NUMBER='"+StrLine+"' AND mdo.PROJECT_NAME='"+StrProjectName+"' AND mdo.PART_NUMBER_V='"+StrPartNumberV+"'\n" +
                "AND mdo.DATETIME='"+date+"' AND mdo.TEST_CLASS='"+StrTestType+"' \n" +
                "order by mdo.Datetime,mdo.Test_Item,mdo.OP_Name,mdo.Remark_3";


        List<MeasureDataOPTestResultInfo> list_dcResult = new ArrayList<>();
        try {

            RowMapper<MeasureDataOPTestResultInfo> mapper = new BeanPropertyRowMapper<>(MeasureDataOPTestResultInfo.class);
            list_dcResult = jdbcTemplate.query(sql, mapper);

        } catch (Exception e) {
            e.printStackTrace();
            logger.error("MeasureDataOPTestDao-->ShhowMeasureDataOPTestResult1陷阱測試方法異常：" + e);
        }
        return list_dcResult;
    }//陷阱測試


    public List<MeasureDataOPTestRequireInfo> FactoryDropdownBoxIf(String StrFactory) {
        String sql = "SELECT distinct(LINE_NUMBER) FROM SPC.MEASURE_DATA_OPTest md WHERE md.Factory='"+StrFactory+"' ORDER BY LINE_NUMBER ASC";
        List<MeasureDataOPTestRequireInfo> list_dci = new ArrayList<>();
        try {
            RowMapper<MeasureDataOPTestRequireInfo> mapper = new BeanPropertyRowMapper<>(MeasureDataOPTestRequireInfo.class);
            list_dci = jdbcTemplate.query(sql, mapper);
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("MeasureDataOPTestDao-->DropdownBoxIf方法異常：" + e);
        }
        return list_dci;
    } //廠區下拉框點擊條件查詢

    public List<MeasureDataOPTestRequireInfo> ShowDay(String StrFactory,String StrLine,String StrPartNumberV,String date,String proName) {
        String sql = "";
        List<MeasureDataOPTestRequireInfo> list_dci = new ArrayList<>();
        try {
            RowMapper<MeasureDataOPTestRequireInfo> mapper = new BeanPropertyRowMapper<>(MeasureDataOPTestRequireInfo.class);
            sql = "SELECT DISTINCT (DATETIME) \n" +
                    "FROM SPC.MEASURE_DATA_OPTest md\n" +
                    " WHERE md.FACTORY='"+StrFactory+"' AND md.LINE_NUMBER='"+StrLine+"' AND md.PART_NUMBER_V='"+StrPartNumberV+"'\n" +
                    "AND substr(DATETIME,0,7)='"+date+"' AND PROJECT_NAME='"+proName+"' ORDER BY DATETIME ASC";
            list_dci = jdbcTemplate.query(sql, mapper);
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("MeasureDataOPTestDao-->ShowDay方法異常：" + e);
        }
        return list_dci;
    }//加載日期下拉框值

    public List<MeasureDataOPTestRequireInfo> ShowFactory() {
        String sql = "";
        List<MeasureDataOPTestRequireInfo> list_dci = new ArrayList<>();
        try {
            RowMapper<MeasureDataOPTestRequireInfo> mapper = new BeanPropertyRowMapper<>(MeasureDataOPTestRequireInfo.class);
            sql = "SELECT distinct(FACTORY) FROM SPC.MEASURE_DATA_OPTest WHERE 1=1 ORDER BY Factory ASC";
            list_dci = jdbcTemplate.query(sql, mapper);
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("MeasureDataOPTestDao-->ShowFactory方法異常：" + e);
        }
        return list_dci;
    }//加載廠區下拉框值

    public List<MeasureDataOPTestRequireInfo> ShowLine() {
        String sql = "";
        List<MeasureDataOPTestRequireInfo> list_dci = new ArrayList<>();
        try {
            RowMapper<MeasureDataOPTestRequireInfo> mapper = new BeanPropertyRowMapper<>(MeasureDataOPTestRequireInfo.class);
            sql = "SELECT distinct(LINE_NUMBER) FROM SPC.MEASURE_DATA_OPTest WHERE 1=1 ORDER BY LINE_NUMBER ASC";
            list_dci = jdbcTemplate.query(sql, mapper);
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("MeasureDataOPTestDao-->ShowLine方法異常：" + e);
        }
        return list_dci;
    }//加載綫別下拉框值

    public List<MeasureDataOPTestRequireInfo> ShowProjectName() {
        String sql = "";
        List<MeasureDataOPTestRequireInfo> list_dci = new ArrayList<>();
        try {
            RowMapper<MeasureDataOPTestRequireInfo> mapper = new BeanPropertyRowMapper<>(MeasureDataOPTestRequireInfo.class);
            sql = "SELECT distinct(PROJECT_NAME) FROM SPC.MEASURE_DATA_OPTest WHERE 1=1 ORDER BY PROJECT_NAME ASC";
            list_dci = jdbcTemplate.query(sql, mapper);
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("MeasureDataOPTestDao-->ShowProjectName方法異常：" + e);
        }
        return list_dci;
    }//加載專案名稱下拉框值
    public List<MeasureDataOPTestRequireInfo> ShowProjectName(String StrFactory,String StrLine) {
        String sql = "SELECT DISTINCT(PROJECT_NAME)\n" +
                "FROM SPC.MEASURE_DATA_OPTest md\n" +
                " WHERE md.FACTORY='"+StrFactory+"' AND md.LINE_NUMBER='"+StrLine+"' ORDER BY md.PROJECT_NAME ASC";
        List<MeasureDataOPTestRequireInfo> list_dci = new ArrayList<>();
        try {
            RowMapper<MeasureDataOPTestRequireInfo> mapper = new BeanPropertyRowMapper<>(MeasureDataOPTestRequireInfo.class);
            list_dci = jdbcTemplate.query(sql, mapper);
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("MeasureDataOPTestDao-->ShowProjectName方法異常：" + e);
        }
        return list_dci;
    }

    public List<MeasureDataOPTestRequireInfo> ShowPartNumberV() {
        String sql = "";
        List<MeasureDataOPTestRequireInfo> list_dci = new ArrayList<>();
        try {
            RowMapper<MeasureDataOPTestRequireInfo> mapper = new BeanPropertyRowMapper<>(MeasureDataOPTestRequireInfo.class);
            sql = "SELECT distinct(PART_NUMBER_V) FROM SPC.MEASURE_DATA_OPTest WHERE 1=1 ORDER BY PART_NUMBER_V ASC";
            list_dci = jdbcTemplate.query(sql, mapper);
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("MeasureDataOPTestDao-->ShowPartNumberV方法異常：" + e);
        }
        return list_dci;
    }//加載料號版本下拉框值
    public List<MeasureDataOPTestRequireInfo> ShowPartNumberV(String StrFactory,String StrLine) {
        String sql = "";
        List<MeasureDataOPTestRequireInfo> list_dci = new ArrayList<>();
        try {
            RowMapper<MeasureDataOPTestRequireInfo> mapper = new BeanPropertyRowMapper<>(MeasureDataOPTestRequireInfo.class);
            sql = "SELECT distinct(PART_NUMBER_V) FROM SPC.MEASURE_DATA_OPTest md WHERE md.FACTORY='"+StrFactory+"' AND md.LINE_NUMBER='"+StrLine+"' ORDER BY PART_NUMBER_V ASC";
            list_dci = jdbcTemplate.query(sql, mapper);
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("MeasureDataOPTestDao-->ShowPartNumberV方法異常：" + e);
        }
        return list_dci;
    }
}
