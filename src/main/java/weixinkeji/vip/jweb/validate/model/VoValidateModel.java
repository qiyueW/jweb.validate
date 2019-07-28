package weixinkeji.vip.jweb.validate.model;

import java.lang.reflect.Field;
import java.lang.reflect.Parameter;
import java.util.HashMap;
import java.util.Map;

import weixinkeji.vip.jweb.validate.JWebMVCValidateVo;
import weixinkeji.vip.jweb.validate.ValidateResult;
import weixinkeji.vip.jweb.validate._tools.Tools;
import weixinkeji.vip.jweb.validate.ann.BindRegex;
import weixinkeji.vip.jweb.validate.ann.RegexAttribute;

/**
 * Vo校验模型
 * 
 * @author wangchunzi
 *
 */
public class VoValidateModel {
	private final Class<?> voClass;
	// vo-属性关联的校验数据
	private final Map<Field, JWebMVCValidateVo> validate_ClassField;
	private static final Map<Class<?>, VoValidateModel> voMap = new HashMap<>();
	private static final Map<Parameter, VoValidateModel> voParamMap = new HashMap<>();

	synchronized public static VoValidateModel getVoValidateModel(Class<?> c) {
		VoValidateModel vo = voMap.get(c);
		if (null != vo) {
			return vo;
		}
		vo = new VoValidateModel(c);
		voMap.put(c, vo);
		return vo;
	}

	synchronized public static VoValidateModel getVoValidateModel(Parameter param) {
		VoValidateModel vo = voParamMap.get(param);
		if (null != vo) {
			return vo;
		}
		vo = new VoValidateModel(param);
		voParamMap.put(param, vo);
		return vo;
	}

	/**
	 * 取得vo对象的检验集合
	 * 
	 * @param c        vo类
	 * @param webParam 改写指定字段，是否为必填项。 语法： {字段名=true|false}
	 * @return Map<Field, JWebMVCValidateVo>
	 */
	private static Map<Field, JWebMVCValidateVo> getJWebMVCValidateVo_fromVo(Class<?> c, String[] webParam) {
		Map<Field, JWebMVCValidateVo> map = new HashMap<Field, JWebMVCValidateVo>();
		Map<String, Boolean> lockAlloyNull = new HashMap<String, Boolean>();
		if (null != webParam && webParam.length > 0) {
			String kv[];
			for (String kvStr : webParam) {
				if (null == kvStr || kvStr.trim().equalsIgnoreCase("")) {
					continue;
				}
				kv = kvStr.trim().split("=");
				if (kv.length == 1) {
					lockAlloyNull.put(kv[0].trim(), false);
				} else if (kv[1].trim().equalsIgnoreCase("")) {
					lockAlloyNull.put(kv[0].trim(), false);
				} else {
					lockAlloyNull.put(kv[0].trim(), Boolean.parseBoolean(kv[1]));
				}
			}
		}
		Field[] fsObj = c.getDeclaredFields();

		JWebMVCValidateVo vo;
		for (Field fs : fsObj) {
			// 字段名，当作key
			if (null != (vo = Tools.getRegex(fs.getName(), fs.getAnnotation(BindRegex.class),
					lockAlloyNull.get(fs.getName())))) {
				map.put(fs, vo);
				fs.setAccessible(true);
				System.out.println(
						"收集到：" + fs.getName() + ", " + vo.regex + "//" + vo.errorMessage + "//" + vo.alloyNull);
			}
		}
		return map;
	}

	/**
	 * 直接传vo对象的方式
	 * 
	 * @param voClass
	 */
	private VoValidateModel(Class<?> voClass) {
		this.voClass = voClass;
		// 当vo作为方法传参时，才有的注解符
		RegexAttribute rat = this.voClass.getAnnotation(RegexAttribute.class);
		this.validate_ClassField = getJWebMVCValidateVo_fromVo(this.voClass, null != rat ? rat.alloyNull() : null);
	}

	/**
	 * vo对象从方法参数中来
	 * 
	 * @param param 参数
	 */
	private VoValidateModel(Parameter param) {
		this.voClass = param.getType();
		// 当vo作为方法传参时，才有的注解符
		RegexAttribute rat = param.getAnnotation(RegexAttribute.class);
		this.validate_ClassField = getJWebMVCValidateVo_fromVo(this.voClass, null != rat ? rat.alloyNull() : null);
	}

	/**
	 * 用户vo校验
	 * 
	 * @param obj         vo实例
	 * @param errorReturn 是否遇错返回;true表示是；false表示否
	 * @return ValidateResult null时表示校验通过
	 */
	public ValidateResult check(Object obj, boolean errorReturn) {
		if (null == obj) {
			return null;
		}
		Object ovalue;
		ValidateResult vd = new ValidateResult();
		if (errorReturn) {
			for (Map.Entry<Field, JWebMVCValidateVo> kv : this.validate_ClassField.entrySet()) {
				try {
					ovalue = kv.getKey().get(obj);// 取出字段的值
					if (null == ovalue) {// 当值为null时，只有一种情况可以通过——规则允许为null
						if (kv.getValue().alloyNull) {// 如果允许字段为null,表示正常通过
							continue;// 继续下一个校验
						} else {
							vd.addError(kv.getKey().getName(), kv.getValue().errorMessage);
							return vd;
						}
					}
					// 找不到关联的正则表达式，同时本身也没有正则表达式，还设置为非null的：那么，只要非null只放行
					if (kv.getValue().regex.isEmpty()) {
						continue;// 继续下一个校验
					}
					// 执行正则表达式校验。如果不通过，直接返回
					if (!ovalue.toString().matches(kv.getValue().regex)) {
						vd.addError(kv.getKey().getName(), kv.getValue().errorMessage);
						return vd;
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		} else {
			for (Map.Entry<Field, JWebMVCValidateVo> kv : this.validate_ClassField.entrySet()) {
				try {
					ovalue = kv.getKey().get(obj);
					if (null == ovalue) {// 当值为null时，只有一种情况可以通过——规则允许为null
						if (kv.getValue().alloyNull) {// 如果允许字段为null,表示正常通过
							continue;// 继续下一个校验
						} else {
							vd.addError(kv.getKey().getName(), kv.getValue().errorMessage);
							continue;// 继续下一个校验
						}
					}
					// 找不到关联的正则表达式，同时本身也没有正则表达式，还设置为非null的：那么，只要非null只放行
					if (kv.getValue().regex.isEmpty()) {
						continue;// 继续下一个校验
					}
					// 校验正则表达式
					if (!ovalue.toString().matches(kv.getValue().regex)) {
						vd.addError(kv.getKey().getName(), kv.getValue().errorMessage);
						continue;
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
		return vd;
	}
}
