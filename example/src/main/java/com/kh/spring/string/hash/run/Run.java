package com.kh.spring.string.hash.run;

import java.util.HashSet;
import java.util.Set;

import com.kh.spring.string.controller.Student;

public class Run {
	
	public static void main(String[] args) {
		
		//HashSet
		//Value값만 저장, index가 존재하지 않음
		// 순서보장 x, 중복 x
		
		//문자열만 담을 수 있는 HashSet
		
		HashSet<String> set = new HashSet();
		
		//List<String> list = new ArrayList();
			
		//개발자 편의를 위해서
	
		// 1.실수방지용! 혹여나 의도하지 않은 타입의 값이 들어가지 않도록
		// 2.개발의 편의성을 위해서! => 강제형변환을 할 필요가 없음!
		// 저장 순서 보장 X, 중복 X => Set의 특징(HashSet의 특징 아님)
		
		Set<Student> students = new HashSet();
		
		//이유? 동일객체로 판단하지 않기 때문!
		//HashSet : 요소가 새롭게 추가 될 때마다 equals()와 hashcode()로 비교 후 
		// 			둘 다 결과가 true일 경우 동일 객체 판단
		
		//equals() : 현재 객체의 주소값을 비교해서 결과를 반환: boolean
		//hashCode(): 현재 객체의 주소값을 해싱 알고리즘을 돌려서 10진수로 반환: int
		

			
			
		}
		
	}
	
