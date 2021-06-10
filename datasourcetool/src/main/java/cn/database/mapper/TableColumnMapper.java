package cn.database.mapper;

import java.util.List;

import cn.database.entity.TableColumn;
import org.apache.ibatis.annotations.Param;

import org.springframework.stereotype.Repository;
import tk.mybatis.mapper.common.Mapper;

@Repository
public interface TableColumnMapper extends Mapper<TableColumn> {

	
	/**
	* 查询指定数据库和表的表结构
	* @date 2018年7月25日
	* @param dataSourceName
	* @param tableName
	* @return
	*/
		
	List<TableColumn> getTableColumn(@Param("dataSourceName") String dataSourceName,
			@Param("tableName") String tableName);

	
	/**
	* 查询数据所有表的表结构
	* @date 2018年7月25日
	* @param dataSourceName
	* @return
	*/
		
	List<TableColumn> getAllTableColumn(@Param("dataSourceName") String dataSourceName);
}