package tw.com.river.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import tw.com.river.bean.Note;

public interface NoteMapper {

	/**
	 * dao insert method
	 * @param note Note object
	 * @return affected rows
	 */
	Integer insert(Note note);
	
	/**
	 * dao delete a note
	 * @param uid user id
	 * @param id note id
	 * @return affected rows
	 */
	Integer delete(
			@Param("uid") Integer uid, 
			@Param("id") Integer id);
	
	/**
	 * dao update a note
	 * @param note object
	 * @return affected rows
	 */
	Integer update(Note note);
	
	/**
	 * dao find specific note object
	 * @param id note id
	 * @return note object
	 */
	Note findNoteByUidAndId(
			@Param("uid") Integer uid, 
			@Param("id") Integer id);
	
	/**
	 * dao find all notes and put into a list
	 * @param uid user id
	 * @return list of notes
	 */
	List<Note> findAllNotes(Integer uid);
	
	
}
