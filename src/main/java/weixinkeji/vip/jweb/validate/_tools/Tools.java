package weixinkeji.vip.jweb.validate._tools;

import java.util.Date;

import weixinkeji.vip.jweb.validate.JWebMVCValidateVo;
import weixinkeji.vip.jweb.validate.ann.BindRegex;
import weixinkeji.vip.jweb.validate.ann.RegexNullType;
import weixinkeji.vip.jweb.validate.init.InitValidateDataCenter;

public class Tools {
	/**
	 * 是否java基本类型
	 * 
	 * @param c Class
	 * @return boolean
	 */
	public static boolean isJavaBaseType(Class<?> c) {
		return c == byte.class || c == Byte.class || c == byte[].class || c == Byte[].class // byte
				|| c == boolean.class || c == Boolean.class// boolean
				|| c == char.class || c == Character.class || c == char[].class || c == Character[].class// char
				|| c == short.class || c == Short.class// short
				|| c == int.class || c == Integer.class // int
				|| c == long.class || c == Long.class // long
				|| c == float.class || c == Float.class // float
				|| c == double.class || c == Double.class // double
				|| c == Date.class || c == java.sql.Date.class;
	}

	/**
	 * 取得属性上的校验数据
	 * 
	 * @param viewKeyName    String 输出字符时的字符key
	 * @param br             BindRegex 绑定表达式的注解
	 * @param orderAlloyNull Boolean 不为null时，表示表达式中[alloyNull]强制使用此参数表示 是否为null
	 * @return JWebMVCValidateVo
	 */
	public static JWebMVCValidateVo getRegex(String viewKeyName, BindRegex br, Boolean orderAlloyNull) {
		if (null == br) {
			return null;
		}

		String keyName = br.keyName();// 返回结果，显示的结果名
		String regex = br.value().isEmpty() ? br.regex() : br.value();// 正则表达式
		String error = br.error();// 错误信息
		boolean alloyNull = br.alloyNull();// 是否允许null;

		if (null == keyName || keyName.trim().isEmpty()) {
			keyName = viewKeyName;
		}
		String[] placeholder = br.placeholder();
		JWebMVCValidateVo ref = InitValidateDataCenter.getJWebMVCValidateVoByRegexKey(regex);
		if (null != ref) {// 表示 引用公共库的表达式
			regex = ref.regex;// 使用引用过来的表达式
			if (null == error || error.trim().isEmpty()) {// 如果用户填写错误提示信息，则用引用的
				error = ref.errorMessage;
			}
			if (br.refAlloyNull() == RegexNullType.unknow) {// 如果用户填写是否允许null，则用引用的
				alloyNull = ref.alloyNull;
			}
		}
		if (null != orderAlloyNull) {
			alloyNull = orderAlloyNull;
		}
		regex = formatStrByPlaceholder(regex, placeholder);// 占位符处理
		return new JWebMVCValidateVo(keyName, regex, error, alloyNull);
	}

	/**
	 * 对占位符，逐一替换。<br>
	 * 占位符语法： ??下标 <br>
	 * 如：??1 表达第1个占位符，??2表达第2个占位符
	 * 
	 * @param str         源
	 * @param placeholder 替换占位符的数据
	 * @return String 被替换后的源
	 */
	private static String formatStrByPlaceholder(String str, String... placeholder) {// 逐一替换
		if (null != placeholder && placeholder.length > 0) {// 如果存在占位符
			for (int i = 0; i < placeholder.length; i++) {
				str = str.replace("??" + (i + 1), placeholder[i]);// 逐一替换
			}
			return str;
		}
		return str;
	}
}
