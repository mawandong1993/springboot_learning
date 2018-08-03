package com.note.cloud_note.web;

import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class ImageServite extends HttpServlet {
	private static final long serialVersionUID = 2221338580531255869L;
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		//发送照片
		byte[] png=creatPng();
		response.setContentType("image/png");
		response.setContentLength(png.length);
		//在消息body中发送消息数据
		response.getOutputStream().write(png);
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

}
