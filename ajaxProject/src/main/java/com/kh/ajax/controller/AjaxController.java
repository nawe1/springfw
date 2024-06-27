package com.kh.ajax.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.gson.Gson;
import com.kh.ajax.model.vo.Menu;


@Controller
public class AjaxController {
	
	/**
	 * 1. HTTPServletReponse객체로 응답데이터를 응답하기(Stream을 이용한 방식)
	 * @throws IOException 
	 * 
	 */
	
	//아날로그한 방식이다!!!
	/*
	@GetMapping("ajax1.do")
	public void calSum(String menu,int amount,HttpServletResponse response) throws IOException {
		
		//System.out.println("사용자가 입력한 메뉴 : " + menu);
		//System.out.println("사용자가 입력한 수량 : " + amount);
		
		int price =0;
		switch(menu) {
		case "알밥" : price = 1000; break;
		case "돈까스" :price = 30000; break;
		case "서브웨이" :price = 2000; break;
		case "김치찜" :price =4000; break;
		case "막국수" :price = 6000; break;
		}
		
		price *= amount;
		
		//System.out.println(price);
		
		// 서비스 다녀와서 요청처리가 다 끝났다.
		// 요청한 페이지에 반환할 데이터를 완성해냈다!!
		
		//한글이 있으면 꺠질 수 있어서 데이터 형식을 지정해 줘야한다.
		response.setContentType("text/html; charset=UTF-8");
		
		//출력
		response.getWriter().print(price);
		
	}
	*/
	/**
	 * 2.응답할 데이터를 문자열로 반환
	 * 		=> HttpServletResponse를 사용하지 않는 방법
	 * 			=> String 반환하면 포워딩 => 응답뷰의 경로로 인식을해서 뷰 리졸버로 전달
	 * 
	 * 따라서 스프링을 사용해서 응답데이터를 반환 할때는
	 * 
	 * => MessageConverter로 이동하게끔 해주어야함! ==> @ResponseBody 에노테이션!
	 */
	
	@ResponseBody
	@GetMapping(value="ajax1.do",produces="text/html; charset=UTF-8")
	public String calSum(String menu,int amount){
		
		int price =0;
		switch(menu) {
		case "알밥" : price = 1000; break;
		case "돈까스" :price = 30000; break;
		case "서브웨이" :price = 2000; break;
		case "김치찜" :price =4000; break;
		case "막국수" :price = 6000; break;
		}
		
		price *= amount;
		
		return String.valueOf(price) + "한글";
		
	}
	
	@GetMapping("responseData")
	public void test(HttpServletResponse response) throws IOException{
		
		PrintWriter writer = response.getWriter();
	}
	
	@ResponseBody
	@GetMapping(value="ajax2.do",produces="application/json; charset=UTF-8")
	public String selectMenu(int  menuNumber) {
		
		// 요청처리를 잘했다는 가정하에~~ 데이터 응답
		
		/**
		 * DB에 존재하는 메뉴 테입ㄹ
		 * ---------------------------------------------
		 * |메뉴번호 | 메뉴이름 | 가격 | 재료 | 
		 * ---------------------------------------------
		 * |   1   |  순두부  | 9500 | 순두부|
		 * ---------------------------------------------
		 * 
		 * JSON(JAVA SCRIPT Object Notation)
		 * 
		 * []
		 * {}
		 * 
		 * 
		 */
		
		Menu menu = new Menu(1,"순두부",9500,"순두부");
		/*
		StringBuilder sb = new StringBuilder();
		
		sb.append("{");
		sb.append("menuNumber : " + "'"+menu.getMenuNumber() + "',");
		sb.append("menuName : " +"'"+ menu.getMenuName()+ "',");
		sb.append("price: " +"'"+ menu.getPrice()+ "',");
		sb.append("material: " +"'"+ menu.getMaterial()+ "',");
		sb.append("}");
		
		
		
		
		return sb.toString();
	}
	*/
		JSONObject jobj = new JSONObject();
		jobj.put("menuNumber",  menu.getMenuNumber());
		jobj.put("menuName",  menu.getMenuName());
		jobj.put("price",  menu.getPrice());
		jobj.put("material",  menu.getMaterial());
		
		return jobj.toJSONString();
	}
	
	@ResponseBody
	@GetMapping(value="ajax3.do",produces="application/json; charset=UTF-8")
	public Menu ajax3Method(int menuNumber) {
		Menu menu = new Menu(menuNumber, "순두부찌개",9500,"순두부");
		
		
		// DB에서 SELECT해옴
		return menu;
	}

	@ResponseBody
	@GetMapping(value="find.do",produces="application/json; charset=UTF-8")
	public String findAll() {
		
		List<Menu> menus = new ArrayList();
		menus.add(new Menu(1,"순두부찌개",9500,"순두부"));
		menus.add(new Menu(2,"김치찌개",12000,"김치"));
		menus.add(new Menu(3,"된장찌개",8000,"된장"));
		
		//selectList 조회결과가 menus에 담겨있음
		
		/**
		 * [
		 * 	{
		 *   menuNumber:1,
		 *   menuName: 순두부찌개
		 *   price = 9500,
		 *   material = 순두부
		 *  }
		 * 
		 *  {
		 *   menuNumber : 2,
		 *   menuName: 김치찌개
		 *   price : 12000
		 *   material : 김치 
		 *  }
		 *
		 *  {
		 *   menuNumber: 3,
		 *   menuName: 된장찌개
		 *   price: 8000,
		 *   material : 된장
		 *  }
		 * ]
		 * 
		 */
		
		/* 몇개를 쓸지 모르기 때문에 이렇게 쓰면 안되고 반복문을 쓰자!
		JSONObject jobj1 = new JSONObject();
		jobj1.put("menuNumber",  menus.get(0).getMenuNumber());
		jobj1.put("menuName",  menus.get(0).getMenuName());
		jobj1.put("price",  menus.get(0).getPrice());
		jobj1.put("material",  menus.get(0).getMaterial());
		
		JSONObject jobj2 = new JSONObject();
		jobj2.put("menuNumber",  menus.get(1).getMenuNumber());
		jobj2.put("menuName",  menus.get(1).getMenuName());
		jobj2.put("price",  menus.get(1).getPrice());
		jobj2.put("material",  menus.get(1).getMaterial());
		
		
		JSONObject jobj3 = new JSONObject();
		jobj3.put("menuNumber",  menus.get(2).getMenuNumber());
		jobj3.put("menuName",  menus.get(2).getMenuName());
		jobj3.put("price",  menus.get(2).getPrice());
		jobj3.put("material",  menus.get(2).getMaterial());
		*/
		JSONArray jarr = new JSONArray();
		
		/*
		jarr.add(jobj1);
		jarr.add(jobj2);
		jarr.add(jobj3);
		*/
		
		/* 이게 귀찮으니 Gson을 사용하는것이다!
		for(int i =0; i < menu.size(); i++) {
			JSONObject jobj = new JSONObject();
			jobj.put("menuNumber",menus.get(i).getMenuNumber());
			jobj.put("menuName",menus.get(i).getMenuName());
			jobj.put("price",menus.get(i).getPrice());
			jobj.put("metarial",menus.get(i).getMaterial());
			
			jarr.add(jobj);
			*/
		
		
		return new Gson().toJson(menus);
	
	}
}
