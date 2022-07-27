package com.ximuyi.demo.redis;

import com.ximuyi.demo.api.CommonResult;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@RequestMapping("/authCode")
@Controller
public class AuthCodeController {

	@Autowired
	private AuthCodeService authCodeService;

	@ApiOperation("获取验证码")
	@RequestMapping(value = "/getAuthCode", method = RequestMethod.GET)
	@ResponseBody
	public CommonResult getAuthCode(@RequestParam String telephone) {
		return authCodeService.generateAuthCode(telephone);
	}

	@ApiOperation("判断验证码是否正确")
	@RequestMapping(value = "/verifyAuthCode", method = RequestMethod.POST)
	@ResponseBody
	public CommonResult updatePassword(@RequestParam String telephone,
									   @RequestParam String authCode) {
		return authCodeService.verifyAuthCode(telephone,authCode);
	}
}
