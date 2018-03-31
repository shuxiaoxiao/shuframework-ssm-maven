package com.shuframework.admin.controller;

import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.shuframework.admin.model.SysUser;
import com.shuframework.util.json.JsonUtil;


/**
 * 测试例子，详情链接：http://jinnianshilongnian.iteye.com/blog/2004660
 * @author shu
 *
 */
@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
/*
 * 现在发现的只能单独加载文件，不能采用通配符加载一类文件，避免多次加载的问题
 * 如果加载的文件里面包含了其他文件，则该其他文件可以不用加入，
 * 如：spring/spring.xml包含了spring/spring-mybatis.xml,spring/spring-mybatis.xml文件包含了xml/mybatis-config.xml，
 * 	那么@ContextConfiguration 可以只加载spring/spring.xml
 */
@ContextConfiguration(locations = {"classpath:spring/spring.xml","classpath:spring/spring-mvc.xml"})
////成功，但是可以不用加classpath:spring/spring-mybatis.xml 文件
//@ContextConfiguration(locations = {"classpath:spring/spring.xml","classpath:spring/spring-mvc.xml","classpath:spring/spring-mybatis.xml"})
//java.lang.IllegalStateException: Failed to load ApplicationContext, @ContextConfiguration 无法加载web.xml
//@ContextConfiguration("file:src/main/webapp/WEB-INF/web.xml")或 @ContextConfiguration("classpath:src/main/webapp/WEB-INF/web.xml")
public class SysUserControllerTest {

	//模拟mvc
    private MockMvc mockMvc;
    
    @Autowired
    private SysUserController userController;
    
    @Autowired  
    private WebApplicationContext wac;

    //MockMvcBuilders构建MockMvc对象，本测试类是测试单个controller，不用注入所有项目
    @Before
    public void setUp() throws Exception {
//    	//注入WebApplicationContext，创建所有项目对象（集成Web环境测试，项目拦截器有效）
//    	mockMvc = MockMvcBuilders.webAppContextSetup(wac).build();
    	
    	//采用@Autowired创建controller里面的注解对象都被创建 （单个类测试，拦截器无效）
    	mockMvc = MockMvcBuilders.standaloneSetup(userController).build();
//    	//采用new 创建controller里面的注解对象都是null，如userService
//    	mockMvc = MockMvcBuilders.standaloneSetup(new SysUserController()).build(); 
    }

    
    @Test
    public void list_get() throws Exception {
    	//get请求参数直接跟在URL后面
    	String requestURL = "/sysUser/list?pageIndex=1&pageSize=10";
    	MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.get(requestURL)
        		.contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print())
                .andReturn();
    	int status = mvcResult.getResponse().getStatus();  
    	String content = mvcResult.getResponse().getContentAsString();
    	System.out.println("Response-status：" + status);
    	System.out.println("Response-content：" + content);
    }
    
    
    /*
     * 如果跟着json参数需要对URL进行加密，同时请求里面需要对URL进行解密，但实际请求时不需要，
     * 所以不推荐采用这个方法
     */
    @Test
    public void list7_get() throws Exception {
    	//get请求参数直接跟在URL后面
    	Map<String, Object> map = new HashMap<>();
    	map.put("pageIndex", 1);
    	map.put("pageSize", 10);
    	map.put("name", "aa");
    	map.put("userType", "1");
    	String requestURL = "/sysUser/list7?userStr=" + URLEncoder.encode(JsonUtil.obj2JsonStr(map), "utf-8");
    	System.out.println(requestURL);
    	MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.get(requestURL)
//    			.contentType(MediaType.APPLICATION_FORM_URLENCODED)
    			)
    			.andExpect(MockMvcResultMatchers.status().isOk())
    			.andDo(MockMvcResultHandlers.print())
    			.andReturn();
    	int status = mvcResult.getResponse().getStatus();  
    	String content = mvcResult.getResponse().getContentAsString();
    	System.out.println("Response-status：" + status);
    	System.out.println("Response-content：" + content);
    }
    
    //@RequestBody SysUser user 对象接收
    @Test
    public void list3_post() throws Exception {
    	//post请求需要将contentType对应好
    	SysUser user = new SysUser();
    	user.setName("aa");
    	user.setUserType("1");
    	MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.post("/sysUser/list3")
//	    		.accept(MediaType.APPLICATION_JSON)
	    		.contentType(MediaType.APPLICATION_JSON)
	    		//该种参数传递，controller用@RequestBody接收
	    		.content(JsonUtil.obj2JsonStr(user)))
		    	.andExpect(MockMvcResultMatchers.status().isOk())
		    	.andDo(MockMvcResultHandlers.print())
		    	.andReturn();
    	//可通过result.getResponse() 拿到MockHttpServletResponse 对象，然后可以获得其他返回信息。
    	int status = mvcResult.getResponse().getStatus();  
    	String content = mvcResult.getResponse().getContentAsString();
    	System.out.println("Response-status：" + status);
    	System.out.println("Response-content：" + content);
    }
    
    
    //@RequestBody SysUser user 对象接收，并URL上跟了其他参数
    @Test
    public void list5_post() throws Exception {
    	//post请求需要将contentType对应好
    	SysUser user = new SysUser();
    	user.setName("aa");
    	user.setUserType("1");
    	String requestURL = "/sysUser/list5?pageIndex=1&pageSize=10";
    	MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.post(requestURL)
    			.contentType(MediaType.APPLICATION_JSON_UTF8)
    			//该种参数传递，controller用@RequestBody接收
    			.content(JsonUtil.obj2JsonStr(user)))
    			.andExpect(MockMvcResultMatchers.status().isOk())
    			.andDo(MockMvcResultHandlers.print())
    			.andReturn();
    	//可通过result.getResponse() 拿到MockHttpServletResponse 对象，然后可以获得其他返回信息。
    	int status = mvcResult.getResponse().getStatus();  
    	String content = mvcResult.getResponse().getContentAsString();
    	System.out.println("Response-status：" + status);
    	System.out.println("Response-content：" + content);
    }
    
    //@RequestBody String userStr 接收
    @Test
    public void list6_post() throws Exception {
    	Map<String, Object> map = new HashMap<>();
    	map.put("pageIndex", 1);
    	map.put("pageSize", 10);
    	map.put("name", "aa");
    	map.put("userType", "1");
    	
    	MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.post("/sysUser/list6")
    			.contentType(MediaType.APPLICATION_JSON_UTF8)
    			.content(JsonUtil.obj2JsonStr(map)))
    			.andExpect(MockMvcResultMatchers.status().isOk())
    			.andDo(MockMvcResultHandlers.print())
    			.andReturn();
    	//可通过result.getResponse() 拿到MockHttpServletResponse 对象，然后可以获得其他返回信息。
    	int status = mvcResult.getResponse().getStatus();  
    	String content = mvcResult.getResponse().getContentAsString();
    	System.out.println("Response-status：" + status);
    	System.out.println("Response-content：" + content);
    }
    
    
    @Test
    public void list6_2_post() throws Exception {
    	Map<String, Object> map = new HashMap<>();
    	map.put("pageIndex", 1);
    	map.put("pageSize", 10);
    	map.put("name", "aa");
    	map.put("userType", "1");
    	
    	MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.post("/sysUser/list6_2")
    			.contentType(MediaType.APPLICATION_JSON_UTF8)
    			.content(JsonUtil.obj2JsonStr(map)))
    			.andExpect(MockMvcResultMatchers.status().isOk())
    			.andDo(MockMvcResultHandlers.print())
    			.andReturn();
    	//可通过result.getResponse() 拿到MockHttpServletResponse 对象，然后可以获得其他返回信息。
    	int status = mvcResult.getResponse().getStatus();  
    	String content = mvcResult.getResponse().getContentAsString();
    	System.out.println("Response-status：" + status);
    	System.out.println("Response-content：" + content);
    }
    
    
    /*
     * 注意list6_post和list8_post的区别，主要在content和contentType上，
     * 这个的根源在于@RequestParam和@RequestBody2个注解的区别
     */
    //@RequestParam String userStr
    @Test
    public void list8_post() throws Exception {
    	Map<String, Object> map = new HashMap<>();
    	map.put("pageIndex", 1);
    	map.put("pageSize", 10);
    	map.put("name", "aa");
    	map.put("userType", "1");
    	
    	MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.post("/sysUser/list8")
    			.contentType(MediaType.APPLICATION_FORM_URLENCODED)
    			.content("userStr="+JsonUtil.obj2JsonStr(map)))
    			.andExpect(MockMvcResultMatchers.status().isOk())
    			.andDo(MockMvcResultHandlers.print())
    			.andReturn();
    	//可通过result.getResponse() 拿到MockHttpServletResponse 对象，然后可以获得其他返回信息。
    	int status = mvcResult.getResponse().getStatus();  
    	String content = mvcResult.getResponse().getContentAsString();
    	System.out.println("Response-status：" + status);
    	System.out.println("Response-content：" + content);
    }
	
}
