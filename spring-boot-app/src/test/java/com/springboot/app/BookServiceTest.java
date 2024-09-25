package com.springboot.app;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import com.springboot.app.entity.Book;
import com.springboot.app.entity.Category;
import com.springboot.app.service.BookService;
import org.junit.Rule;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest(classes=SpringBootAppApplication.class)
public class BookServiceTest {
	@Rule
	public MockitoRule rule = MockitoJUnit.rule();
	  
	@Mock
    private BookService bookService;
	
	@Mock
	private Category cat;
	
	@Mock
	private Book bks;
    
    @Test
    public void testsaveBook(){
    	
    	@SuppressWarnings("deprecation")
		java.sql.Date sqlDate = new java.sql.Date(2020,12,12);
	    
    	float f = 1;
    	long l= 1l;
    	cat = new Category();
    	cat.setCatName("abc");
    	cat.setCreated_dt(sqlDate);
    	cat.setModify_dt(sqlDate);
    	
    	bks = new Book();
        bks.setBookAuthor("abc");
        bks.setBookName("sss");
        bks.setBookPublishar("100");
        bks.setBookPrice(f);
        bks.setCreated_dt(sqlDate);
        bks.setModify_dt(sqlDate);
        bks.setId(l);
        bks.setCategory(cat);
    	
    	Mockito.when(this.bookService.saveBook(bks)).thenReturn(bks);
        Book savedBook = this.bookService.saveBook(bks);
       
        assertNotNull(savedBook);
        assertEquals("sss", savedBook.getBookName());
    }
	
}
