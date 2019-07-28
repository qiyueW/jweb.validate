package weixinkeji.vip.jweb.validate.ann;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.ElementType.PARAMETER;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

/**
 * 绑定 正则表达式
 * 
 * 给属性（普通类型的属性）或参数（普通类型的参数） 绑定 正则表达式
 * 
 * @author wangchunzi
 *
 */
@Retention(RUNTIME)
@Target({ FIELD, PARAMETER })
public @interface BindRegex {
	
	/**
	 * 返回校验结果时，显示的字段名。默认与参数名或属性名一样
	 * 
	 * @return
	 */
	public String keyName() default "";

	/**
	 * 正则表达式
	 * 
	 * @return String
	 */
	public String value() default "";// 与regex是等价关系

	/**
	 * 正则表达式
	 * 
	 * @return String
	 */
	public String regex() default "";// 等价上述

	/**
	 * 校验不通过时的错误信息
	 * 
	 * @return String
	 */
	public String error() default "";

	/**
	 * 占位符
	 * 
	 * @return String[]
	 */
	public String[] placeholder() default "";

	/**
	 * 是否允许null（默认false,不允许）
	 * 
	 * @return boolean
	 */
	public boolean alloyNull() default false;

	/**
	 * 当表达式是引用类型时，可以通过此值，改写引用的表达式是否允许为null
	 * 
	 * 当值为RegexNullType.unknow时，只采用引用的
	 * 
	 * @return
	 */
	public RegexNullType refAlloyNull() default RegexNullType.unknow;

}
