package com.greedy.section01.javaconfig;

import java.util.Date;

import org.apache.ibatis.annotations.Select;

public interface Mapper {
	
	@Select("SELECT SYSDATE FROM DUAL")
	
	java.util.Date selectSysdate(); 
	

}
