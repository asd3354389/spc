package com.foxlink.spc.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.foxlink.spc.pojo.LineNumber;
import com.foxlink.spc.pojo.Measure;
import com.foxlink.spc.pojo.MeasureDataResultInfo;
import com.foxlink.spc.pojo.SPEC;
import com.foxlink.spc.pojo.SPECMeasure;
import com.foxlink.spc.pojo.SaveMeasure;


@Repository("foxlinkMeasureDao")
public class FoxlinkMeasureDao {
	private static Logger logger=Logger.getLogger(FoxlinkMeasureDao.class);
	private JdbcTemplate jdbcTemplate;
	@Autowired
	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	public List ShowAllFactory() {
		// TODO Auto-generated method stub
		String sql = "SELECT DISTINCT(Factory) FROM spc.line_number WHERE 1=1  ORDER BY Factory ASC ";
		List Factorylist = new ArrayList<>();
		try{
			Factorylist = jdbcTemplate.queryForList(sql);
		}catch(Exception e){
			logger.error("查詢所有廠區失敗，原因："+e);
			e.printStackTrace();
		}
		return Factorylist;
	}

	public List ShowAllProjectName() {
		// TODO Auto-generated method stub
		String sql = "SELECT DISTINCT(Project_Name) FROM spc.spec WHERE 1=1  ORDER BY Project_Name ASC ";
		List ProjectNamelist = new ArrayList<>();
		try{
			ProjectNamelist = jdbcTemplate.queryForList(sql);
		}catch(Exception e){
			logger.error("查詢所有廠區失敗，原因："+e);
			e.printStackTrace();
		}
		return ProjectNamelist;
	}

	public List ShowLink(String factory) {
		// TODO Auto-generated method stub
		String sql = "SELECT DISTINCT(Line_Number) FROM spc.Line_Number WHERE 1=1 AND Factory= ? ORDER BY Line_Number ASC";
		List Linklist = new ArrayList<>();
		try{
			Object[] a =new Object[1];
			a[0] = factory;
			Linklist = jdbcTemplate.queryForList(sql,a);
		}catch(Exception e){
			logger.error("查詢所有廠區失敗，原因："+e);
			e.printStackTrace();
		}
		return Linklist;
	}

	public List ShowPartVerion(String projectName) {
		// TODO Auto-generated method stub
		String sql = "SELECT DISTINCT(Part_Number_V) FROM spc.spec WHERE 1=1 ";
		List PartVersionlist = new ArrayList<>();
		
		if(projectName == null || projectName == ""){
			sql += " ORDER BY Part_Number_V ASC ";
			try{
				PartVersionlist = jdbcTemplate.queryForList(sql);
			}catch(Exception e){
				logger.error("查詢料號版本失敗，原因："+e);
				e.printStackTrace();
			}
		}else{
			sql += "AND Project_Name= ? ORDER BY Part_Number_V ASC ";
			try{
				Object[] a =new Object[1];
				a[0] = projectName;
				PartVersionlist = jdbcTemplate.queryForList(sql,a);
			}catch(Exception e){
				logger.error("查詢料號版本失敗，原因："+e);
				e.printStackTrace();
			}
		}
		
		return PartVersionlist;
	}

	public List ShowStutas(String partVersion) {
		// TODO Auto-generated method stub
		String sql = "SELECT DISTINCT(Status) FROM spc.spec WHERE 1=1 AND Part_Number_V = ? ORDER BY Status ASC ";
		List Stutaslist = new ArrayList<>();
		try{
			Object[] a =new Object[1];
			a[0] = partVersion;
			Stutaslist = jdbcTemplate.queryForList(sql,a);
		}catch(Exception e){
			logger.error("查詢量測階段失敗，原因："+e);
			e.printStackTrace();
		}
		return Stutaslist;
	}

	public List ShowFrequency(String pro_Name, String part_version, String stutas) {
		// TODO Auto-generated method stub
		String sql = "SELECT Frequency FROM spc.spec WHERE 1=1  ";
		List Frequencylist = new ArrayList<>();
		List params = new ArrayList<>();
		System.out.println(pro_Name+"----"+part_version+"----"+stutas);
		try{
			if(!pro_Name.equals("")&&pro_Name!=null){
				sql += " AND Project_Name = ? ";
				params.add(pro_Name);
			}
			sql += " AND Part_Number_V = ? AND Status = ? AND Inspection_Method like '2.5D'  group by Frequency ";
			params.add(part_version);
			params.add(stutas);
			Frequencylist = jdbcTemplate.queryForList(sql,params.toArray());
		}catch(Exception e){
			logger.error("查詢量測階段失敗，原因："+e);
			e.printStackTrace();
		}
		return Frequencylist;
	}

	public List ShowMeasureTable(SaveMeasure saveMeasure) {
		// TODO Auto-generated method stub
		String sql = "SELECT WorkShop,Inspection_Item,Inspection_Content,Inspection_Method"
				+ ",to_char(Nominal_Dim,'FM9999990.000') Nominal_Dim,to_char(Upper_Dim,'FM9999990.000') Upper_Dim,to_char(Lower_Dim,'FM9999990.000') Lower_Dim,Frequency,Status,Remark1,SPC_NUM,DIM_Location FROM spc.spec WHERE 1=1 ";
		List<SPECMeasure> ListSpec = new ArrayList<SPECMeasure>();
		List params = new ArrayList<>();
		try{
			if(!saveMeasure.getProjectName().equals("")){
				sql += " AND Project_Name = ? ";
				params.add(saveMeasure.getProjectName());
			}
			sql += " AND Part_Number_V=? AND Status=? AND Frequency=? AND Inspection_Method like '2.5D' order by Workshop";
			params.add(saveMeasure.getPartVersion());
			params.add(saveMeasure.getStatus());
			params.add(saveMeasure.getFrequency());
			RowMapper<SPECMeasure> mapper=new BeanPropertyRowMapper<>(SPECMeasure.class);
			ListSpec = jdbcTemplate.query(sql,mapper,params.toArray());
		}catch(Exception e){
			logger.error("查詢量測table失敗，原因："+e);
			e.printStackTrace();
		}
		return ListSpec;
	}

	public List<MeasureDataResultInfo> findMeasured(SaveMeasure saveMeasure) {
		// TODO Auto-generated method stub
		//SELECT * FROM measure_data WHERE 1=1 " . $qa . " " . $qb . " " . $qc . " " . $qd . " " . $qe . " " . $qf . " 
		//" . $qg . " " . $qh . " " . $qi . " " . $qk . " AND Inspection_Method like '2.5D' order by Workshop
		String sql = "SELECT DATETIME ,WORKSHOP ,INSPECTION_ITEM ,INSPECTION_CONTENT ,NOMINAL_DIM ,UPPER_DIM" +
                " ,LOWER_DIM ,STATUS ,PERIOD ,FREQUENCY , INSPECTION_METHOD ,SPC_NUM " +
                ",MEASURE_VALUE1 ,MEASURE_VALUE2 ,MEASURE_VALUE3 ,MEASURE_VALUE4 ,MEASURE_VALUE5" +
                " ,MEASURE_VALUE6 , MEASURE_VALUE7 ,MEASURE_VALUE8 ,MEASURE_VALUE9 ,MEASURE_VALUE10 " +
                ",MEASURE_VALUE11 ,MEASURE_VALUE12 ,MEASURE_VALUE13 ,MEASURE_VALUE14 , MEASURE_VALUE15 " +
                ",MEASURE_VALUE16 ,MEASURE_VALUE17 ,MEASURE_VALUE18 ,MEASURE_VALUE19 ,MEASURE_VALUE20 " +
                ",MEASURE_VALUE21 ,MEASURE_VALUE22 , MEASURE_VALUE23 ,MEASURE_VALUE24 ,MEASURE_VALUE25 " +
                ",MEASURE_VALUE26 ,MEASURE_VALUE27 ,MEASURE_VALUE28 ,MEASURE_VALUE29 ,MEASURE_VALUE30 " +
                ", MEASURE_VALUE31 ,MEASURE_VALUE32 ,MEASURE_RESULT ,PERSONNEL_ID  " +
                "FROM SPC.MEASURE_DATA md WHERE 1=1 ";
		List params = new ArrayList<>();
		List<MeasureDataResultInfo> list_dcResult = new ArrayList<MeasureDataResultInfo>();
		try{
			if(saveMeasure.getProjectName()==""||saveMeasure.getProjectName().equals("")){
				sql+=" AND Project_Name is null ";
			}else{
				sql+=" AND Project_Name = ? ";
				params.add(saveMeasure.getProjectName());
			}
			if(saveMeasure.getTicketNumber().equals("")||saveMeasure.getTicketNumber()==""){
				sql += " and Ticket_Number is null ";
			}else{
				sql += " and Ticket_Number = ? ";
				params.add(saveMeasure.getTicketNumber());
			}
			if(saveMeasure.getMachineNumber().equals("")||saveMeasure.getMachineNumber()==""){
				sql += " and machine_number is null ";
			}else{
				sql += " and machine_number = ? ";
				params.add(saveMeasure.getMachineNumber());
			}
			sql += " AND Part_Number_V=? AND Status=? AND Frequency=? and period=? and Factory=? "
					+ " and line_number=? and date1 = to_char(sysdate,'yyyy-mm-dd')  AND Inspection_Method like '2.5D' order by Workshop";
			params.add(saveMeasure.getPartVersion());
			params.add(saveMeasure.getStatus());
			params.add(saveMeasure.getFrequency());
			params.add(saveMeasure.getPeriod());
			params.add(saveMeasure.getFactory());
			params.add(saveMeasure.getLink());
			RowMapper<MeasureDataResultInfo> mapper = new BeanPropertyRowMapper<>(MeasureDataResultInfo.class);
            list_dcResult = jdbcTemplate.query(sql, mapper,params.toArray());
		}catch(Exception e){
			logger.error("查詢量測table失敗，原因："+e);
			e.printStackTrace();
		}
		return list_dcResult;
	}

	public int sumbitMeasureTable(Measure[] faultList, SaveMeasure saveMeasure, List<SPECMeasure> sPECMeasure) {
		// TODO Auto-generated method stub
		String sql = "INSERT INTO spc.measure_data(Project_Name,Part_Number_V,Line_Number,Ticket_Number,DateTime,Date1,Time1,"
				+ "Workshop,Inspection_Item,Inspection_Content,Inspection_Method,Nominal_Dim,Upper_Dim,Lower_Dim,Frequency,Status,Period,"
				+ "Measure_Value1,Measure_Value2,Measure_Value3,Measure_Value4,Measure_Value5,"
				+ "Measure_Value6,Measure_Value7,Measure_Value8,Measure_Value9,Measure_Value10,Measure_Value11,Measure_Value12,"
				+ "Measure_Value13,Measure_Value14,Measure_Value15,Measure_Value16,Measure_Value17,Measure_Value18,Measure_Value19,"
				+ "Measure_Value20,Measure_Value21,Measure_Value22,Measure_Value23,Measure_Value24,Measure_Value25,Measure_Value26,"
				+ "Measure_Value27,Measure_Value28,Measure_Value29,Measure_Value30,Measure_Value31,Measure_Value32,"
				+ "Measure_Result,Factory,Measurement_Number,Machine_Number,Personnel_ID,Remark1,SPC_Num,Dim_Location) "
				+ "VALUES(?,?,?,?,sysdate,to_char(sysdate,'yyyy-mm-dd'),to_char(sysdate,'hh24:mi:ss'),"
				+ "?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
		int result = 0;
		try{
			jdbcTemplate.batchUpdate(sql, new BatchPreparedStatementSetter() {
				
				@Override
				public void setValues(PreparedStatement ps, int i) throws SQLException {
					// TODO Auto-generated method stub
					ps.setString(1, saveMeasure.getProjectName());
					ps.setString(2,saveMeasure.getPartVersion());
					ps.setString(3,saveMeasure.getLink());
					ps.setString(4,saveMeasure.getTicketNumber());
					ps.setString(5,faultList[i].getWorkShop());
					ps.setString(6,faultList[i].getInspection_Item());
					ps.setString(7,sPECMeasure.get(i).getInspection_Content());
					ps.setString(8,sPECMeasure.get(i).getInspection_Method());
					ps.setString(9,faultList[i].getNominal_Dim());
					ps.setString(10,faultList[i].getUpper_Dim());
					ps.setString(11, faultList[i].getLower_Dim());
					ps.setString(12,saveMeasure.getFrequency());
					ps.setString(13,saveMeasure.getStatus());
					ps.setString(14,saveMeasure.getPeriod());
					if(faultList[i].getDim1().size()==5){
						for(int j = 0;j<5;j++){
							ps.setString(j+15,faultList[i].getDim1().get(j));
						}
						for(int j = 5;j<32;j++){
							ps.setString(j+15,"");
						}
					}else{
						for(int j = 0;j<32;j++){
							ps.setString(j+15,faultList[i].getDim1().get(j));
						}
					}
					ps.setString(47,faultList[i].getResult());
					ps.setString(48,saveMeasure.getFactory());
					ps.setString(49,saveMeasure.getMeasurementNumber());
					ps.setString(50,saveMeasure.getMachineNumber());
					ps.setString(51,saveMeasure.getPersonnel_ID());
					ps.setString(52,sPECMeasure.get(i).getRemark1());
					ps.setString(53,sPECMeasure.get(i).getSPC_NUM());
					ps.setString(54,sPECMeasure.get(i).getDIM_Location());
				}
				
				@Override
				public int getBatchSize() {
					// TODO Auto-generated method stub
					return faultList.length;
				}
			});
		}catch(Exception e){
			logger.error("提交量測數據失敗，原因："+e);
			e.printStackTrace();
			result=1;
		}
		return result;
	}

	public List<Map<String, Object>> getMeasureData(String remoteAddr) {
		String sql = "SELECT Measure_data FROM spc.temp_data WHERE IP=?";
		String updateSql = "UPDATE spc.temp_data SET Measure_data = 'NA' WHERE IP = ?";
		List<Map<String,Object>> data = new ArrayList<Map<String,Object>>();
		try{
			data = jdbcTemplate.queryForList(sql, remoteAddr);
			jdbcTemplate.update(updateSql, remoteAddr);
		}catch(Exception e){
			logger.error("獲取歷史量測數據失敗，原因："+e);
			e.printStackTrace();
		}
		return data;
	}
	
	public int deleteMeasureTable(Measure[] faultList,SaveMeasure saveMeasure){
		String sql = "delete from SPC.MEASURE_DATA t where Part_Number_V=? AND Status=? AND Frequency=? and period=? "
					+ " and line_number=? and workshop=?  and date1 = to_char(sysdate,'yyyy-mm-dd') ";
		int result = 0;
		try{
			if(saveMeasure.getTicketNumber().equals("")||saveMeasure.getTicketNumber()==""){
				sql += " and Ticket_Number is null ";
			}else{
				sql += " and Ticket_Number = ? ";
			}
			if(saveMeasure.getMachineNumber().equals("")||saveMeasure.getMachineNumber()==""){
				sql += " and machine_number is null ";
			}else{
				sql += " and machine_number = ? ";
			}
			jdbcTemplate.batchUpdate(sql, new BatchPreparedStatementSetter() {
				
				@Override
				public void setValues(PreparedStatement ps, int i) throws SQLException {
					// TODO Auto-generated method stub
					ps.setString(1,saveMeasure.getPartVersion());
					ps.setString(2,saveMeasure.getStatus());
					ps.setString(3,saveMeasure.getFrequency());
					ps.setString(4,saveMeasure.getPeriod());
					ps.setString(5,saveMeasure.getLink());
					ps.setString(6,faultList[i].getWorkShop());
					if(saveMeasure.getTicketNumber().equals("")||saveMeasure.getTicketNumber()==""){
						if(!(saveMeasure.getMachineNumber().equals("")||saveMeasure.getMachineNumber()=="")){
							ps.setString(7,saveMeasure.getMachineNumber());
						}
					}else{
						ps.setString(7,saveMeasure.getTicketNumber());
						if(!(saveMeasure.getMachineNumber().equals("")||saveMeasure.getMachineNumber()=="")){
							ps.setString(8,saveMeasure.getMachineNumber());
						}
					}
				}
				
				@Override
				public int getBatchSize() {
					// TODO Auto-generated method stub
					return faultList.length;
				}
			});
		}catch(Exception e){
			logger.error("刪除歷史量測數據失敗，原因："+e);
			e.printStackTrace();
			result=1;
		}
		return result;
	}
}
