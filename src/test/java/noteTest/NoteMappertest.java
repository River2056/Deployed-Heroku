package noteTest;

import org.junit.Test;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import tw.com.river.bean.Note;
import tw.com.river.mapper.NoteMapper;

public class NoteMappertest {

	@Test
	public void testMapperInsert() {

		AbstractApplicationContext ctx = new ClassPathXmlApplicationContext("spring-dao.xml");
		NoteMapper noteMapper = ctx.getBean("noteMapper", NoteMapper.class);
		
		Note note = new Note();
		note.setUid(1);
		note.setComment("學習發送電文");
		note.setStart("08:37:55");
		note.setEnd("18:46:57");
		
		Integer affectedRows = noteMapper.insert(note);
		System.out.println("Insert complete! affected rows: " + affectedRows);
		
		ctx.close();
		
	}
	
	@Test
	public void testMapperFind() {

		AbstractApplicationContext ctx = new ClassPathXmlApplicationContext("spring-dao.xml");
		NoteMapper noteMapper = ctx.getBean("noteMapper", NoteMapper.class);
		
		Integer uid = 1;
		Integer id = 2;
		Note note = noteMapper.findNoteByUidAndId(uid, id);
		System.out.println("Note found: " + note);
		
		ctx.close();
		
	}
}
