package tw.com.river.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import tw.com.river.bean.Note;
import tw.com.river.mapper.NoteMapper;
import tw.com.river.service.exception.DataNotFoundException;
import tw.com.river.service.exception.NoNoteEntryException;

@Service("noteService")
public class NoteServiceImpl implements INoteService {

	@Resource(name="noteMapper")
	private NoteMapper noteMapper;

	public Integer add(Note note) {
		if(note == null || note.getComment() == null || note.getComment().isEmpty()) {
			throw new NoNoteEntryException("無紀錄內容, 請重新檢查!");
		}
		insert(note);
		Integer noteId = note.getId();
		
		return noteId;
	}
	
	public Integer edit(Note note) {
		if(note == null || note.getComment() == null || note.getComment().isEmpty()) {
			throw new NoNoteEntryException("修改內容不得為空!");
		}
		update(note);
		Integer noteId = note.getId();
			
		return noteId;
	}
	
	public Integer insert(Note note) {
		return noteMapper.insert(note);
	}
	
	public Integer delete(Integer uid, Integer id) {
		Integer affectedRows = noteMapper.delete(uid, id);
		if(affectedRows != 1) {
			throw new DataNotFoundException("數據已不存在, 可能已經提前遭刪除");
		}
		
		return affectedRows;
	}
	
	
	
	public Integer update(Note note) {
		return noteMapper.update(note);
	}

	public Note findNoteByUidAndId(Integer uid, Integer id) {
		return noteMapper.findNoteByUidAndId(uid, id);
	}

	public List<Note> findAllNotes(Integer uid) {
		return noteMapper.findAllNotes(uid);
	}

}
