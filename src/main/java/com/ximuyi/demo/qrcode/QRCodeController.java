package com.ximuyi.demo.qrcode;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;

@RestController
@RequestMapping("/qrcode")
public class QRCodeController {

	private static final Logger logger = LoggerFactory.getLogger(QRCodeController.class);

	@Autowired
	private QRCodeService qrCodeService;


	@GetMapping("/getQRCode")
	public void getQRCode(String codeContent, HttpServletResponse response) {
		try {
			qrCodeService.createQRCode2Stream(codeContent, response);
			logger.info("成功生成二维码！");
		}
		catch (Exception e) {
			logger.error("发生错误， 错误信息是：{}！", e.getMessage());
		}
	}


	@GetMapping("/saveQRCode")
	public void saveQRCode(String codeContent) {
		try {
			qrCodeService.createQRCode2File(codeContent, "C:/Users/chenjingjun/Desktop/QRCode.jpg");
			logger.info("成功生成二维码！");
		}
		catch (Exception e) {
			logger.error("发生错误， 错误信息是：{}！", e.getMessage());
		}
	}
}
