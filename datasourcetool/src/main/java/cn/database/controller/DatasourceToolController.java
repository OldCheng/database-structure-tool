package cn.database.controller;

import java.util.List;
import java.util.Map;

import cn.database.dto.TableColumnDto;
import cn.database.entity.TableColumn;
import cn.database.service.ExcelService;
import cn.database.service.TableColumnService;
import cn.database.utils.MapUtil;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("exportExcel")
@Api("数据库导出Excel")
@CrossOrigin
public class DatasourceToolController {

	private Logger logger = Logger.getLogger(this.getClass());

	@Autowired
	private TableColumnService tableColumnService;

	@Autowired
	private ExcelService excelService;

	@PostMapping("printAll")
	@ApiOperation(value = "所有表结构打印测试")
	public ResponseEntity<Object> printAll(@RequestBody TableColumnDto tableColumnDto) {
		try {
			List<TableColumn> tableColumns = tableColumnService.getAllTableColumn(tableColumnDto.getDataSourceName());
			Map<String, List<TableColumn>> map = MapUtil.dealTableColumn(tableColumns);
			excelService.createTableColumnExcel(map, tableColumnDto);
		} catch (Exception e) {
			logger.info(e.getLocalizedMessage());
			logger.info("-------------",e);
			return new ResponseEntity<>(e.getLocalizedMessage(), HttpStatus.ACCEPTED);
		}
		return new ResponseEntity<>(HttpStatus.OK);
	}

	@PostMapping("printSingle")
	@ApiOperation(value = "单表结构打印测试")
	public ResponseEntity<Object> printSingle(@RequestBody TableColumnDto tableColumnDto) {
		try {
			List<TableColumn> tableColumns = tableColumnService.getTableColumns(tableColumnDto.getDataSourceName(),
					tableColumnDto.getTableName());
			excelService.createSingleTableColumnExcel(tableColumns, tableColumnDto);
		} catch (Exception e) {
			return new ResponseEntity<>(e.getLocalizedMessage(), HttpStatus.ACCEPTED);
		}

		return new ResponseEntity<>(HttpStatus.OK);
	}
}
