package com.sparta.deliveryapi.models.user;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.*;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class UserTest {

    @Autowired
    private TestRestTemplate restTemplate;
    private HttpHeaders headers;
    private ObjectMapper mapper = new ObjectMapper();
    // 유저객체 초기화

    private UserDto user2 = UserDto.builder()
            .username("홍길동")
            .password("123123")
            .build();
    @BeforeEach
    public void setup() {
        headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
    }

    @Nested
    @DisplayName("회원 등록")
    class RegisterUser{
        @Test
        @Order(1)
        @DisplayName("일반 회원1 등록 ")
        void test1() throws JsonProcessingException{
            //given
            UserDto user1 = UserDto.builder()
                    .username("김주호")
                    .password("123")
                    .build();
            String requestBody = mapper.writeValueAsString(user1);
            HttpEntity<String> request = new HttpEntity<>(requestBody, headers);

            //when
            ResponseEntity<UserDto> response = restTemplate.postForEntity(
                    "/user/signup-user",
                    request,
                    UserDto.class);
            //then
            assertEquals(HttpStatus.OK, response.getStatusCode());
            UserDto userDtoResponse = response.getBody();
            assertNotNull(userDtoResponse);
            assertEquals(userDtoResponse.username, user1.username);

        }

        @Test
        @Order(2)
        @DisplayName("주인1 등록 ")
        void test2() throws JsonProcessingException{
            //given
            UserDto owner1 = UserDto.builder()
                    .username("홍길동")
                    .password("1234")
                    .build();
            String requestBody = mapper.writeValueAsString(owner1);
            HttpEntity<String> request = new HttpEntity<>(requestBody, headers);

            //when
            ResponseEntity<UserDto> response = restTemplate.postForEntity(
                    "/user/signup-owner",
                    request,
                    UserDto.class);
            //then
            assertEquals(HttpStatus.OK, response.getStatusCode());
            UserDto userDtoResponse = response.getBody();
            assertNotNull(userDtoResponse);
            assertEquals(userDtoResponse.username, owner1.username);
        }
    }







    @Getter
    @Setter
    @Builder
    static class UserDto {
        private String username;
        private String password;
        private  UserRoleEnum role;
    }
}
