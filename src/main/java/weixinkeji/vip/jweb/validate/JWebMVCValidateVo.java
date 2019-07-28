package weixinkeji.vip.jweb.validate;

public class JWebMVCValidateVo {

	public final String keyName;
	public final String regex;
	public final String errorMessage;
	public final boolean alloyNull;

	/**
	 * 构造方法
	 * 
	 * @param keyName      返回结果时，显示的字段名
	 * @param regex        正则表达式
	 * @param errorMessage 错误时，提示的信息
	 * @param isMust       是否允许null
	 */
	public JWebMVCValidateVo(String keyName, String regex, String errorMessage, boolean isMust) {
		this.keyName = keyName;
		this.regex = regex;
		this.errorMessage = errorMessage;
		this.alloyNull = isMust;
	}

	/**
	 * 构造方法 。默认非null
	 * 
	 * @param keyName      返回结果时，显示的字段名
	 * @param regex        正则表达式
	 * @param errorMessage 错误时，提示的信息
	 */
	public JWebMVCValidateVo(String keyName, String regex, String errorMessage) {
		this.keyName = keyName;
		this.regex = regex;
		this.errorMessage = errorMessage;
		this.alloyNull = false;
	}

	/**
	 * 檢查不通過時，返回錯誤的信息
	 * 
	 * @param source 被檢測的字符串
	 * @return String null|錯誤的字符串
	 */
	public String check(String source) {
		return source.matches(regex) ? null : errorMessage;
	}

}
