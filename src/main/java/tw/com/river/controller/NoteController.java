package tw.com.river.controller;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import tw.com.river.bean.Note;
import tw.com.river.bean.ResponseResult;
import tw.com.river.service.INoteService;
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
	public String showListPage(ModelMap modelMap, HttpSession session) {
		Integer uid = getUidFromSession(session);
		List<Note> noteList = noteService.findAllNotes(uid);
		modelMap.addAttribute("noteList", noteList);
		
		return "list";
	}
	
	@RequestMapping("/edit.do")
	public String showEditPage(Integer id, ModelMap modelMap, HttpSession session) {
		Integer uid = getUidFromSession(session);
		Note note = noteService.findNoteByUidAndId(uid, id);
		modelMap.addAttribute("note", note);
		
		return "edit";
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
			
		} catch (Exception e) {
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
		System.out.println(note);
		try {
			noteService.edit(note);
			rr = new ResponseResult<Void>(1, "修改成功!");
			
		} catch (NoNoteEntryException e) {
			rr = new ResponseResult<Void>(0, e);
			
		}
		
		return rr;
	}
}
