package weixinkeji.vip.jweb.validate;

import java.util.ArrayList;
import java.util.List;

public class ValidateResult {

	/**
	 * 错误的数量
	 */
	private int errorCount = 0;

	/**
	 * 错误的数据集合
	 */
	private List<String[]> voError = new ArrayList<>();

	/**
	 * 添加一个错误
	 * 
	 * @param name    字段名
	 * @param message 错误提示
	 * 
	 * @return ValidateResult
	 */
	public ValidateResult addError(String name, String message) {
		this.errorCount++;
		this.voError.add(new String[] { name, message });
		return this;
	}

	/**
	 * 有多少错误
	 * 
	 * @return int
	 */
	public int getErrorCount() {
		return errorCount;
	}

	/**
	 * 错误的集合
	 * 
	 * @return List
	 */
	public List<String[]> getVoError() {
		return voError;
	}

	/**
	 * 把结果变成json格式的数据
	 * 
	 * @return String
	 */
	@Override
	public String toString() {
		if (errorCount == 0) {
			return "[]";
		}
		StringBuilder sb = new StringBuilder();
		String[] rs;
		if (this.errorCount == 1) {
			rs = voError.get(0);
			sb.append("[");
			this.addToJson(sb, rs[0], rs[1]);
			sb.append("]");
			return sb.toString();
		}
		for (int i = 0; i < this.errorCount; i++) {
			rs = this.voError.get(i);
			sb.append(",");
			this.addToJson(sb, rs[0], rs[1]);
		}
		return "[" + sb.substring(1) + "]";
	}

	/**
	 * 把结果变成json格式的数据
	 * 
	 * @return String
	 */
	public String toJsonStrs() {
		if (errorCount == 0) {
			return "[]";
		}
		StringBuilder sb = new StringBuilder();
		String[] rs;
		if (this.errorCount == 1) {
			rs = voError.get(0);
			sb.append("[");
			this.addToJson(sb, rs[0], rs[1]);
			sb.append("]");
			return sb.toString();
		}
		for (int i = 0; i < this.errorCount; i++) {
			rs = this.voError.get(i);
			sb.append(",");
			this.addToJson(sb, rs[0], rs[1]);
		}
		return "[" + sb.substring(1) + "]";
	}

	private void addToJson(StringBuilder sb, String fieldName, String error) {
		sb.append("{\"fieldName\":\"").append(fieldName).append("\"").append(",\"message\":\"")
				.append(null == error ? "" : error).append("\"}");
	}

}
