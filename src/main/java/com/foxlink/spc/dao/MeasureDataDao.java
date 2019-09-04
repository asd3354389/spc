package com.foxlink.spc.dao;

import com.foxlink.spc.pojo.MeasureDataRequireInfo;
import com.foxlink.spc.pojo.MeasureDataResultInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import org.apache.log4j.Logger;
import java.util.ArrayList;
import java.util.List;
@Repository("MeasureDataDao")
public class MeasureDataDao {
    private static Logger logger = Logger.getLogger(MeasureDataDao.class);
    private JdbcTemplate jdbcTemplate;

    @Autowired
    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<MeasureDataRequireInfo> ShowMeasureDataRequire() {
        String sql = "SELECT FACTORY,LINE_NUMBER,PROJECT_NAME,PART_NUMBER_V,STATUS FROM SPC.MEASURE_DATA";
        List<MeasureDataRequireInfo> list_dci = new ArrayList<>();
        try {
            RowMapper<MeasureDataRequireInfo> mapper = new BeanPropertyRowMapper<>(MeasureDataRequireInfo.class);
            list_dci = jdbcTemplate.query(sql, mapper);
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("MeasureDataDao-->ShowMeasureDataRequire方法異常：" + e);
        }
        return list_dci;
    }//查詢下拉框的所有值

    public List<MeasureDataResultInfo> ShowMeasureDataResult() {
        String sql = "SELECT DATETIME ,WORKSHOP ,INSPECTION_ITEM ,INSPECTION_CONTENT ,NOMINAL_DIM ,UPPER_DIM ,LOWER_DIM ,STATUS ,PERIOD ,FREQUENCY , INSPECTION_METHOD ,SPC_NUM ,MEASURE_VALUE1 ,MEASURE_VALUE2 ,MEASURE_VALUE3 ,MEASURE_VALUE4 ,MEASURE_VALUE5 ,MEASURE_VALUE6 , MEASURE_VALUE7 ,MEASURE_VALUE8 ,MEASURE_VALUE9 ,MEASURE_VALUE10 ,MEASURE_VALUE11 ,MEASURE_VALUE12 ,MEASURE_VALUE13 ,MEASURE_VALUE14 , MEASURE_VALUE15 ,MEASURE_VALUE16 ,MEASURE_VALUE17 ,MEASURE_VALUE18 ,MEASURE_VALUE19 ,MEASURE_VALUE20 ,MEASURE_VALUE21 ,MEASURE_VALUE22 , MEASURE_VALUE23 ,MEASURE_VALUE24 ,MEASURE_VALUE25 ,MEASURE_VALUE26 ,MEASURE_VALUE27 ,MEASURE_VALUE28 ,MEASURE_VALUE29 ,MEASURE_VALUE30 , MEASURE_VALUE31 ,MEASURE_VALUE32 ,MEASURE_RESULT ,PERSONNEL_ID  FROM SPC.MEASURE_DATA";
        List<MeasureDataResultInfo> list_dcResult = new ArrayList<>();
        try {

            RowMapper<MeasureDataResultInfo> mapper = new BeanPropertyRowMapper<>(MeasureDataResultInfo.class);
            list_dcResult = jdbcTemplate.query(sql, mapper);

        } catch (Exception e) {
            e.printStackTrace();
            logger.error("MeasureDataDao-->ShhowMeasureDataResult方法異常：" + e);
        }
        return list_dcResult;
    }//查詢表單

    public List<MeasureDataResultInfo> ShowMeasureDataResult(String StrFactory,String StrLine,String StrPartNumberV,String StrStatus,String date) {
        String sql = "SELECT DATETIME ,WORKSHOP ,INSPECTION_ITEM ,INSPECTION_CONTENT ,NOMINAL_DIM ,UPPER_DIM" +
                " ,LOWER_DIM ,STATUS ,PERIOD ,FREQUENCY , INSPECTION_METHOD ,SPC_NUM " +
                ",MEASURE_VALUE1 ,MEASURE_VALUE2 ,MEASURE_VALUE3 ,MEASURE_VALUE4 ,MEASURE_VALUE5" +
                " ,MEASURE_VALUE6 , MEASURE_VALUE7 ,MEASURE_VALUE8 ,MEASURE_VALUE9 ,MEASURE_VALUE10 " +
                ",MEASURE_VALUE11 ,MEASURE_VALUE12 ,MEASURE_VALUE13 ,MEASURE_VALUE14 , MEASURE_VALUE15 " +
                ",MEASURE_VALUE16 ,MEASURE_VALUE17 ,MEASURE_VALUE18 ,MEASURE_VALUE19 ,MEASURE_VALUE20 " +
                ",MEASURE_VALUE21 ,MEASURE_VALUE22 , MEASURE_VALUE23 ,MEASURE_VALUE24 ,MEASURE_VALUE25 " +
                ",MEASURE_VALUE26 ,MEASURE_VALUE27 ,MEASURE_VALUE28 ,MEASURE_VALUE29 ,MEASURE_VALUE30 " +
                ", MEASURE_VALUE31 ,MEASURE_VALUE32 ,MEASURE_RESULT ,PERSONNEL_ID  " +
                "FROM SPC.MEASURE_DATA md " +
                "WHERE md.FACTORY='"+StrFactory+"' AND md.LINE_NUMBER='"+StrLine+"' AND md.PART_NUMBER_V='"+StrPartNumberV+"'\n" +
                "AND md.STATUS='"+StrStatus+"' AND TO_CHAR(DATETIME,'yyyy-MM-dd')='"+date+"' ORDER BY DATETIME ASC";
        List<MeasureDataResultInfo> list_dcResult = new ArrayList<>();
        try {

            RowMapper<MeasureDataResultInfo> mapper = new BeanPropertyRowMapper<>(MeasureDataResultInfo.class);
            list_dcResult = jdbcTemplate.query(sql, mapper);

        } catch (Exception e) {
            e.printStackTrace();
            logger.error("MeasureDataDao-->ShhowMeasureDataResult方法異常：" + e);
        }
        return list_dcResult;
    }//按條件查表單

    public List<MeasureDataRequireInfo> FactoryDropdownBoxIf(String StrFactory) {
        String sql = "SELECT distinct(LINE_NUMBER) FROM SPC.MEASURE_DATA md WHERE md.Factory='"+StrFactory+"' ORDER BY LINE_NUMBER ASC";
        List<MeasureDataRequireInfo> list_dci = new ArrayList<>();
        try {
            RowMapper<MeasureDataRequireInfo> mapper = new BeanPropertyRowMapper<>(MeasureDataRequireInfo.class);
            list_dci = jdbcTemplate.query(sql, mapper);
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("MeasureDataDao-->DropdownBoxIf方法異常：" + e);
        }
        return list_dci;
    } //廠區下拉框點擊條件查詢

    public List<MeasureDataRequireInfo> ShowDay(String StrFactory,String StrLine,String StrPartNumberV,String StrStatus,String date) {
        String sql = "";
        List<MeasureDataRequireInfo> list_dci = new ArrayList<>();
        try {
            RowMapper<MeasureDataRequireInfo> mapper = new BeanPropertyRowMapper<>(MeasureDataRequireInfo.class);
            sql = "SELECT DISTINCT (TO_CHAR(DATETIME,'yyyy-MM-dd')) DATETIME \n" +
                    "FROM SPC.MEASURE_DATA md\n" +
                    " WHERE md.FACTORY='"+StrFactory+"' AND md.LINE_NUMBER='"+StrLine+"' AND md.PART_NUMBER_V='"+StrPartNumberV+"'\n" +
                    "AND md.STATUS='"+StrStatus+"' AND TO_CHAR(DATETIME,'yyyy-MM')='"+date+"' ORDER BY DATETIME ASC";
            list_dci = jdbcTemplate.query(sql, mapper);
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("MeasureDataDao-->ShowDay方法異常：" + e);
        }
        return list_dci;
    }//查詢下拉框日期的值

    public List<MeasureDataRequireInfo> ShowFactory() {
        String sql = "";
        List<MeasureDataRequireInfo> list_dci = new ArrayList<>();
        try {
            RowMapper<MeasureDataRequireInfo> mapper = new BeanPropertyRowMapper<>(MeasureDataRequireInfo.class);
            sql = "SELECT distinct(FACTORY) FROM SPC.MEASURE_DATA WHERE 1=1 ORDER BY Factory ASC";
            list_dci = jdbcTemplate.query(sql, mapper);
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("MeasureDataDao-->ShowFactory方法異常：" + e);
        }
        return list_dci;
    }//查詢廠區

    public List<MeasureDataRequireInfo> ShowLine() {
        String sql = "";
        List<MeasureDataRequireInfo> list_dci = new ArrayList<>();
        try {
            RowMapper<MeasureDataRequireInfo> mapper = new BeanPropertyRowMapper<>(MeasureDataRequireInfo.class);
            sql = "SELECT distinct(LINE_NUMBER) FROM SPC.MEASURE_DATA WHERE 1=1 ORDER BY LINE_NUMBER ASC";
            list_dci = jdbcTemplate.query(sql, mapper);
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("MeasureDataDao-->ShowLine方法異常：" + e);
        }
        return list_dci;
    }//查詢綫別

    public List<MeasureDataRequireInfo> ShowProjectName() {
        String sql = "";
        List<MeasureDataRequireInfo> list_dci = new ArrayList<>();
        try {
            RowMapper<MeasureDataRequireInfo> mapper = new BeanPropertyRowMapper<>(MeasureDataRequireInfo.class);
            sql = "SELECT distinct(PROJECT_NAME) FROM SPC.MEASURE_DATA WHERE 1=1 ORDER BY PROJECT_NAME ASC";
            list_dci = jdbcTemplate.query(sql, mapper);
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("MeasureDataDao-->ShowProjectName方法異常：" + e);
        }
        return list_dci;
    }
    public List<MeasureDataRequireInfo> ShowProjectName(String StrFactory,String StrLine) {
        String sql = "SELECT DISTINCT(PROJECT_NAME)\n" +
                "FROM SPC.MEASURE_DATA md\n" +
                " WHERE md.FACTORY='"+StrFactory+"' AND md.LINE_NUMBER='"+StrLine+"' ORDER BY md.PROJECT_NAME ASC";
        List<MeasureDataRequireInfo> list_dci = new ArrayList<>();
        try {
            RowMapper<MeasureDataRequireInfo> mapper = new BeanPropertyRowMapper<>(MeasureDataRequireInfo.class);
            list_dci = jdbcTemplate.query(sql, mapper);
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("MeasureDataDao-->ShowProjectName方法異常：" + e);
        }
        return list_dci;
    }

    public List<MeasureDataRequireInfo> ShowPartNumberV() {
        String sql = "";
        List<MeasureDataRequireInfo> list_dci = new ArrayList<>();
        try {
            RowMapper<MeasureDataRequireInfo> mapper = new BeanPropertyRowMapper<>(MeasureDataRequireInfo.class);
            sql = "SELECT distinct(PART_NUMBER_V) FROM SPC.MEASURE_DATA WHERE 1=1 ORDER BY PART_NUMBER_V ASC";
            list_dci = jdbcTemplate.query(sql, mapper);
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("MeasureDataDao-->ShowPartNumberV方法異常：" + e);
        }
        return list_dci;
    }
    public List<MeasureDataRequireInfo> ShowPartNumberV(String StrFactory,String StrLine,String ProName) {
        String sql = "";
        List<MeasureDataRequireInfo> list_dci = new ArrayList<>();
        if (ProName!=""){
            try {
                RowMapper<MeasureDataRequireInfo> mapper = new BeanPropertyRowMapper<>(MeasureDataRequireInfo.class);
                sql = "SELECT distinct(PART_NUMBER_V) FROM SPC.MEASURE_DATA md WHERE md.FACTORY='"+StrFactory+"' AND md.LINE_NUMBER='"+StrLine+"' AND md.PROJECT_NAME='"+ProName+"' ORDER BY PART_NUMBER_V ASC";
                list_dci = jdbcTemplate.query(sql, mapper);
            } catch (Exception e) {
                e.printStackTrace();
                logger.error("MeasureDataDao-->ShowPartNumberV_ProName方法異常：" + e);
            }
            return list_dci;
        }
             try {
            RowMapper<MeasureDataRequireInfo> mapper = new BeanPropertyRowMapper<>(MeasureDataRequireInfo.class);
            sql = "SELECT distinct(PART_NUMBER_V) FROM SPC.MEASURE_DATA md WHERE md.FACTORY='"+StrFactory+"' AND md.LINE_NUMBER='"+StrLine+"' ORDER BY PART_NUMBER_V ASC";
            list_dci = jdbcTemplate.query(sql, mapper);
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("MeasureDataDao-->ShowPartNumberV方法異常：" + e);
        }
        return list_dci;

    }

    public List<MeasureDataRequireInfo> ShowStatus() {
        String sql = "";
        List<MeasureDataRequireInfo> list_dci = new ArrayList<>();
        try {
            RowMapper<MeasureDataRequireInfo> mapper = new BeanPropertyRowMapper<>(MeasureDataRequireInfo.class);
            sql = "SELECT distinct(STATUS) FROM SPC.MEASURE_DATA WHERE 1=1 ORDER BY STATUS ASC";
            list_dci = jdbcTemplate.query(sql, mapper);
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("MeasureDataDao-->ShowStatus方法異常：" + e);
        }
        return list_dci;
    }
    public List<MeasureDataRequireInfo> ShowStatus(String StrFactory,String StrLine,String proName,String partVerion) {
        String sql = "";
        List<MeasureDataRequireInfo> list_dci = new ArrayList<>();
        if (proName!="" && partVerion!=""){
            try {
                RowMapper<MeasureDataRequireInfo> mapper = new BeanPropertyRowMapper<>(MeasureDataRequireInfo.class);
                sql = "SELECT distinct(STATUS) FROM SPC.MEASURE_DATA md  WHERE md.FACTORY='"+StrFactory+"' AND md.LINE_NUMBER='"+StrLine+"' AND md.PROJECT_NAME='"+proName+"' AND md.PART_NUMBER_V='"+partVerion+"' ORDER BY md.STATUS ASC";
                list_dci = jdbcTemplate.query(sql, mapper);
            } catch (Exception e) {
                e.printStackTrace();
                logger.error("MeasureDataDao-->ShowStatus_partVerion方法異常：" + e);
            }
            return list_dci;
        }
        try {
            RowMapper<MeasureDataRequireInfo> mapper = new BeanPropertyRowMapper<>(MeasureDataRequireInfo.class);
            sql = "SELECT distinct(STATUS) FROM SPC.MEASURE_DATA md  WHERE md.FACTORY='"+StrFactory+"' AND md.LINE_NUMBER='"+StrLine+"' AND md.PART_NUMBER_V='"+partVerion+"' ORDER BY md.STATUS ASC";
            list_dci = jdbcTemplate.query(sql, mapper);
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("MeasureDataDao-->ShowStatus方法異常：" + e);
        }
        return list_dci;
    }
}
