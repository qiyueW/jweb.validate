package weixinkeji.vip.jweb.validate.model;

import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.util.ArrayList;

import junit.framework.TestCase;
import weixinkeji.vip.jweb.validate.init.InitValidateDataCenter;
import weixinkeji.vip.jweb.validate.tdata.controller.THelloController;
import weixinkeji.vip.jweb.validate.tdata.entity.Hello;

public class VoValidateModelTest extends TestCase {

	protected void setUp() throws Exception {
		InitValidateDataCenter._0_init(new ArrayList<>());
	}

	public Hello getHellog() {
		Hello obj = new Hello();
		obj.setAge(1111111111);
		obj.setHelloId("1234567890");
		obj.setHelloName("name");
		obj.setOrderId(2);
		obj.setNum2(1234567890);
		return obj;
	}

	// 直接传入一个vo 对象
	public void testCheck() {
		VoValidateModel model = VoValidateModel.getVoValidateModel(Hello.class);
		System.out.println(model.check(getHellog(), false));
	}

	// 从方法参数中传入一个vo
	public void testCheck2() {
		Method[] ms = THelloController.class.getDeclaredMethods();
		VoValidateModel model;
		BaseTypeValidateModel bModel;
		
		for (Method m : ms) {
			for (Parameter pobj : m.getParameters()) {
				if (pobj.getType() == Hello.class) {
					model = VoValidateModel.getVoValidateModel(pobj);
					System.out.println(model.check(getHellog(), false));
				}else {
					bModel=BaseTypeValidateModel.getVoValidateModel(pobj);
					System.out.println("abc:"+bModel.chec("abc"));
				}
			}
		}
	}

}
