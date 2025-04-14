package com.zust.mallapi.utils;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Base64;
import java.util.Map;
import java.util.HashMap;
import java.util.Random;
import java.util.UUID;
import javax.imageio.ImageIO;

public class VerifyCodeUtil {
    public static final int WIDTH = 60; // 验证码图片宽度
    public static final int HEIGHT = 20; // 验证码图片高度
    public static final String CHARS = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ"; // 种子数据
    public static final int CODE_LENGTH = 4; // 验证码长度
    public static final int BG_COLOR = 0xDCDCDC; // 验证码图片背景颜色

    // 生成验证码和图片的方法
    public static Map<String, String> generateVerifyCode() {
        Map<String, String> result = new HashMap<>();
        String code = generateCode(); // 随机生成验证码
        BufferedImage image = generateImage(code); // 根据验证码生成图片

        // 将验证码和图片的 Base64 编码保存到 Map
        try {
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            // 写入 JPEG 格式的图片数据到字节流
            ImageIO.write(image, "JPEG", baos);
            byte[] buffer = baos.toByteArray();

            // 生成 UUID
            String uuid = UUID.randomUUID().toString();

            // 将 UUID 和图片的 Base64 编码保存到 Map
            result.put("uuid", uuid);
            result.put("img", Base64.getEncoder().encodeToString(buffer));
        } catch (IOException e) {
            e.printStackTrace();
        }

        result.put("code", code); // 保存验证码文本
        return result;
    }

    // 生成随机验证码
    private static String generateCode() {
        Random random = new Random();
        StringBuilder code = new StringBuilder(CODE_LENGTH);
        for (int i = 0; i < CODE_LENGTH; i++) {
            int index = random.nextInt(CHARS.length());
            code.append(CHARS.charAt(index));
        }
        return code.toString();
    }

    // 生成验证码图片
    private static BufferedImage generateImage(String code) {
        BufferedImage image = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_RGB);
        Graphics g = image.getGraphics();

        // 设置背景颜色
        g.setColor(new Color(BG_COLOR));
        g.fillRect(0, 0, WIDTH, HEIGHT);

        // 设置字体
        g.setFont(new Font("Arial", Font.PLAIN, 18));

        // 设置验证码字符颜色
        g.setColor(Color.BLACK);
        g.drawString(code, 10, 18);

        // 添加一些干扰线
        addNoise(g);

        g.dispose();
        return image;
    }

    // 添加一些干扰线
    private static void addNoise(Graphics g) {
        Random random = new Random();
        g.setColor(Color.GRAY);
        for (int i = 0; i < 3; i++) {
            int x1 = random.nextInt(WIDTH);
            int y1 = random.nextInt(HEIGHT);
            int x2 = random.nextInt(WIDTH);
            int y2 = random.nextInt(HEIGHT);
            g.drawLine(x1, y1, x2, y2);
        }
    }

    /**
     * 校验验证码是否正确
     * @param code 输入的验证码
     * @param verifyCode 生成的验证码
     * @param ignoreCase 是否忽略大小写
     * @return 是否验证通过
     */
    public static boolean verification(String code, String verifyCode, boolean ignoreCase) {
        if (code != null && !code.trim().equals("")) {
            return ignoreCase ? verifyCode.equalsIgnoreCase(code) : verifyCode.equals(code);
        }
        return false;
    }
}
