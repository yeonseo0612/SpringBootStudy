package com.example.demo.di2;

import java.util.HashMap;
import java.util.Map;

class Car{};
class SportCar extends Car{};
class Truck extends Car{};
class Engine{};

// 겍체 컨테이너로 객체를 저장
// 객체 컨테이너 : 객체들을 저장하는 공간
// 클래스 안에 map으로 객체를 저장

class AppContext{
	Map map; // 객체 저장소
	public AppContext() {
		// 객체를 메모리에 미리 올려놓고 시작을 한다.
		map = new HashMap();
		map.put("car", new SportCar());
		map.put("engine", new Engine());
	}
	
	Object getBean(String key) {
		return map.get(key);
	}
	
	
}

public class Main1 {
	public static void main(String[] args) {
		AppContext ac = new AppContext();
		
		// 필요할때마다 호출해서 사용한다.
		Car car = (Car)ac.getBean("car");
		System.out.println(car);
		
		Engine engine = (Engine)ac.getBean("engine");
		System.out.println(engine);
		
		
		
	}
}
