package weixinkeji.vip.jweb.validate.model;

import java.lang.reflect.Parameter;
import java.util.HashMap;
import java.util.Map;

import weixinkeji.vip.jweb.validate.JWebMVCValidateVo;
import weixinkeji.vip.jweb.validate.ValidateResult;
import weixinkeji.vip.jweb.validate._tools.Tools;
import weixinkeji.vip.jweb.validate.ann.BindRegex;

/**
 * 普通类型校验模型
 * 
 * @author wangchunzi
 *
 */
public class BaseTypeValidateModel {
//	private final Class<?> voClass;
	private final JWebMVCValidateVo validateVo;
	private static final Map<Parameter, BaseTypeValidateModel> voParamMap = new HashMap<>();

	synchronized public static BaseTypeValidateModel getVoValidateModel(Parameter param) {
		BaseTypeValidateModel vo = voParamMap.get(param);
		if (null != vo) {
			return vo;
		}
		vo = new BaseTypeValidateModel(param);
		voParamMap.put(param, vo);
		return vo;
	}

	/**
	 * 取得java官方基本类型 绑定的校验数据
	 * 
	 * @param pobj 参数
	 * @return JWebMVCValidateVo 校验数据
	 */
	private static JWebMVCValidateVo getJWebMVCValidateVo(Parameter pobj) {
		return Tools.getRegex(pobj.getName(), pobj.getAnnotation(BindRegex.class), null);
	}

	/**
	 * vo对象从方法参数中来
	 * 
	 * @param param 参数
	 */
	private BaseTypeValidateModel(Parameter param) {
//		this.voClass = param.getType();
		this.validateVo = getJWebMVCValidateVo(param);
	}

	/**
	 * 校验
	 * 
	 * @param ovalue 值
	 * @return ValidateResult 结果
	 */
	public ValidateResult chec(Object ovalue) {
		ValidateResult vd = new ValidateResult();
		
		if(null==this.validateVo) {
			return vd;
		}
		try {
			if (null == ovalue) {// 当值为null时，只有一种情况可以通过——规则允许为null
				if (validateVo.alloyNull) {// 如果允许字段为null,表示正常通过
					return vd;
				} else {
					vd.addError(validateVo.keyName, validateVo.errorMessage);
					return vd;
				}
			}
			// 找不到关联的正则表达式，同时本身也没有正则表达式，还设置为非null的：那么，只要非null就放行
			if (validateVo.regex.isEmpty()) {
				return vd;
			}
			// 执行正则表达式校验。如果不通过，直接返回
			if (!ovalue.toString().matches(validateVo.regex)) {
				vd.addError(validateVo.keyName, validateVo.errorMessage);
				return vd;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return vd;
	}
}
