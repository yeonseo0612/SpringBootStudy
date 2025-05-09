
package com.example.todo.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.todo.dto.TestRequestBodyDTO;
import com.example.todo.model.ResponseDTO;

@RestController
//데이터를 반환하는 컨트롤러로 사용
//JSON이나 XML형식의 데이터를 반환한다.
//@Controller, @ResponseBody 두개의 어노테이션의 결합이다.
//@ResponseBody는 메서드의 반환값을 HTTP ResponseBody로 직렬화해 클라이언트에게 전달한다.
@RequestMapping("test") // test주소로 요청이 들어왔을 때 현재 컨트롤러로 들어올 수 있게 해준다.
public class TestController {
	
	@GetMapping("/testGetMapping")//GET으로 요청이 들어왔을 때 요청을 받아서 아래 메서드를 실행해준다.
	public String testController() {
		return "Hello world";
		
	}
	
	@GetMapping("/test2")
	public String testController2() {
		return "Hello world2";
	}
	
//	@GetMapping({"/users", "/users/{id}"})
//	public String getUserById(@PathVariable(value = "id", required = false) Optional<Integer> userId) {
//		return "User ID : " + userId;
//	}
	
	//정규식을 써서 변수 형식을 제한할 수 있다.
	@GetMapping("/users/{userId}/orders/{orderId}")
	public String getOrderByUserAndOrderId(@PathVariable("userId") Long id, @PathVariable("orderId") Long orderId) {
		return "UserId" + id + "OrderId" +  orderId;
	}
	
	@GetMapping({"/users", "/users/{id}"})
	public String getUserById(
	    @PathVariable(value = "id", required = false) Optional<Long> pathUserId,
	    @RequestParam(value = "id", required = false) Long paramUserId) {
	    
	    Long finalId = pathUserId.orElse(paramUserId != null ? paramUserId : -1L);
	    return "User ID : " + finalId;
	}
	
	@GetMapping("/search")
	public String search(@RequestParam("query") String query, @RequestParam("page") int page) {
		return "Search query : " + query + ", page : " + page;
	}
	
	@PostMapping("/submitForm")
    public String submitForm(@RequestParam("name") String name, 
                             @RequestParam("email") String email) {
        return "Form submitted: Name = " + name + ", Email = " + email;
    }
	

	@GetMapping("/testRequestBody")
    //@RequestBody TestRequestBodyDTO testRequestBodyDTO
    //RequestBody로 날아오는 JSON을 TestRequestBody객체로 변환해서 가져와라
    //날아오는 JSON의 형식은 TestRequestBody의 형태와 같아야 할 것이다.
	public String testControllerRequestBody(@RequestBody TestRequestBodyDTO testRequestBodyDTO) {
		return "Hello World! ID"+testRequestBodyDTO.getId() + " Message : " + testRequestBodyDTO.getMessage();
	}
	
//	@GetMapping("/testResponseBody")
//	public ResponseDTO<String> testControllerResponseBody(){
//		List<String> list = Arrays.asList("하나","둘","셋");
//		ResponseDTO<String> response = ResponseDTO.<String>builder().data(list).build();
//		return response;
//	}
	
	@GetMapping("/testResponseEntity")
	public ResponseEntity<ResponseDTO<String>> testResponseEntity(){
		List<String> list = new ArrayList<>();
		list.add("Hello World! I'm ResponseEntity. And you got 400");
		ResponseDTO<String> response = ResponseDTO.<String>builder().data(list).build();
		return ResponseEntity.badRequest().body(response);
	}
	//ResponseDTO를 반환하는 것과 비교했을 때 큰 차이는 없지만 단지 헤더와 HTTPStatus를 조작할 수 있다는 점이 다르다.

}
