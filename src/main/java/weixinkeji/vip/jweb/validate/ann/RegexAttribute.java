package weixinkeji.vip.jweb.validate.ann;

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
@Target({ PARAMETER })
public @interface RegexAttribute {

	/**
	 * 设置校验规则——是否允许为null<br>
	 * 用途：流行设计中，有些表是自增主键，增加时不需要，修改时id却是非要不可。那么，我们可以用此定制私有的校验规则 <br>
	 * 属性=是否为null
	 * 
	 * @return String[]
	 */
	public String[] alloyNull();
}
