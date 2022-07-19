package com.ximuyi.demo.qrcode;


import cn.hutool.core.io.FileUtil;
import cn.hutool.extra.qrcode.QrCodeException;
import cn.hutool.extra.qrcode.QrCodeUtil;
import cn.hutool.extra.qrcode.QrConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletResponse;
import java.awt.*;
import java.io.File;
import java.io.IOException;

@Service
public class QRCodeService {

	private static final Logger logger = LoggerFactory.getLogger(QRCodeService.class);

	// 自定义参数，这部分是Hutool工具封装的
	private static QrConfig initQrConfig() {
		QrConfig config = new QrConfig(300, 300);
		// 设置边距，既二维码和背景之间的边距
		config.setMargin(3);
		// 设置前景色，既二维码颜色（青色）
		config.setForeColor(Color.CYAN);
		// 设置背景色（灰色）
		config.setBackColor(Color.GRAY);
		return config;
	}

	/**
	 * 生成到文件
	 *
	 * @param content
	 * @param filepath
	 */
	public void createQRCode2File(String content, String filepath) {
		try {
			File file = new File("src/main/resources/qrcode/icon.jpeg");
			QrCodeUtil.generate(content, QrConfig.create().setImg(file.getAbsoluteFile()), FileUtil.file(filepath));
			logger.info("生成二维码成功, 位置在：{}！", filepath);
		}
		catch (QrCodeException e) {
			logger.error("发生错误！ {}！", e.getMessage());
		}
	}

	/**
	 * 生成到流
	 *
	 * @param content
	 * @param response
	 */
	public void createQRCode2Stream(String content, HttpServletResponse response) {
		try {
			QrCodeUtil.generate(content, initQrConfig(), "png", response.getOutputStream());
			logger.info("生成二维码成功!");
		} catch (QrCodeException | IOException e) {
			logger.error("发生错误！ {}！", e.getMessage());
		}
	}
}
