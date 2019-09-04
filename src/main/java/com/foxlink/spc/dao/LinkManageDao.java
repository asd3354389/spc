package com.foxlink.spc.dao;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.foxlink.spc.pojo.LineNumber;


@Repository("linkManageDao")
public class LinkManageDao {
	private static Logger logger=Logger.getLogger(LinkManageDao.class);
	private JdbcTemplate jdbcTemplate;
	@Autowired
	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	public List<LineNumber> ShowAllLink() {
		// TODO Auto-generated method stub
		String sql = "SELECT FACTORY,FLOOR,LINE_NUMBER,DEPT_CODE,WECHAT_GROUP,PRODUCTION_DEPT FROM SPC.Line_Number WHERE 1=1 order by Factory,Floor,Line_Number";
		
		List<LineNumber> LineNumberList = new ArrayList<>();
		
		try{
	        RowMapper<LineNumber> mapper = new BeanPropertyRowMapper<>(LineNumber.class);

			LineNumberList = jdbcTemplate.query(sql, mapper);
			
		}catch(Exception e){
			e.printStackTrace();
			logger.error("LinkManageDao查找所有線別時發生錯誤："+e);
		}
		
		return LineNumberList;
	}

	public int checkExist(String factory, String line_Number) {
		// TODO Auto-generated method stub
		String sql = "SELECT COUNT(*) FROM SPC.Line_Number WHERE FACTORY = ? AND LINE_NUMBER = ?";
		
		int result = -1;
		
        try{
        	Object a[] = new Object[2];
            a[0] = factory;
            a[1] = line_Number;
        	result = jdbcTemplate.queryForObject(sql, Integer.class, a);
        	System.out.println(result);
        }catch(Exception e){
        	logger.error("LinkManageDao查詢線別是否存在時發生錯誤："+e);
        	e.printStackTrace();
        }
        
		return result;
	}

	public int updateLink(String factory, String floor, String line_Number, String dept_Code, String production_Dept,
			String wechat_Group) {
		// TODO Auto-generated method stub
		String sql = "UPDATE SPC.Line_Number SET FLOOR = ?,DEPT_CODE=?,PRODUCTION_DEPT=?,WECHAT_GROUP=? WHERE FACTORY=? AND LINE_NUMBER=? ";
		
		int result = -1;
		
		try{
			Object a[] = new Object[6];
	        a[0] = floor;
	        a[1] = dept_Code;
	        a[2] = production_Dept;
	        a[3] = wechat_Group;
	        a[4] = factory;
	        a[5] = line_Number;
	        result = jdbcTemplate.update(sql, a);
		}catch(Exception e){
        	logger.error("LinkManageDao更新線別時發生錯誤："+e);
        	e.printStackTrace();
        }
		
        
        return result;
		
	}

	public int addLink(String factory, String floor, String line_Number, String dept_Code, String production_Dept,
			String wechat_Group) {
		// TODO Auto-generated method stub
		String sql = "INSERT INTO SPC.Line_Number(FACTORY,FLOOR,LINE_NUMBER,DEPT_CODE,WECHAT_GROUP,PRODUCTION_DEPT) "
				+ " VALUES(?,?,?,?,?,?)";
		int result = -1;
		try{
			Object a[] = new Object[6];
			a[0] = factory;
	        a[1] = floor;
	        a[2] = line_Number;
	        a[3] = dept_Code;
	        a[4] = wechat_Group;
	        a[5] = production_Dept;
	        result = jdbcTemplate.update(sql, a);
		}catch(Exception e){
        	logger.error("LinkManageDao添加線別時發生錯誤："+e);
        	e.printStackTrace();
        }
		
		return result;
	}

	public int DeleteLink(String factory, String line_Number) {
		// TODO Auto-generated method stub
		String sql = "DELETE FROM SPC.Line_Number WHERE FACTORY = ? AND LINE_NUMBER = ?";
		int result = -1;
		
		try{
			Object a[] = new Object[2];
			a[0] = factory;
	        a[1] = line_Number;
	        result = jdbcTemplate.update(sql, a);
		}catch(Exception e){
        	logger.error("LinkManageDao刪除線別時發生錯誤："+e);
        	e.printStackTrace();
        }
		
		return result;
	}

}
