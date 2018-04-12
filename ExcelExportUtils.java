package com.zkdj.pomp.utils;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.IOException;
import java.io.OutputStream;
import java.util.Map;

/**
 * Excel 导出工具类
 * POI 版本：3.14
 * @author Wangjianchun
 * @date 2018年4月12日
 */
public class ExcelExportUtils {

	/**
	 * 创建 xls 格式的 excel 文件对应的 Workbook 对象
	 * @return HSSFWorkbook 对象
	 */
	public static Workbook createWorkbookForExcel95(){
		return new HSSFWorkbook();
	}

	/**
	 * 创建 xlsx 格式的 excel 文件对应的 Workbook 对象
	 * @return XSSFWorkbook 对象
	 */
	public static Workbook createWorkbookForExcel2003(){
		return new XSSFWorkbook();
	}

	public static Sheet createSheet(Workbook wb, String sheetName){
		return wb.createSheet(sheetName);
	}

	/**
	 * 创建表头
	 * 说明：默认在第一行创建表头
	 * @param sheet 表格对象
	 * @param style 表头的样式
	 * @param columnNameMap 列名Map，key：列索引，value：列名称
	 */
	public static void createHeader(Sheet sheet, CellStyle style, Map<Integer, String> columnNameMap){
		createHeader(0, sheet, style, columnNameMap);
	}

	/**
	 * 创建表头
	 * 说明：在 rowIndex 所在的行创建表头
	 * @param rowIndex 行索引
	 * @param sheet 表格对象
	 * @param style 表头的样式
	 * @param columnNameMap 列名Map，key：列索引，value：列名称
	 */
	public static void createHeader(int rowIndex, Sheet sheet, CellStyle style, Map<Integer, String> columnNameMap){
		// 第三步，在sheet中添加表头第0行,注意老版本poi对Excel的行数列数有限制short
		Row row = sheet.createRow(rowIndex);
		for(Map.Entry<Integer, String> entry : columnNameMap.entrySet()){
			createCell(row, entry.getKey(), entry.getValue(), style);
		}
	}

	/**
	 * 设置表格各列的宽度
	 * @param sheet
	 * @param columnWidthMap 列宽Map，key：列索引，value：列宽
	 */
	public static void setColumnWidth(Sheet sheet, Map<Integer, Integer> columnWidthMap){
		for(Map.Entry<Integer, Integer> entry : columnWidthMap.entrySet()){
			setColumnWidth(sheet, entry.getKey(), entry.getValue());
		}
	}

	public static void createCell(Row row, int columnIndex, String cellValue, CellStyle cellStyle){
		Cell cell = row.createCell(columnIndex);
		cell.setCellValue(cellValue);
		cell.setCellStyle(cellStyle);
	}

	public static void createCell(Row row, int columnIndex, double cellValue, CellStyle cellStyle){
		Cell cell = row.createCell(columnIndex);
		cell.setCellValue(cellValue);
		cell.setCellStyle(cellStyle);
	}

	/**
	 * 设置列宽
	 * @param sheet
	 * @param columnIndex
	 * @param width POI的列宽的单位是 1/256，这里只需要传入实际的列宽就可以了，在设置列宽时会自动乘以 256
	 */
	public static void setColumnWidth(Sheet sheet, int columnIndex, int width){
		sheet.setColumnWidth(columnIndex, width * 256);
	}

	/**
	 * 创建单元格样式对象
	 * 说明：默认的边框样式：CellStyle.BORDER_THIN
	 * @param wb
	 * @param alignment 对齐方式
	 * @return
	 */
	public static CellStyle createCellStyle(Workbook wb, short alignment){
		CellStyle style = wb.createCellStyle();
		style.setAlignment(alignment);
		style.setBorderBottom(CellStyle.BORDER_THIN);
		style.setBorderLeft(CellStyle.BORDER_THIN);
		style.setBorderRight(CellStyle.BORDER_THIN);
		style.setBorderTop(CellStyle.BORDER_THIN);

		return style;
	}

	public static Font createFont(Workbook wb, String fontName, short fontHeight){
		Font font = wb.createFont();
		font.setFontName(fontName);
		font.setFontHeightInPoints(fontHeight);

		return font;
	}

	public static void write(OutputStream os, Workbook wb) {
		try {
			//将文件写入到输出流
			wb.write(os);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				wb.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

}  