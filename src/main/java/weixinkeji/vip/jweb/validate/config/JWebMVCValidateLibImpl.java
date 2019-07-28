package weixinkeji.vip.jweb.validate.config;

import java.util.Map;

import weixinkeji.vip.jweb.validate.JWebMVCValidateVo;

public class JWebMVCValidateLibImpl implements JWebMVCValidateLib {

	@Override
	public void regRegex(Map<String, JWebMVCValidateVo> kv) {
		kv.put("int", new JWebMVCValidateVo("", "^[0-9]{0,11}$", ""));
		kv.put("double", new JWebMVCValidateVo("", "^[-+]?[0-9]*\\.?[0-9]+([eE][-+]?[0-9]+)?$", ""));
	}

}
