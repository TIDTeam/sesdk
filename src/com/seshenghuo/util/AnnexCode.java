/**
 * @file AnnexCode.java
 * @date Aug 6, 2006
 * @author lijun
 * @version 1.0.0
 */

package com.seshenghuo.util;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Random;

import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * @author lijun
 * 
 */
public class AnnexCode {
	public AnnexCode() {

	}

	private char[] ch = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9',
			'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M',
			'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z' };
	private String value;

	/**
	 * 给定范围获得随机颜色
	 * 
	 * @param fc
	 * @param bc
	 * @return
	 */
	private Color getColor(int fc, int bc) {
		Random random = new Random();
		if (fc > 255)
			fc = 255;
		if (bc > 255)
			bc = 255;
		int r = fc + random.nextInt(bc - fc);
		int g = fc + random.nextInt(bc - fc);
		int b = fc + random.nextInt(bc - fc);
		return new Color(r, g, b);
	}

	/**
	 * 生成图象
	 * 
	 * @param width
	 * @param height
	 * @return
	 */
	private BufferedImage makeImage(int width, int height) {
		// 在内存中创建图象
		BufferedImage image = new BufferedImage(width, height,
				BufferedImage.TYPE_INT_RGB);
		Graphics g = image.getGraphics();
		String font = Config.getValue("annexcode.fontFamily");
		String size = Config.getValue("annexcode.fontSize");
		int _size = Integer.valueOf(size).intValue();
		// 生成随机类
		Random rand = new Random();
		// 设定背景色
		g.setColor(getColor(200, 250));
		g.fillRect(0, 0, width, height);
		// 设定字体
		g.setFont(new Font(font, Font.PLAIN, _size));
		// 画边框
		g.setColor(new Color(150, 150, 150));
		g.drawRect(0, 0, width - 1, height - 1);
		// 随机产生155条干扰线，使图象中的认证码不易被其它程序探测到
		g.setColor(getColor(160, 200));
		for (int i = 0; i < 155; i++) {
			int x = rand.nextInt(width);
			int y = rand.nextInt(height);
			int xl = rand.nextInt(12);
			int yl = rand.nextInt(12);
			g.drawLine(x, y, x + xl, y + yl);
		}

		String sRand = "";
		int len = Config.getIntValue("annexcode.length");
		// 取随机产生的认证码(4位数字)
		for (int i = 0; i < len; i++) {
			String iRand = String.valueOf(ch[rand.nextInt(36)]);
			sRand += iRand;
			// 将认证码显示到图象中
			g.setColor(new Color(20 + rand.nextInt(110),
					20 + rand.nextInt(110), 20 + rand.nextInt(110)));
			// 调用函数出来的颜色相同，可能是因为种子太接近，所以只能直接生成
			g.drawString(iRand, 13 * i + 6, 12);
		}
		setValue(sRand);
		g.dispose();
		return image;
	}

	/**
	 * 输出图片
	 * 
	 * @param request
	 * @param response
	 */
	public void write(HttpServletRequest request, HttpServletResponse response) {
		HttpSession session = request.getSession(true);

		int w = Config.getIntValue("annexcode.width");
		int h = Config.getIntValue("annexcode.height");
		try {
			BufferedImage image = makeImage(w, h);
			ServletOutputStream o = response.getOutputStream();
			session.setAttribute("annexCode", getValue());
			ImageIO.write(image, "JPEG", o);
		} catch (IOException e) {
			// System.out.println("make image fail !");
		}
	}

	/**
	 * 获取
	 * 
	 * @return the value
	 */
	private String getValue() {
		return this.value;
	}

	/**
	 * 设置
	 * 
	 * @param value
	 *            the value to set
	 */
	private void setValue(String value) {
		this.value = value;
	}
}
