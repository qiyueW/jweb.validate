package weixinkeji.vip.jweb.validate.init;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import weixinkeji.vip.jweb.validate.JWebMVCValidateVo;

public class InitValidateDataCenter {
	/**
	 * 正则表达式库
	 */
	static final Map<String, JWebMVCValidateVo> sysValidationLib = new HashMap<>();
	private static boolean isInit = false;

	/**
	 * 通过key，取得关联的正则表达式
	 * 
	 * @param key 表达式key
	 * @return JWebMVCValidateVo
	 */
	public static JWebMVCValidateVo getJWebMVCValidateVoByRegexKey(String key) {
		return sysValidationLib.get(key);
	}

	/**
	 * 初始化
	 * 
	 * @param list 类集合
	 */
	synchronized public static void _0_init(List<Class<?>> list) {
		if (isInit) {
			return;
		}
		isInit = true;
		// 初始化用户配置
		new _0_init_validateLib(list, sysValidationLib);

	}

}
