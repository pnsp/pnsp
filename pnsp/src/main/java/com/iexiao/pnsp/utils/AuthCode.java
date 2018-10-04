package com.iexiao.pnsp.utils;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Random;
import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.iexiao.pnsp.base.constants.UserConstant;

/**
 * 生成类 数字文字图片验证码
 *
 * @author Nick
 */
@Component
public class AuthCode {

  private static final Logger LOGGER = LoggerFactory.getLogger(AuthCode.class);
	
  /**
   * 随机取得一个字体
   *
   * @param random random 随机数
   * @return Font 返回一个新字体
   */
  private synchronized Font getsFont(Random random) {
    return new Font("Fixedsys", Font.CENTER_BASELINE, 18);
  }

  /**
   * 返回一个随机颜色
   *
   * @param fc     fc 随机数
   * @param bc     bc 随机数
   * @param random random 随机数
   * @return Color 返回一个新颜色
   */
  synchronized Color getRandColor(int fc, int bc, Random random) {
    if (fc > 255)
      fc = 255;
    if (bc > 255)
      bc = 255;
    int r = fc + random.nextInt(bc - fc - 6);
    int g = fc + random.nextInt(bc - fc - 4);
    int b = fc + random.nextInt(bc - fc - 8);
    return new Color(r, g, b);
  }

  /**
   * 生成随机数图片
   */
  public synchronized void getRandcode(HttpServletRequest request,
                                       HttpServletResponse response){
    System.setProperty("java.awt.headless", "true");
    HttpSession session = request.getSession();
    LOGGER.info("authCode sessionId ====================== " + session.getId());
    int width = 88, height = 38;// 设置图片大小
    BufferedImage image = new BufferedImage(width, height,
            BufferedImage.TYPE_INT_RGB);
    Graphics g = image.getGraphics();
    Random random = new Random();
    g.fillRect(0, 0, width, height);// 设定边框
    g.setFont(new Font("Times New Roman", Font.ROMAN_BASELINE, 18));
    g.setColor(getRandColor(111, 133, random));
    // 产生随机线(干扰线)
    for (int i = 0; i < 30; i++) {
      int x = random.nextInt(width);
      int y = random.nextInt(height);
      int xl = random.nextInt(13);
      int yl = random.nextInt(15);
      g.drawLine(x, y, x + xl, y + yl);
    }
    // 产生随机点
    g.setColor(getRandColor(130, 150, random));
    // 产生5个随机数
    String sRand = "";
    for (int i = 0; i < 5; i++) {
      g.setFont(getsFont(random));
      g.setColor(new Color(random.nextInt(101), random.nextInt(111), random
              .nextInt(121)));
			  
	  //字母数字组合
      //String rand = String.valueOf(getRandomString(random.nextInt(36)));
	  
	  //纯数字
      String rand = String.valueOf(getRandomString(random.nextInt(10)));

      // 成语(只取一次，注释6次for循环)
      //String rand = getRandomString(random.nextInt(3));
	  
      sRand += rand;
      g.translate(random.nextInt(5), random.nextInt(5));
      g.drawString(rand, 15 * i, 22);
    }
    session.removeAttribute(UserConstant.AUTH_CODE);
    session.setAttribute(UserConstant.AUTH_CODE, sRand);
    session.setMaxInactiveInterval(2*60);
    g.dispose();
    try {
		ImageIO.write(image, "JPEG", response.getOutputStream());
	} catch (IOException e) {
		LOGGER.error("authCode ImageIO.write =================== IOException",e);
	}
  }

  public synchronized String getRandomString(int num) {
	//纯数字
    String randnumber = "0123456789";
	return String.valueOf(randnumber.charAt(num));
	
	//字母数字组合
    //String randstring = "0123456789abcdefghijklmnopqrstuvwxyz";
    //return String.valueOf(randstring.charAt(num));

    // 成语
    //String[] codes = {"财运亨通", "一帆风顺", "今晚吃鸡"};
    //return codes[num];
  }
}
