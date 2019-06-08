package tw.com.river.controller;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import tw.com.river.bean.Note;
import tw.com.river.bean.ResponseResult;
import tw.com.river.service.INoteService;
import tw.com.river.service.exception.DataNotFoundException;
import tw.com.river.service.exception.NoNoteEntryException;

@Controller
@RequestMapping("/note")
public class NoteController extends BaseController {

	@Resource(name="noteService")
	private INoteService noteService;
	
	@RequestMapping("/add.do")
	public String showAddPage() {
		return "add";
	}
	
	@RequestMapping("/list.do")
	public String showListPage() {
		return "list";
	}
	
	@RequestMapping("/check_comment.do")
	@ResponseBody
	public ResponseResult<Void> checkForComment(String comment) {
		boolean status = noteService.checkForComment(comment);
		ResponseResult<Void> rr = status ? new ResponseResult<Void>(1) : new ResponseResult<Void>(0);
		return rr;
	}
	
	@RequestMapping("/get_note.do")
	@ResponseBody
	public ResponseResult<Note> handleShowNote(Integer id, HttpSession session) {
		ResponseResult<Note> rr;
		Integer uid = getUidFromSession(session);
		Note note = noteService.findNoteByUidAndId(uid, id);
		rr = new ResponseResult<Note>(1, note);
		
		return rr;
	}
	
	@RequestMapping("/get_list.do")
	@ResponseBody
	public ResponseResult<List<Note>> handleShowList(HttpSession session) {
		ResponseResult<List<Note>> rr;
		Integer uid = getUidFromSession(session);
		List<Note> noteList = noteService.findAllNotes(uid);
		rr = new ResponseResult<List<Note>>(1, noteList);
		return rr;
	}
	
	@RequestMapping(method=RequestMethod.POST, value="/handle_add.do")
	@ResponseBody
	public ResponseResult<Void> handleAdd(Note note, HttpSession session) {
		ResponseResult<Void> rr;
		Integer uid = getUidFromSession(session);
		note.setUid(uid);
		try {
			noteService.add(note);
			rr = new ResponseResult<Void>(1, "增加記錄成功!");
		} catch (NoNoteEntryException e) {
			rr = new ResponseResult<Void>(0, e);
		}
		
		return rr;
	}
	
	@RequestMapping("/handle_delete.do")
	@ResponseBody
	public ResponseResult<Void> handleDelete(Integer id, HttpSession session) {
		ResponseResult<Void> rr;
		Integer uid = getUidFromSession(session);
		try {
			noteService.delete(uid, id);
			rr = new ResponseResult<Void>(1, "刪除成功!");
			
		} catch (DataNotFoundException e) {
			rr = new ResponseResult<Void>(0, e);
			
		}
		
		return rr;
	}
	
	@RequestMapping("/handle_delete_all.do")
	@ResponseBody
	public ResponseResult<Void> handleDeleteAllRecords(HttpSession session) {
		ResponseResult<Void> rr;
		Integer uid = getUidFromSession(session);
		try {
			noteService.deleteAllRecord(uid);
			rr = new ResponseResult<Void>(1, "刪除成功!");
			
		} catch (DataNotFoundException e) {
			rr = new ResponseResult<Void>(0, e);
		}
		
		return rr;
	}
	
	@RequestMapping(method=RequestMethod.POST, value="/handle_edit.do")
	@ResponseBody
	public ResponseResult<Void> handleEdit(Note note, HttpSession session) {
		ResponseResult<Void> rr;
		Integer uid = getUidFromSession(session);
		note.setUid(uid);
		try {
			noteService.edit(note);
			rr = new ResponseResult<Void>(1, "修改成功!");
			
		} catch (NoNoteEntryException e) {
			rr = new ResponseResult<Void>(0, e);
			
		}
		
		return rr;
	}
	
	@RequestMapping(value="/export.do", produces="application/vnd.openxmlformats-officedocument.spreadsheetml.sheet")
	@ResponseBody
	public byte[] exportExcel(HttpServletResponse response, HttpSession session) {
		Integer uid = getUidFromSession(session);
		List<Note> allNotes = noteService.findAllNotes(uid);
		String fileName = "TimeSheet" + allNotes.get(0).getDay() + ".xlsx";
		response.setHeader("Content-Disposition", "attachment; filename=\"" + fileName + "\"");
		byte[] bytes = null;
		try {
			bytes = createExcel(allNotes);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return bytes;
	}

	private byte[] createExcel(List<Note> list) throws IOException {
		XSSFWorkbook workbook = new XSSFWorkbook();
		XSSFSheet sheet = workbook.createSheet("TimeSheet");
		XSSFCellStyle style = workbook.createCellStyle();
		XSSFRow headerRow = sheet.createRow(0);
		headerRow.createCell(0).setCellValue("出勤表");
		XSSFRow titleRow = sheet.createRow(1);
		String[] forTitleRow = {"Date", "Comments", "Start", "End"};
		for(int i = 0 ; i < 4 ; i++) {
			XSSFCell cell = titleRow.createCell(i);
			cell.setCellValue(forTitleRow[i]);
		}
		
		// actual data insertion
		for(int i = 0 ; i < list.size() ; i++) {
			XSSFRow row = sheet.createRow(i + 2);
			Note note = list.get(i);
			String[] noteValues = {note.getDay(), note.getComment(), note.getStart(), note.getEnd()};
			for(int j = 0 ; j < 4 ; j++) {
				XSSFCell cell = row.createCell(j);
				cell.setCellValue(noteValues[j]);
			}
		}
		sheet.setColumnWidth(0, 5000);
		sheet.setColumnWidth(1, 20000);
		sheet.setColumnWidth(2, 4500);
		sheet.setColumnWidth(3, 4500);
		
		ByteArrayOutputStream out = new ByteArrayOutputStream();
		workbook.write(out);
		workbook.close();
		out.close();
		
		byte[] bytes = out.toByteArray();
		
		return bytes;
	}
	
//	private void setCellValueAndStyle(String value, XSSFCell cell, XSSFCellStyle style) {
//		cell.setCellStyle(style);
//		cell.setCellValue(value);
//	}
}
