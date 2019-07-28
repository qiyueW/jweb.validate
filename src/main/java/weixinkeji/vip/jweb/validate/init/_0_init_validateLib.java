package weixinkeji.vip.jweb.validate.init;

import java.util.List;
import java.util.Map;

import weixinkeji.vip.jweb.validate.JWebMVCValidateVo;
import weixinkeji.vip.jweb.validate.config.JWebMVCValidateLib;
import weixinkeji.vip.jweb.validate.config.JWebMVCValidateLibImpl;

public class _0_init_validateLib extends __InitModel {
	/**
	 * 需要扫描类的集合
	 * 
	 * @param list 扫描到的类
	 */
	public _0_init_validateLib(List<Class<?>> list, Map<String, JWebMVCValidateVo> sysValidationLib) {
		super(list);
		this.initConfig(sysValidationLib);
	}

	/**
	 * 初始化配置
	 * 
	 * @param sysValidationLib 正则表达式数据中心
	 */
	private void initConfig(Map<String, JWebMVCValidateVo> sysValidationLib) {
		// 初始化系统默认的表达式库
		JWebMVCValidateLib lib = new JWebMVCValidateLibImpl();
		lib.regRegex(sysValidationLib);// 加入系统默认的预定表达式

		// 用户的配置
		lib = super.findObject(JWebMVCValidateLib.class, null);
		if (null != lib) {
			lib.regRegex(sysValidationLib);
		}

	}

}
