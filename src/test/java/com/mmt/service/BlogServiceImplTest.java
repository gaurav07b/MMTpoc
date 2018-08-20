package com.mmt.service;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.mmt.MakeMyTripApIsApplication;
import com.mmt.dto.TravelBlogsDto;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = MakeMyTripApIsApplication.class)
public class BlogServiceImplTest {
	
	@MockBean
	private BlogServiceImpl blogServiceImpl;	
	
    @Autowired
    private WebApplicationContext wac;
    private MockMvc mvc;
    
    @Before
    public void setUp() {
    	mvc = MockMvcBuilders.webAppContextSetup(wac).build();
    }

	@Test
	public void testAddNewBlogs() {
		String blog = "{\n" +
				"	\"blogNam\":\"Destination Testing3\",\n" +
				"	\"imgURL\":\"https://pagez.com/attachments/2311/article/0/e54b4f826b9950d4b45a8c275f721862.jpg\",\n" +
				"	\"blogURL\":\"http://amysimpson.com/blog/\"\n" +
		"}";
		
		RequestBuilder requestBuilder=MockMvcRequestBuilders.post("/mmt/blog/writeNew").accept(MediaType.APPLICATION_JSON).content(blog).contentType(MediaType.APPLICATION_JSON);
		try {
			MvcResult result = mvc.perform(requestBuilder).andReturn();
			assertEquals(200, result.getResponse().getStatus());
			}catch (Exception e) {
				
			}
	}

	@Test
	public void testReadBlogs() throws Exception {
		TravelBlogsDto tbdto = new TravelBlogsDto();
		tbdto.setId(2);
		tbdto.setBlogName("TravelBlog");
		tbdto.setBlogURL("www.xyz.com");
		tbdto.setImgURL("localImg.jpg");
		
		Mockito.when(blogServiceImpl.readBlogs(tbdto.getId())).thenReturn(tbdto);
		
		RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/mmt/blog/open_blog/"+tbdto.getId()).contentType(MediaType.APPLICATION_JSON);
		try {
			MvcResult result = mvc.perform(requestBuilder).andReturn();
			assertEquals(200, result.getResponse().getStatus());
		}catch (Exception e) {
			// TODO: handle exception
		}
	}

	@Test
	public void testRemoveBlogs() throws Exception {
		throw new RuntimeException("not yet implemented");
	}

	@Test
	public void testAllBlogInfo() throws Exception {
		throw new RuntimeException("not yet implemented");
	}
//
//	@Test
//	public void testAssemblerBlog() throws Exception {
//		throw new RuntimeException("not yet implemented");
//	}

}
