package cn.database.service;

import java.util.List;

import cn.database.entity.TableColumn;
import cn.database.mapper.TableColumnMapper;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

@Service
@Transactional
public class TableColumnService {

	private Logger logger = Logger.getLogger(this.getClass());

	@Autowired
	private TableColumnMapper tableColumnMapper;

	
	/**
	* 查询指定的数据库表的表结构
	* @date 2018年7月25日
	* @return
	*/
		
	public List<TableColumn> getTableColumns(String dataSourceName, String tableName) {
		if(StringUtils.isEmpty(dataSourceName)||StringUtils.isEmpty(tableName)) {
			throw new RuntimeException("请输入数据库名和表名");
		}
		//查询数据库和表是否存在
		//待做
		List<TableColumn> tableColumn = tableColumnMapper.getTableColumn(dataSourceName,tableName);
		return tableColumn;
	}
	
	/**
	* 查询数据库所有表的表结构
	* @date 2018年7月25日
	* @param dataSourceName
	* @param
	* @return
	*/
		
	public List<TableColumn> getAllTableColumn(String dataSourceName) {
		if(StringUtils.isEmpty(dataSourceName)) {
			throw new RuntimeException("请输入数据库名");
		}
		//查询数据库和表是否存在
		//待做
		List<TableColumn> tableColumn;
		try {
			tableColumn = tableColumnMapper.getAllTableColumn(dataSourceName);
			return tableColumn;
		}catch (Exception e){
			logger.error(e);
		}
		return null;
	}
}
