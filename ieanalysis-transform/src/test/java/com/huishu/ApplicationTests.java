package com.huishu;

import com.huishu.app.Application;
import com.huishu.utils.FileUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Properties;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class)
public class ApplicationTests {

	@Test
	public void contextLoads() {
	}

	@Test
	public void testReadPropertiesFile(){
		Properties properties = FileUtils.getProperties("units.properties");
		String value = properties.getProperty("RMB.CNY");
		String value2 = properties.getProperty("USD.CNY");
		String value3 = properties.getProperty("JPY.CNY");
		String value4 = properties.getProperty("EUR.CNY");
		System.out.println("RMB.CNY="+value);
		System.out.println("USD.CNY="+value2);
		System.out.println("JPY.CNY="+value3);
		System.out.println("EUR.CNY="+value4);
	}

}
