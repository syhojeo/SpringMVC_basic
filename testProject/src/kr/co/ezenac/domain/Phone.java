package kr.co.ezenac.domain;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.RequestScope;

import kr.co.ezenac.database.MapperInterface;

@Component
@RequestScope
public class Phone {
	
	@Autowired
	MapperInterface mapper;
	
	private String name;
	private String phone;
	private String userId;
	
	public Phone() {
		
	}
	
	//phone 번호로 userId가 있는지 확인하기
	public DataStatus checkPhoneUser(String phone) {
		String userId = mapper.checkUserPhone(phone);
		DataStatus result;
		
		if (userId == null) {
			result = DataStatus.Not_Exist;
		}
		else {
			result = DataStatus.Exist;
		}
		
		return result;
	}
	
	//userid 리턴
//	public String checkPhoneUser(String name, String phone) {
//		
//	}
}
