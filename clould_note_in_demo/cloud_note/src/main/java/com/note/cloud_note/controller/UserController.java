package com.note.cloud_note.controller;

import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

import javax.annotation.Resource;
import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.note.cloud_note.exception.PasswordException;
import com.note.cloud_note.exception.UserNameException;
import com.note.cloud_note.exception.UserNotFoundException;
import com.note.cloud_note.service.UserService;
import com.note.cloud_note.util.JsonResult;
import com.note.cloud_note.vo.User;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;



@Controller
@RequestMapping("/user")
public class UserController extends AbstractController{
	@Resource
	private UserService userService;
	
	@RequestMapping("/login.do")
	@ResponseBody
	public Object login(String name,String password,HttpSession session){
		User user=userService.login(name, password);
		//登陆成功的时候，将user信息保存到session，用于在过滤器中检查登录情况
		session.setAttribute("loginUser", user);
		return new JsonResult(user);
		
	}
	
	@RequestMapping("/regist.do")
	@ResponseBody
	public JsonResult regist(String name, String nick, String password, String confirm){
		User user=userService.regist(name, nick, password, confirm);
		return new JsonResult(user);
	}
	
	
	
	@ExceptionHandler(UserNameException.class)
	@ResponseBody
	public JsonResult handleUserNotFound(UserNameException e){
		e.printStackTrace();
		return new JsonResult(4,e);
	}
	
	@ExceptionHandler(UserNotFoundException.class)
	@ResponseBody
	public JsonResult handleUserNotFound(UserNotFoundException e){
		e.printStackTrace();
		return new JsonResult(2,e);
	}
	
	@ExceptionHandler(PasswordException.class)
	@ResponseBody
	public JsonResult handlePassword(PasswordException e){
		e.printStackTrace();
		return new JsonResult(3,e);
	}
	
	/*
	 * @ResponseBody注解会自动处理控制器返回值
	 * 1、如果是Javabean（数组，集合）返回json，
	 * 2、如果是byte数组，则将byte数组直接装入响应消息的body
	 */
	//produces="image/png"用于设置content-type
	@RequestMapping(value="/image.do",produces="image/png")
	@ResponseBody
	public byte[] image() throws Exception{
		return creatPng();
	}
	
	@RequestMapping(value="/downloading.do",produces="Application/octet-stream")
	@ResponseBody
	public byte[] downloading(HttpServletResponse res) throws IOException{
		//Content-Disposition: attachment; filename="fname.ext"
		res.setHeader("Content-Disposition", "attachment;filename=\"demo.png\"");
		return creatPng();
		
	}
	@RequestMapping(value="/excel.do",produces="Application/octet-stream")
	@ResponseBody
	public byte[] downloading2(HttpServletResponse res) throws IOException{
		//Content-Disposition: attachment; filename="fname.ext"
		res.setHeader("Content-Disposition", "attachment;filename=\"demo.xls\"");
		return creatExcel();
	}
	
	public byte[] creatExcel() throws IOException{
		//创建工作簿
		HSSFWorkbook workbook=new HSSFWorkbook();
		//创建工作表
		HSSFSheet sheet=workbook.createSheet("demo");
		//在工作表中创建数据行
		HSSFRow row=sheet.createRow(0);
		//创建行中的格子
		HSSFCell cell=row.createCell(0);
		cell.setCellValue("Hello World!");
		//将Excel文件保存为byte数组
		ByteArrayOutputStream out =new ByteArrayOutputStream();
		workbook.write(out);
		out.close();
		return out.toByteArray();
	}
	
	
	
	/**
	 * 创建一个图片，并且编码为png格式，返回编码以后的数据
	 * @throws IOException 
	 */
	public byte[] creatPng() throws IOException{
		BufferedImage img= new BufferedImage(200, 80, BufferedImage.TYPE_3BYTE_BGR);
		//在图片上绘制内容
		img.setRGB(100, 40, 0xffffff);
		//将图片编码为png
		ByteArrayOutputStream out= new ByteArrayOutputStream();
		ImageIO.write(img, "png", out);
		byte[] png=out.toByteArray();
		out.close();
		return png;
	}
	
	@RequestMapping("/upload.do")
	@ResponseBody
	public JsonResult upload(MultipartFile userfile1,MultipartFile userfile2) throws Exception{
		//springmvc 中可以利用MultipartFile接受上载的文件！
		//文件中的一切数据都可以从MultipartFile对象中找到
		
		//获取上载的原始文件名
		String file1=userfile1.getOriginalFilename();
		String file2=userfile2.getOriginalFilename();
		System.out.println(file1);
		System.out.println(file2);
		
		/*
		 * 3种方法
		 * 1、transferTo(目标文件)
		 * 2、userfile1.getBytes()获取文件的全部数据，将文件全部读取到内存，适合处理小文件
		 * 2、userfile1.getInputStream() 获取上载文件的流，适合处理大文件
		 */
		File dir=new File("D:/demo");
		dir.mkdir();
		File f1=new File(dir, file1);
		File f2=new File(dir, file2);
		//第一种保存文件
//		userfile1.transferTo(f1);
		
		//第三种 利用流复制数据
		InputStream in1 = userfile1.getInputStream();
		FileOutputStream out1 = new FileOutputStream(f1);
		int b;
		while((b=in1.read())!=-1){
			out1.write(b);
		}
		in1.close();
		out1.close();
		
		System.out.println("ok");
		
		InputStream in2 = userfile2.getInputStream();
		FileOutputStream out2=new FileOutputStream(f2);
		byte[] buf= new byte[8*1024];
		int n;
		while((n=in2.read(buf))!=-1){
			out2.write(buf, 0, n);
		}
		in2.close();
		out2.close();
		
		return new JsonResult(true);
	}
	

	
}
