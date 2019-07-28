package weixinkeji.vip.jweb.validate.tdata.controller;

import weixinkeji.vip.jweb.validate.ann.BindRegex;
import weixinkeji.vip.jweb.validate.ann.RegexAttribute;
import weixinkeji.vip.jweb.validate.tdata.entity.Hello;

public class THelloController {

	public String sayHello(
			// 表示对Hello属性id，改写校验属性，alloyNull,让其允许为null
			// 同时，表达参数时，key=hello,如果不指定接收参数的key,默认key=变量名obj
			@RegexAttribute(alloyNull = { "id=true" }) Hello obj, @BindRegex(regex = "[0-9]+") String wo) {

		return null;
	}

	public String sayHello2(
			// 表示对Hello属性id，改写校验属性，alloyNull,让其允许为null
			// 同时，表达参数时，key=hello,如果不指定接收参数的key,默认key=变量名obj
			@RegexAttribute(alloyNull = { "id=true" }) Hello obj, @BindRegex(regex = "[0-9]+") String wo) {

		return null;
	}
}
