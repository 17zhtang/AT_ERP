package com.haoyun.automationtesting.framework.aop;

import java.io.File;
import java.io.FileFilter;
import java.io.FileInputStream;
import java.io.IOException;

//import mx4j.tools.config.DefaultConfigurationBuilder.Object;






import java.net.JarURLConnection;
import java.net.URL;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.aspectj.lang.ProceedingJoinPoint;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.haoyun.automationtesting.framework.ExcelOperate;
import com.haoyun.automationtesting.framework.TestCase;
import com.haoyun.automationtesting.framework.log;


/**
 * 
 * @author Administrator
 *
 */
public class LogAdviceSecond {

	//private final Logger logger = LoggerFactory.getLogger(LogAdvice.class);
	public static String pwd() throws IOException{
		String filePath="";
		File directory = new File(".");
		return filePath = directory.getCanonicalPath() + "/resource/case.xlsx";
	}
	public Object log(ProceedingJoinPoint pjp) throws Throwable {
		long start = System.currentTimeMillis();
		Object result = null;
		// String method = String.format("%s.%s",
		// pjp.getTarget().getClass().getName(), pjp.getSignature().getName());

		// System.out.println(classcase[classcase.length-1]);//获取当前类名

		if (!pwd().endsWith(".xlsx")) {
			System.out.println("文件不是excel类型");
		}
		FileInputStream fis = null;
		Workbook wb = null;
		try {
			fis = new FileInputStream(pwd());
		} catch (Exception e) {
			e.printStackTrace();
		}
        try {
				// 2007版本的excel，用.xlsx结尾
				wb = new XSSFWorkbook(fis);// 得到工作簿
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
        
        try {
		// System.out.println("一共有多少给sheet页："+wb.getNumberOfSheets());
		for (int sheetindex = 1; sheetindex < wb.getNumberOfSheets(); sheetindex++) {// 获取sheet页个数
			String sheetname = wb.getSheetName(sheetindex);
			// System.out.println("sheetname::"+sheetindex+":"+sheetname);

			java.lang.String method = pjp.getTarget().getClass().getName();

			String[] classcase = method.split("\\.");
			if (!sheetname.trim().isEmpty()) {

				Sheet st = wb.getSheet(sheetname);// 选择执行测试案例的sheet页
				int row = st.getLastRowNum();
				int rowindex=0;
				
				Cell cell = null;
				try{
				for (int i = 1; i <= row + 1; i++) {
					
					
					cell = st.getRow(i).getCell(0);
					
					//System.out.println("cell:"+cell);
					if (cell == null) {
						break;
					}else{
						rowindex=rowindex+1;
					}
				}
				} catch (Exception e) {
					// TODO Auto-generated catch block
					//e.printStackTrace();
				}
				//System.out.println("rowindex:"+rowindex);
				for (int i = 5; i <= rowindex + 1; i++) {
					//System.out.println(i+"次"+ExcelOperate.getexcel(i, 4, sheetname).trim());
					if (ExcelOperate.getexcel(i, 4, sheetname).trim().contains("未通过")) {// 执行excel中状态为未通过的用例
						
						//System.out.println(i+"次"+classcase[classcase.length - 1]);
						if (ExcelOperate.getexcel(i, 1, sheetname).equals(
								classcase[classcase.length - 1])) {// 判断excel中的类名和执行的类名是否一致
							//logger.info("开始执行：{}...", method);
							log.logInfo("由于该用例执行未通过，再次执行用例："+ method);
							result = pjp.proceed();
							
							log.logInfo("再次执行完成'"+ method+"'所用时间:"+
									(System.currentTimeMillis() - start) / 1000 +"秒");
							//logger.info("执行完成{}所用时间[{}]秒.", method,
							//		(System.currentTimeMillis() - start) / 1000);
							break;
						}

					} else {
						//logger.info("用例{}不执行",ExcelOperate.getexcel(i, 2, sheetname));

					}
				}
			}
		}
        } catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
	}
	
	
	
	public static void addMongoToBeanFactory(ApplicationContext context,
			Class<?> beanClass, String beanName, String host, int port,
			String database) {
		ConfigurableApplicationContext applicationContext = (ConfigurableApplicationContext) context;
		DefaultListableBeanFactory beanFactory = (DefaultListableBeanFactory) applicationContext
				.getBeanFactory();
		if (!beanFactory.containsBean(beanName)) {
			BeanDefinitionBuilder beanDefinitionBuilder = BeanDefinitionBuilder
					.rootBeanDefinition(beanClass);
			// beanDefinitionBuilder.addPropertyValue("host", host);
			// beanDefinitionBuilder.addPropertyValue("port", port);
			// beanDefinitionBuilder.addPropertyValue("database", database);
			// beanDefinitionBuilder.setInitMethodName("init");
			// beanDefinitionBuilder.setDestroyMethodName("destroy");
			beanFactory.registerBeanDefinition(beanName,
					beanDefinitionBuilder.getBeanDefinition());
			// System.out.println("Add to bean container.");
		}
	}

	/**
	 * 从包package中获取所有的Class
	 * 
	 * @param pack
	 * @return
	 */
	public static Set<Class<?>> getClasses(String pack) {

		// 第一个class类的集合
		Set<Class<?>> classes = new LinkedHashSet<Class<?>>();
		// 是否循环迭代
		boolean recursive = true;
		// 获取包的名字 并进行替换
		String packageName = pack;
		String packageDirName = packageName.replace('.', '/');
		// 定义一个枚举的集合 并进行循环来处理这个目录下的things
		Enumeration<URL> dirs;
		try {
			dirs = Thread.currentThread().getContextClassLoader()
					.getResources(packageDirName);
			// 循环迭代下去
			while (dirs.hasMoreElements()) {
				// 获取下一个元素
				URL url = dirs.nextElement();
				// 得到协议的名称
				String protocol = url.getProtocol();
				// 如果是以文件的形式保存在服务器上
				if ("file".equals(protocol)) {
					// System.err.println("file类型的扫描");
					// 获取包的物理路径
					String filePath = URLDecoder.decode(url.getFile(), "UTF-8");
					// 以文件的方式扫描整个包下的文件 并添加到集合中
					findAndAddClassesInPackageByFile(packageName, filePath,
							recursive, classes);
				} else if ("jar".equals(protocol)) {
					// 如果是jar包文件
					// 定义一个JarFile
					// System.err.println("jar类型的扫描");
					JarFile jar;
					try {
						// 获取jar
						jar = ((JarURLConnection) url.openConnection())
								.getJarFile();
						// 从此jar包 得到一个枚举类
						Enumeration<JarEntry> entries = jar.entries();
						// 同样的进行循环迭代
						while (entries.hasMoreElements()) {
							// 获取jar里的一个实体 可以是目录 和一些jar包里的其他文件 如META-INF等文件
							JarEntry entry = entries.nextElement();
							String name = entry.getName();
							// 如果是以/开头的
							if (name.charAt(0) == '/') {
								// 获取后面的字符串
								name = name.substring(1);
							}
							// 如果前半部分和定义的包名相同
							if (name.startsWith(packageDirName)) {
								int idx = name.lastIndexOf('/');
								// 如果以"/"结尾 是一个包
								if (idx != -1) {
									// 获取包名 把"/"替换成"."
									packageName = name.substring(0, idx)
											.replace('/', '.');
								}
								// 如果可以迭代下去 并且是一个包
								if ((idx != -1) || recursive) {
									// 如果是一个.class文件 而且不是目录
									if (name.endsWith(".class")
											&& !entry.isDirectory()) {
										// 去掉后面的".class" 获取真正的类名
										String className = name.substring(
												packageName.length() + 1,
												name.length() - 6);
										try {
											// 添加到classes
											classes.add(Class
													.forName(packageName + '.'
															+ className));
										} catch (ClassNotFoundException e) {
											// log
											// .error("添加用户自定义视图类错误
											// 找不到此类的.class文件");
											e.printStackTrace();
										}
									}
								}
							}
						}
					} catch (IOException e) {
						// log.error("在扫描用户定义视图时从jar包获取文件出错");
						e.printStackTrace();
					}
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}

		return classes;
	}

	/**
	 * 以文件的形式来获取包下的所有Class
	 * 
	 * @param packageName
	 * @param packagePath
	 * @param recursive
	 * @param classes
	 */
	public static void findAndAddClassesInPackageByFile(String packageName,
			String packagePath, final boolean recursive, Set<Class<?>> classes) {
		// 获取此包的目录 建立一个File
		File dir = new File(packagePath);
		// 如果不存在或者 也不是目录就直接返回
		if (!dir.exists() || !dir.isDirectory()) {
			// log.warn("用户定义包名 " + packageName + " 下没有任何文件");
			return;
		}
		// 如果存在 就获取包下的所有文件 包括目录
		File[] dirfiles = dir.listFiles(new FileFilter() {
			// 自定义过滤规则 如果可以循环(包含子目录) 或则是以.class结尾的文件(编译好的java类文件)
			@Override
			public boolean accept(File file) {
				return (recursive && file.isDirectory())
						|| (file.getName().endsWith(".class"));
			}
		});
		// 循环所有文件
		for (File file : dirfiles) {
			// 如果是目录 则继续扫描
			if (file.isDirectory()) {
				findAndAddClassesInPackageByFile(
						packageName + "." + file.getName(),
						file.getAbsolutePath(), recursive, classes);
			} else {
				// 如果是java类文件 去掉后面的.class 只留下类名
				String className = file.getName().substring(0,
						file.getName().length() - 6);
				try {
					// 添加到集合中去
					// classes.add(Class.forName(packageName + '.' +
					// className));
					// 经过回复同学的提醒，这里用forName有一些不好，会触发static方法，没有使用classLoader的load干净
					classes.add(Thread.currentThread().getContextClassLoader()
							.loadClass(packageName + '.' + className));
				} catch (ClassNotFoundException e) {
					// log.error("添加用户自定义视图类错误 找不到此类的.class文件");
					e.printStackTrace();
				}
			}
		}
	}

	//主程序调用入口
	 public static void mainfile() {

	ApplicationContext context = new ClassPathXmlApplicationContext(
			"springSecond.xml");
	List<TestCase> testCases = new ArrayList<>();
	// Instrumentation inst = JavaAgent.getInstrumentation();
	// Class<?>[] classes = inst.getAllLoadedClasses();

	Set<Class<?>> classes = getClasses("com.haoyun.automationtesting.test");
	for (Class<?> clazz : classes) {
		// if (clazz.getPackage() != null &&
		// "appium.frame.testcase".equals(clazz.getPackage().getName())) {
		// System.out.println(clazz.getName());

		if (TestCase.class.isAssignableFrom(clazz) && !clazz.isInterface()) {
			// System.out.println(clazz.getName());
			try {

				addMongoToBeanFactory(context, clazz,
						clazz.getSimpleName(), null, 0, null);
				testCases.add((TestCase) context.getBean(clazz
						.getSimpleName()));
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		// }
	}
	// GenericDao dao = (GenericDao) context.getBean("testDao");
	for (TestCase tc : testCases) {
		// System.out.println(tc.getClass());
		try {
			// tc.test(dao);//执行带数据库参数的方法
			tc.testcase();// 不带参数

		} catch (Throwable e) {
			try {
				 //log.logError(tc.getClass().getName() + "失败，" +
				 //e.getMessage());
				 //log.logWarn(tc.getClass().getName() + "失败，"
				 //+ e.getMessage());
				 //} catch (InterruptedException e1) {
				
				// e1.printStackTrace();
				// }
				//e.getMessage();
			} catch (Exception e1) {

				e1.printStackTrace();
			}
		}
	}
}
}
