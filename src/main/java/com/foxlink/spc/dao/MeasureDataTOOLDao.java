package com.foxlink.spc.dao;
import com.foxlink.spc.pojo.MeasureDataTOOLRequireInfo;
import com.foxlink.spc.pojo.MeasureDataTOOLResultInfo;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import java.util.ArrayList;
import java.util.List;

@Repository("MeasureDataTOOLDao")
public class MeasureDataTOOLDao {
    private static Logger logger = Logger.getLogger(MeasureDataTOOLDao.class);
    private JdbcTemplate jdbcTemplate;

    @Autowired
    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<MeasureDataTOOLResultInfo> ShowMeasureDataTOOLResult() {
        String sql="SELECT DATETIME,INSPECTION_ITEM,DEVICE_NUM,INSPECTION_RESULT,PERSONNEL_ID,BATCH_REMARK FROM SPC.MEASURE_DATA_TOOL";
        List<MeasureDataTOOLResultInfo> list_dcResult = new ArrayList<>();
        try {

            RowMapper<MeasureDataTOOLResultInfo> mapper = new BeanPropertyRowMapper<>(MeasureDataTOOLResultInfo.class);
            list_dcResult = jdbcTemplate.query(sql, mapper);

        } catch (Exception e) {
            e.printStackTrace();
            logger.error("MeasureDataTOOLDao-->ShhowMeasureDataTOOLResult方法異常：" + e);
        }
        return list_dcResult;
    }
    public List<MeasureDataTOOLResultInfo> ShowMeasureDataTOOLResult(String StrFactory,String StrLine,String StrPartNumberV,String date,String StrProjectName ) {
        String sql = "SELECT DATETIME,INSPECTION_ITEM,DEVICE_NUM,INSPECTION_RESULT,PERSONNEL_ID,BATCH_REMARK FROM SPC.MEASURE_DATA_TOOL\n" +
                "WHERE FACTORY='"+StrFactory+"' AND LINE_NUMBER='"+StrLine+"' AND PROJECT_NAME='"+StrProjectName+"' AND PART_NUMBER_V='"+StrPartNumberV+"' AND TO_CHAR(DATETIME,'yyyy-MM-dd')='"+date+"'";


        List<MeasureDataTOOLResultInfo> list_dcResult = new ArrayList<>();
        try {

            RowMapper<MeasureDataTOOLResultInfo> mapper = new BeanPropertyRowMapper<>(MeasureDataTOOLResultInfo.class);
            list_dcResult = jdbcTemplate.query(sql, mapper);

        } catch (Exception e) {
            e.printStackTrace();
            logger.error("MeasureDataTOOLDao-->ShhowMeasureDataTOOLResult方法異常：" + e);
        }
        return list_dcResult;
    }

    public List<MeasureDataTOOLRequireInfo> FactoryDropdownBoxIf(String StrFactory) {
        String sql = "SELECT distinct(LINE_NUMBER) FROM SPC.MEASURE_DATA_TOOL md WHERE md.Factory='"+StrFactory+"' ORDER BY LINE_NUMBER ASC";
        List<MeasureDataTOOLRequireInfo> list_dci = new ArrayList<>();
        try {
            RowMapper<MeasureDataTOOLRequireInfo> mapper = new BeanPropertyRowMapper<>(MeasureDataTOOLRequireInfo.class);
            list_dci = jdbcTemplate.query(sql, mapper);
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("MeasureDataTOOLDao-->DropdownBoxIf方法異常：" + e);
        }
        return list_dci;
    } //廠區下拉框點擊條件查詢

    public List<MeasureDataTOOLRequireInfo> ShowDay(String StrFactory,String StrLine,String StrPartNumberV,String date,String proName) {
        String sql = "";
        List<MeasureDataTOOLRequireInfo> list_dci = new ArrayList<>();
        try {
            RowMapper<MeasureDataTOOLRequireInfo> mapper = new BeanPropertyRowMapper<>(MeasureDataTOOLRequireInfo.class);
            sql = "SELECT DISTINCT (TO_CHAR(DATETIME,'yyyy-MM-dd')) DATETIME \n" +
                    "FROM SPC.MEASURE_DATA_TOOL md\n" +
                    " WHERE md.FACTORY='"+StrFactory+"' AND md.LINE_NUMBER='"+StrLine+"' AND md.PART_NUMBER_V='"+StrPartNumberV+"'\n" +
                    "AND TO_CHAR(DATETIME,'yyyy-MM')='"+date+"' AND PROJECT_NAME='"+proName+"' ORDER BY DATETIME ASC";
            list_dci = jdbcTemplate.query(sql, mapper);
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("MeasureDataTOOLDao-->ShowDay方法異常：" + e);
        }
        return list_dci;
    }//加載日期下拉框值

    public List<MeasureDataTOOLRequireInfo> ShowFactory() {
        String sql = "";
        List<MeasureDataTOOLRequireInfo> list_dci = new ArrayList<>();
        try {
            RowMapper<MeasureDataTOOLRequireInfo> mapper = new BeanPropertyRowMapper<>(MeasureDataTOOLRequireInfo.class);
            sql = "SELECT distinct(FACTORY) FROM SPC.MEASURE_DATA_TOOL WHERE 1=1 ORDER BY Factory ASC";
            list_dci = jdbcTemplate.query(sql, mapper);
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("MeasureDataTOOLDao-->ShowFactory方法異常：" + e);
        }
        return list_dci;
    }//加載廠區下拉框值

    public List<MeasureDataTOOLRequireInfo> ShowLine() {
        String sql = "";
        List<MeasureDataTOOLRequireInfo> list_dci = new ArrayList<>();
        try {
            RowMapper<MeasureDataTOOLRequireInfo> mapper = new BeanPropertyRowMapper<>(MeasureDataTOOLRequireInfo.class);
            sql = "SELECT distinct(LINE_NUMBER) FROM SPC.MEASURE_DATA_TOOL WHERE 1=1 ORDER BY LINE_NUMBER ASC";
            list_dci = jdbcTemplate.query(sql, mapper);
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("MeasureDataTOOLDao-->ShowLine方法異常：" + e);
        }
        return list_dci;
    }//加載綫別下拉框值

    public List<MeasureDataTOOLRequireInfo> ShowProjectName() {
        String sql = "";
        List<MeasureDataTOOLRequireInfo> list_dci = new ArrayList<>();
        try {
            RowMapper<MeasureDataTOOLRequireInfo> mapper = new BeanPropertyRowMapper<>(MeasureDataTOOLRequireInfo.class);
            sql = "SELECT distinct(PROJECT_NAME) FROM SPC.MEASURE_DATA_TOOL WHERE 1=1 ORDER BY PROJECT_NAME ASC";
            list_dci = jdbcTemplate.query(sql, mapper);
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("MeasureDataTOOLDao-->ShowProjectName方法異常：" + e);
        }
        return list_dci;
    }//加載專案名稱下拉框值
    public List<MeasureDataTOOLRequireInfo> ShowProjectName(String StrFactory,String StrLine) {
        String sql = "SELECT DISTINCT(PROJECT_NAME)\n" +
                "FROM SPC.MEASURE_DATA_TOOL md\n" +
                " WHERE md.FACTORY='"+StrFactory+"' AND md.LINE_NUMBER='"+StrLine+"' ORDER BY md.PROJECT_NAME ASC";
        List<MeasureDataTOOLRequireInfo> list_dci = new ArrayList<>();
        try {
            RowMapper<MeasureDataTOOLRequireInfo> mapper = new BeanPropertyRowMapper<>(MeasureDataTOOLRequireInfo.class);
            list_dci = jdbcTemplate.query(sql, mapper);
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("MeasureDataTOOLDao-->ShowProjectName方法異常：" + e);
        }
        return list_dci;
    }

    public List<MeasureDataTOOLRequireInfo> ShowPartNumberV() {
        String sql = "";
        List<MeasureDataTOOLRequireInfo> list_dci = new ArrayList<>();
        try {
            RowMapper<MeasureDataTOOLRequireInfo> mapper = new BeanPropertyRowMapper<>(MeasureDataTOOLRequireInfo.class);
            sql = "SELECT distinct(PART_NUMBER_V) FROM SPC.MEASURE_DATA_TOOL WHERE 1=1 ORDER BY PART_NUMBER_V ASC";
            list_dci = jdbcTemplate.query(sql, mapper);
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("MeasureDataTOOLDao-->ShowPartNumberV方法異常：" + e);
        }
        return list_dci;
    }//加載料號版本下拉框值
    public List<MeasureDataTOOLRequireInfo> ShowPartNumberV(String StrFactory,String StrLine,String proName) {
        String sql = "";
        List<MeasureDataTOOLRequireInfo> list_dci = new ArrayList<>();
            try {
                RowMapper<MeasureDataTOOLRequireInfo> mapper = new BeanPropertyRowMapper<>(MeasureDataTOOLRequireInfo.class);
                sql = "SELECT distinct(PART_NUMBER_V) FROM SPC.MEASURE_DATA_TOOL md WHERE md.FACTORY='"+StrFactory+"' AND md.LINE_NUMBER='"+StrLine+"' AND PROJECT_NAME ='"+proName+"' ORDER BY PART_NUMBER_V ASC";
                list_dci = jdbcTemplate.query(sql, mapper);
            } catch (Exception e) {
                e.printStackTrace();
                logger.error("MeasureDataTOOLDao-->ShowPartNumberV方法異常：" + e);
            }
            return list_dci;

    }
}
