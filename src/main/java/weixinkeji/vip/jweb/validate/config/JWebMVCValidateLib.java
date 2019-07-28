package weixinkeji.vip.jweb.validate.config;

import java.util.Map;

import weixinkeji.vip.jweb.validate.JWebMVCValidateVo;

public interface JWebMVCValidateLib {

	/**
	 * 注册正则表达式 
	 * 
	 * @param kv Map
	 */
	void regRegex(Map<String, JWebMVCValidateVo> kv);

}
