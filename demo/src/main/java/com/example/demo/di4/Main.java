package com.example.demo.di4;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
//ctrl + shift + o
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import java.lang.reflect.Field;
import com.google.common.reflect.ClassPath;

// 컴포넌트 스캐닝
// 클래스 앞에 @Component 어노테이션을 붙이고
// 패지키에서 컴포넌트 어노테이션이 붙어있는 클래스를 찾아서 객체로 만들어 맵에 저장하는 기법

@Component class Car{
	@Autowired
	Engine engine;
	
	@Autowired
	Wheel wheel;
	
	@Override
	public String toString() {
		return "Car [engine ="+ engine + ", wheel=" + wheel + "]";
	}
};
@Component class SportCar extends Car{};
@Component class Truck extends Car{};
@Component class Engine{};
@Component class Wheel{};

class AppContext{
	Map map;
	
	// 생성자
	public AppContext() {
		map = new HashMap();
		doComponentScan();
		doAutowried();
	}
	
	//map에 저장된 객체의 객체변수중 @Autowired가 붙어있으면 타입에 맞는 객체를 찾아서 연결한다.
	private void doAutowried() {
		try {
			//맵에 들어있는 객체를 하나씩 꺼내서
			for(Object obj: map. values()) {
				//getClass()로 클래스 정보를 얻어오고
				//getDeclaredFields()로 해당 클래스에 있는 필드의 정보들을 배열
				for(Field fld : obj.getClass().getDeclaredFields()) {
					
						//필드에 오토와이어드 어노테이션이 붙어있는지 확인하고
					 if(fld.getAnnotation(Autowired.class) != null) {
						//그 필드에 맞는 객체가 있으면 세팅을 해라
						 fld.set(obj, getBean(fld.getType()));
					 }
					
				}
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
	
	
	
	//패키지에 클래스를 모두 순회하면서 @Component어노테이션이 붙은 클래스를 Map에 객체로 등록을 한다.
	//@ComponentScan이 아래 함수의 역할을 한다.
	private void doComponentScan() {
		try {
			// 1. 패키지 내에 클래스 목록을 가져온다.
			// 2. 반복문으로 클래스를 하나씩 읽어와서 컴포넌트가 붙어있는지 확인한다.
			// 3. @Component가 붙어있으면 객체를 생성해서 map에 저장
			
			// ClassLoader
			// JVM 내부에서 클래스와 리소스(설정 파일, 이미지 등등)를 로딩하는 역할을 하는 객체
			// AppContext 클래스를 로딩한 ClassLoader객체를 반환
			ClassLoader classloader = AppContext.class.getClassLoader();
			
			// ClassPath는 구아바 라이브러리에서 제공하는 클래스
			// 클래스 경로상의 모든 클래스를 탐색하고 사용할 수 있게 도와준다.
			ClassPath classpath = ClassPath.from(classloader);
			
			// 지정한 패키지 내의 최상위 클래스를 가져온다.
			Set<ClassPath.ClassInfo> set = classpath.getTopLevelClasses("com.example.demo.di4");
			
			for(ClassPath.ClassInfo classInfo: set) {
				// ClassInfo 객체를 실제 Class로 변환한다.
				Class clazz = classInfo.load();
				
				// 해당 클래스에 @Component 어노테이션이 있는지 확인한다.
				// @Component는 스프링에서 자주 사용되는 어노테이션으로 빈으로 등록하려는 클래스에 부여한다.
				Component component = (Component)clazz.getAnnotation(Component.class);
				
				// @Component 어노테이션이 null이 아니면
				// 즉, 해당 클래스가 아너테이션으로 지정되어있다면 아래의 로직을 실행해라
				if(component != null) {
					// 클래스의 이름의 첫글자를 소문자로 반환하여 id로 사용할것이다
					// 변환하는 이유는 스프링에서 빈을 생성할 때 기본적으로 클래스 이름의 첫 글자를 소문자로 사용하기 때문이다.
					// getSimpleName() : 패키지 없이 클래스 이름만 반환
					String id = StringUtils.uncapitalize(classInfo.getSimpleName());
					
					// newInstance() : 기본 생성자를 호출하여 객체를 생성한다.
					map.put(id, clazz.newInstance());
				}
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
	// 클래스의 이름으로 찾기
	// 메서드 오버로딩
	Object getBean(String key) {
		return map.get(key);
	}
	
	//클래스의 타입으로 찾기
	//클래스의 정보 자체를 매개변수로 받는다.
	Object getBean(Class clazz) {
		//map.values() : map의 value들을 컬렉
		for(Object obj : map.values()) {
			//객체 obj가 clazz에 속하니? obj instanceof clazz
			if(clazz.isInstance(obj)) {
				return obj;
			}
			
		}
		return null;
	}
	
}
public class Main {

	public static void main(String[] args) {
		AppContext ac = new AppContext();
		
		//car겍체에 필드로  engine과 wheel을 갖는다.
		Car car = (Car)ac.getBean("car");
		System.out.println("car :" + car);
		
		
		
		Engine engine = (Engine)ac.getBean("engine");
		System.out.println("engine :" + engine);
		
		
		 Wheel wheel = (Wheel)ac.getBean("wheel");
		 
		 
		 //원래 자바에서는 필드에 직접 객체를 넣어줘야한다.(의존성 주입)
		 //car.engine = engine;
		 //car.wheel = wheel;
		 
		 System.out.println(car);
		 
	}
	
	
	
}

