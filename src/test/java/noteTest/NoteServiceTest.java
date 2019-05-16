package noteTest;

import java.util.List;

import org.junit.Test;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import tw.com.river.bean.Note;
import tw.com.river.service.INoteService;

public class NoteServiceTest {

	@Test
	public void testServiceAdd() {

		AbstractApplicationContext ctx = new ClassPathXmlApplicationContext("spring-dao.xml", "spring-service.xml");
		INoteService noteService = ctx.getBean("noteService", INoteService.class);
		
		Note note = new Note();
		note.setUid(1);
//		note.setComment("學習寫業務邏輯");
//		note.setStart("08:38:55");
//		note.setEnd("18:50:57");
		
		Integer noteId = noteService.add(note);
		System.out.println("Add complete, new note id: " + noteId);
		
		ctx.close();
		
	}
	
	@Test
	public void testServiceFindAllNotes() {

		AbstractApplicationContext ctx = new ClassPathXmlApplicationContext("spring-dao.xml", "spring-service.xml");
		INoteService noteService = ctx.getBean("noteService", INoteService.class);
		
		Integer uid = 1;
		List<Note> noteList = noteService.findAllNotes(uid);
		for(Note note : noteList) {
			System.out.println(note);
		}
		
		ctx.close();
		
	}
	
	@Test
	public void testServiceDelete() {

		AbstractApplicationContext ctx = new ClassPathXmlApplicationContext("spring-dao.xml", "spring-service.xml");
		INoteService noteService = ctx.getBean("noteService", INoteService.class);
		
		Integer uid = 1;
		Integer id = 4;
		Integer affectedRows = noteService.delete(uid, id);
		System.out.println("Delete complete, affected rows: " + affectedRows);
		
		ctx.close();
		
	}
}
