package com.ximuyi.demo.redis;

import com.ximuyi.demo.api.CommonResult;

public interface AuthCodeService {

	/**
	 * 生成验证码
	 */
	CommonResult generateAuthCode(String telephone);

	/**
	 * 判断验证码和手机号码是否匹配
	 */
	CommonResult verifyAuthCode(String telephone, String authCode);
}
