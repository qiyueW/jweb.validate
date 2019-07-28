package weixinkeji.vip.jweb.validate;

import java.lang.reflect.Method;

public class JWebValidate {

	/**
	 * 校验对象或java基本类型
	 * 
	 * @param obj 对象
	 * @return ValidateResult
	 */
	public static ValidateResult check(Object obj) {
		if (null == obj) {
			return null;
		}
		return null;
	}

	/**
	 * 校验 方法里的传参
	 * 
	 * @param method   方法
	 * @param argValue 传参 值
	 * @return ValidateResult
	 */
	public static ValidateResult check(Method method, Object... argValue) {
		return null;
	}

	/**
	 * 校验 方法里的第index个传参 （index从0开始算）
	 * 
	 * @param method   方法
	 * @param index    参数下标
	 * @param argValue 传参 值
	 * @return ValidateResult
	 */
	public static ValidateResult check(Method method, int index, Object argValue) {
		return null;
	}
}
