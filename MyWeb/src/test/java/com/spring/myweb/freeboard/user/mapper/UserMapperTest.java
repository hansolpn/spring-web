package com.spring.myweb.freeboard.user.mapper;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.spring.myweb.user.entity.User;
import com.spring.myweb.user.mapper.IUserMapper;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(locations = {"file:src/main/webapp/WEB-INF/spring/root-context.xml"})
public class UserMapperTest {

	@Autowired
	private IUserMapper mapper;
	
	@Test
    @DisplayName("회원 가입을 진행했을 때 회원가입이 성공해야 한다.")
    void registTest() {
//		mapper.join(User.builder()
//						.userId("test1")
//						.userPw("test1")
//						.userName("test1")
//						.userPhone1("010")
//						.userPhone2("12345678")
//						.userEmail1("test1")
//						.userEmail2("naver.com")
//						.addrBasic("서울특별시")
//						.addrDetail("테스트동")
//						.addrZipNum("10101")
//						.build());
		mapper.join(User.builder()
				.userId("kim1234")
				.userPw("kkk1111")
				.userName("김철수")
				.userEmail1("kim1234")
				.userEmail2("naver.com")
				.build());
    }
    
    @Test
    @DisplayName("존재하는 회원 아이디를 조회했을 시 1이 리턴되어야 한다.")
    void checkIdTest() {
        int res = mapper.idCheck("test1");
        System.out.println(res);
        
        assertEquals(1, res);
    }
    
    @Test
    @DisplayName("존재하는 회원 아이디를 입력 했을 시 그 회원의 비밀번호가 리턴되어야 한다.")
    void loginTest() {
        String res = mapper.login("test1");
        System.out.println(res);
        
        assertEquals("test1", res);
    }
    
    @Test
    @DisplayName("존재하지 않는 회원의 아이디를 전달하면 null이 올 것이다.")
    void getInfoTest() {
        //User res = mapper.getInfo("test1");
        User res = mapper.getInfo("test2");
        System.out.println(res);
        
        assertNull(res);
    }
    
    @Test
    @DisplayName("id를 제외한 회원의 정보를 수정할 수 있어야 한다.")
    void updateTest() {
    	User user = mapper.getInfo("test1");
    	user.setUserName("변경된 이름");
    	user.setUserPhone1("010-9876-5432");
    	user.setAddrDetail("변경된 주소");
    	
        mapper.updateUser(user);
    }
	
	
}
