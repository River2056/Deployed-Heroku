package tw.com.river.service;

import java.util.List;

import tw.com.river.bean.Note;
import tw.com.river.service.exception.NoNoteEntryException;

public interface INoteService {

	/**
	 * service add a note method
	 * @param note object
	 * @return new note's id
	 * @throws NoNoteEntryException
	 */
	Integer add(Note note);
	
	/**
	 * service edit a note method
	 * @param note object
	 * @return affected rows
	 */
	Integer edit(Note note);
	
	/**
	 * dao insert method
	 * @param note Note object
	 * @return affected rows
	 */
	Integer insert(Note note);
	
	/**
	 * service delete a note
	 * @param uid user id
	 * @param id note id
	 * @return affected rows, if not equal to 1, means there's a problem
	 * @throws DataNotFoundException
	 */
	Integer delete(Integer uid, Integer id);
	
	/**
	 * dao update a note
	 * @param note object
	 * @return affected rows
	 */
	Integer update(Note note);
	
	/**
	 * check for comments(AJAX purpose)
	 * @param comment
	 * @return
	 */
	boolean checkForComment(String comment);
	
	/**
	 * dao find specific note object
	 * @param id note id
	 * @return note object
	 */
	Note findNoteByUidAndId(Integer uid, Integer id);
	
	/**
	 * dao find all notes and put into a list
	 * @param uid user id
	 * @return list of notes
	 */
	List<Note> findAllNotes(Integer uid);
}
