package com.foxlink.spc.dao;

import com.foxlink.spc.pojo.MeasureDataEleScaleRequireInfo;
import com.foxlink.spc.pojo.MeasureDataEleScaleResultInfo;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository("MeasureDataEleScaleDao")
public class MeasureDataEleScaleDao {
    private static Logger logger = Logger.getLogger(MeasureDataEleScaleDao.class);
    private JdbcTemplate jdbcTemplate;

    @Autowired
    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<MeasureDataEleScaleResultInfo> ShowMeasureDataEleScaleResult(String StrFactory,String StrLine,String StrPartNumberV,String StrStatus,String date,String proName) {
        String sqlStrProName=" AND Project_Name='"+proName+"'";
        if (proName.equals("")){ sqlStrProName=" AND 1=1"; }

        String sql = "SELECT DATETIME,WORKSHOP,INSPECTION_ITEM,INSPECTION_CONTENT,NOMINAL_DIM,UPPER_DIM,LOWER_DIM,STATUS,PERIOD,FREQUENCY," +
                "MEASURE_VALUE1,MEASURE_VALUE2,MEASURE_VALUE3,MEASURE_VALUE4,MEASURE_VALUE5," +
                " A_MEASURE_VALUE1,A_MEASURE_VALUE2,A_MEASURE_VALUE3,A_MEASURE_VALUE4,A_MEASURE_VALUE5," +
                "B_MEASURE_VALUE1,B_MEASURE_VALUE2,B_MEASURE_VALUE3,B_MEASURE_VALUE4,B_MEASURE_VALUE5,MEASURE_RESULT,PERSONNEL_ID" +
                " FROM SPC.MEASURE_DATA_BALANCE_TEMP md "+
                " WHERE md.FACTORY='"+StrFactory+"' AND md.LINE_NUMBER='"+StrLine+"' AND md.PART_NUMBER_V='"+StrPartNumberV+"'\n" +
                "AND md.STATUS='"+StrStatus+"' AND TO_CHAR(DATETIME,'yyyy-MM-dd')='"+date+"'"+sqlStrProName+" ORDER BY DATETIME ASC";
        List<MeasureDataEleScaleResultInfo> list_dcResult = new ArrayList<>();
        try {

            RowMapper<MeasureDataEleScaleResultInfo> mapper = new BeanPropertyRowMapper<>(MeasureDataEleScaleResultInfo.class);
            list_dcResult = jdbcTemplate.query(sql, mapper);

        } catch (Exception e) {
            e.printStackTrace();
            logger.error("MeasureDataEleScaleDao-->ShhowMeasureDataEleScaleResult方法異常：" + e);
        }
        return list_dcResult;
    }//按條件查表單

    public List<MeasureDataEleScaleRequireInfo> FactoryDropdownBoxIf(String StrFactory) {
        String sql = "SELECT distinct(LINE_NUMBER) FROM SPC.MEASURE_DATA_BALANCE_TEMP md WHERE md.Factory='"+StrFactory+"' ORDER BY LINE_NUMBER ASC";
        List<MeasureDataEleScaleRequireInfo> list_dci = new ArrayList<>();
        try {
            RowMapper<MeasureDataEleScaleRequireInfo> mapper = new BeanPropertyRowMapper<>(MeasureDataEleScaleRequireInfo.class);
            list_dci = jdbcTemplate.query(sql, mapper);
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("MeasureDataEleScaleDao-->DropdownBoxIf方法異常：" + e);
        }
        return list_dci;
    } //廠區下拉框點擊條件查詢

    public List<MeasureDataEleScaleRequireInfo> ShowDay(String StrFactory,String StrLine,String StrPartNumberV,String StrStatus,String date) {
        String sql = "";
        List<MeasureDataEleScaleRequireInfo> list_dci = new ArrayList<>();
        try {
            RowMapper<MeasureDataEleScaleRequireInfo> mapper = new BeanPropertyRowMapper<>(MeasureDataEleScaleRequireInfo.class);
            sql = "SELECT DISTINCT (TO_CHAR(DATETIME,'yyyy-MM-dd')) DATETIME \n" +
                    "FROM SPC.MEASURE_DATA_BALANCE_TEMP md\n" +
                    " WHERE md.FACTORY='"+StrFactory+"' AND md.LINE_NUMBER='"+StrLine+"' AND md.PART_NUMBER_V='"+StrPartNumberV+"'\n" +
                    "AND md.STATUS='"+StrStatus+"' AND TO_CHAR(DATETIME,'yyyy-MM')='"+date+"' ORDER BY DATETIME ASC";
            list_dci = jdbcTemplate.query(sql, mapper);
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("MeasureDataEleScaleDao-->ShowDay方法異常：" + e);
        }
        return list_dci;
    }//查詢下拉框日期的值

    public List<MeasureDataEleScaleRequireInfo> ShowFactory() {
        String sql = "";
        List<MeasureDataEleScaleRequireInfo> list_dci = new ArrayList<>();
        try {
            RowMapper<MeasureDataEleScaleRequireInfo> mapper = new BeanPropertyRowMapper<>(MeasureDataEleScaleRequireInfo.class);
            sql = "SELECT distinct(FACTORY) FROM SPC.MEASURE_DATA_BALANCE_TEMP WHERE 1=1 ORDER BY Factory ASC";
            list_dci = jdbcTemplate.query(sql, mapper);
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("MeasureDataEleScaleDao-->ShowFactory方法異常：" + e);
        }
        return list_dci;
    }//查詢廠區

    public List<MeasureDataEleScaleRequireInfo> ShowLine() {
        String sql = "";
        List<MeasureDataEleScaleRequireInfo> list_dci = new ArrayList<>();
        try {
            RowMapper<MeasureDataEleScaleRequireInfo> mapper = new BeanPropertyRowMapper<>(MeasureDataEleScaleRequireInfo.class);
            sql = "SELECT distinct(LINE_NUMBER) FROM SPC.MEASURE_DATA_BALANCE_TEMP WHERE 1=1 ORDER BY LINE_NUMBER ASC";
            list_dci = jdbcTemplate.query(sql, mapper);
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("MeasureDataEleScaleDao-->ShowLine方法異常：" + e);
        }
        return list_dci;
    }//查詢綫別

    public List<MeasureDataEleScaleRequireInfo> ShowProjectName() {
        String sql = "";
        List<MeasureDataEleScaleRequireInfo> list_dci = new ArrayList<>();
        try {
            RowMapper<MeasureDataEleScaleRequireInfo> mapper = new BeanPropertyRowMapper<>(MeasureDataEleScaleRequireInfo.class);
            sql = "SELECT distinct(PROJECT_NAME) FROM SPC.MEASURE_DATA_BALANCE_TEMP WHERE 1=1 ORDER BY PROJECT_NAME ASC";
            list_dci = jdbcTemplate.query(sql, mapper);
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("MeasureDataEleScaleDao-->ShowProjectName方法異常：" + e);
        }
        return list_dci;
    }
    public List<MeasureDataEleScaleRequireInfo> ShowProjectName(String StrFactory,String StrLine) {
        String sql = "SELECT DISTINCT(PROJECT_NAME)\n" +
                "FROM SPC.MEASURE_DATA_BALANCE_TEMP md\n" +
                " WHERE md.FACTORY='"+StrFactory+"' AND md.LINE_NUMBER='"+StrLine+"' ORDER BY md.PROJECT_NAME ASC";
        List<MeasureDataEleScaleRequireInfo> list_dci = new ArrayList<>();
        try {
            RowMapper<MeasureDataEleScaleRequireInfo> mapper = new BeanPropertyRowMapper<>(MeasureDataEleScaleRequireInfo.class);
            list_dci = jdbcTemplate.query(sql, mapper);
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("MeasureDataEleScaleDao-->ShowProjectName方法異常：" + e);
        }
        return list_dci;
    }

    public List<MeasureDataEleScaleRequireInfo> ShowPartNumberV() {
        String sql = "";
        List<MeasureDataEleScaleRequireInfo> list_dci = new ArrayList<>();
        try {
            RowMapper<MeasureDataEleScaleRequireInfo> mapper = new BeanPropertyRowMapper<>(MeasureDataEleScaleRequireInfo.class);
            sql = "SELECT distinct(PART_NUMBER_V) FROM SPC.MEASURE_DATA_BALANCE_TEMP WHERE 1=1 ORDER BY PART_NUMBER_V ASC";
            list_dci = jdbcTemplate.query(sql, mapper);
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("MeasureDataEleScaleDao-->ShowPartNumberV方法異常：" + e);
        }
        return list_dci;
    }
    public List<MeasureDataEleScaleRequireInfo> ShowPartNumberV(String StrFactory,String StrLine,String ProName) {
        String sql = "";
        List<MeasureDataEleScaleRequireInfo> list_dci = new ArrayList<>();
        if (ProName!=""){
            try {
                RowMapper<MeasureDataEleScaleRequireInfo> mapper = new BeanPropertyRowMapper<>(MeasureDataEleScaleRequireInfo.class);
                sql = "SELECT distinct(PART_NUMBER_V) FROM SPC.MEASURE_DATA_BALANCE_TEMP md WHERE md.FACTORY='"+StrFactory+"' AND md.LINE_NUMBER='"+StrLine+"' AND md.PROJECT_NAME='"+ProName+"' ORDER BY PART_NUMBER_V ASC";
                list_dci = jdbcTemplate.query(sql, mapper);
            } catch (Exception e) {
                e.printStackTrace();
                logger.error("MeasureDataDao-->ShowPartNumberV_ProName方法異常：" + e);
            }
            return list_dci;
        }
        try {
            RowMapper<MeasureDataEleScaleRequireInfo> mapper = new BeanPropertyRowMapper<>(MeasureDataEleScaleRequireInfo.class);
            sql = "SELECT distinct(PART_NUMBER_V) FROM SPC.MEASURE_DATA_BALANCE_TEMP md WHERE md.FACTORY='"+StrFactory+"' AND md.LINE_NUMBER='"+StrLine+"' ORDER BY PART_NUMBER_V ASC";
            list_dci = jdbcTemplate.query(sql, mapper);
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("MeasureDataEleScaleDao-->ShowPartNumberV方法異常：" + e);
        }
        return list_dci;
    }

    public List<MeasureDataEleScaleRequireInfo> ShowStatus() {
        String sql = "";
        List<MeasureDataEleScaleRequireInfo> list_dci = new ArrayList<>();
        try {
            RowMapper<MeasureDataEleScaleRequireInfo> mapper = new BeanPropertyRowMapper<>(MeasureDataEleScaleRequireInfo.class);
            sql = "SELECT distinct(STATUS) FROM SPC.MEASURE_DATA_BALANCE_TEMP WHERE 1=1 ORDER BY STATUS ASC";
            list_dci = jdbcTemplate.query(sql, mapper);
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("MeasureDataEleScaleDao-->ShowStatus方法異常：" + e);
        }
        return list_dci;
    }
    public List<MeasureDataEleScaleRequireInfo> ShowStatus(String StrFactory,String StrLine,String ProName,String PartVerion) {
        String sql = "";
        List<MeasureDataEleScaleRequireInfo> list_dci = new ArrayList<>();
        if (ProName!="" && PartVerion!=""){
            try {
                RowMapper<MeasureDataEleScaleRequireInfo> mapper = new BeanPropertyRowMapper<>(MeasureDataEleScaleRequireInfo.class);
                sql = "SELECT distinct(STATUS) FROM SPC.MEASURE_DATA_BALANCE_TEMP md  WHERE md.FACTORY='"+StrFactory+"' AND md.LINE_NUMBER='"+StrLine+"' AND md.PROJECT_NAME='"+ProName+"' AND md.PART_NUMBER_V='"+PartVerion+"' ORDER BY md.STATUS ASC";
                list_dci = jdbcTemplate.query(sql, mapper);
            } catch (Exception e) {
                e.printStackTrace();
                logger.error("MeasureDataDao-->ShowStatus_partVerion方法異常：" + e);
            }
            return list_dci;
        }
        try {
            RowMapper<MeasureDataEleScaleRequireInfo> mapper = new BeanPropertyRowMapper<>(MeasureDataEleScaleRequireInfo.class);
            sql = "SELECT distinct(STATUS) FROM SPC.MEASURE_DATA_BALANCE_TEMP md  WHERE md.FACTORY='"+StrFactory+"' AND md.LINE_NUMBER='"+StrLine+"' AND md.PART_NUMBER_V='"+PartVerion+"' ORDER BY md.STATUS ASC";
            list_dci = jdbcTemplate.query(sql, mapper);
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("MeasureDataEleScaleDao-->ShowStatus方法異常：" + e);
        }
        return list_dci;
    }
}
