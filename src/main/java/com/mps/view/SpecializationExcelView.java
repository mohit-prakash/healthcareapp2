package com.mps.view;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.web.servlet.view.document.AbstractXlsxView;

import com.mps.entity.Specialization;

public class SpecializationExcelView extends AbstractXlsxView {

	@Override
	protected void buildExcelDocument
						(Map<String, Object> model, 
						Workbook workbook, 
						HttpServletRequest request,
						HttpServletResponse response) throws Exception {
		
		// 1. Define your own excel file name
		response.addHeader("Content-Disposition", "attachement;filename=SpecsList.xlsx");
		
		//2. read data given by controller
		@SuppressWarnings("unchecked")
		List<Specialization> list= (List<Specialization>) model.get("list");
		//3. create one sheet
		Sheet sheet = workbook.createSheet("Specialization");
		
		//Set row#0 as Header
		setHead(sheet);
		
		//Set data from row#1 onwards
		setBody(sheet,list);
		
	}
	private void setHead(Sheet sheet) {
		Row row = sheet.createRow(0);
		row.createCell(0).setCellValue("ID");
		row.createCell(1).setCellValue("CODE");
		row.createCell(2).setCellValue("NAME");
		row.createCell(3).setCellValue("NOTE");
	}
	
	private void setBody(Sheet sheet,List<Specialization> list) {
		int rowNum=1;
		for(Specialization s: list)
		{
			Row row = sheet.createRow(rowNum++);
			row.createCell(0).setCellValue(s.getId());
			row.createCell(1).setCellValue(s.getSpecCode());
			row.createCell(2).setCellValue(s.getSpecName());
			row.createCell(3).setCellValue(s.getSpecNote());
		}
	}

}
