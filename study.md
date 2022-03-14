## 2022-03-14

#### 프로젝트 생성
- 프로젝트 생성 관련 옵션
  - 프로젝트 선택
    - Project: Gradle Project
    - Language: Java
    - Spring Boot: 2.6.4
  - Project Metadata
    - Group: Hello
    - Artifact: thymeleaf-basic
    - Name: thymeleaf-basic
    - Package name: hello.thymeleaf
    - Packaging: Jar
    - Java: 11
  - Dependencies
    - Spring Web
    - Lombok
    - Thymeleaf
- 동작 확인
  - 기본 메인 클래스 실행
  - http://localhost:8080 을 호출하였을 시 Whitelabel Error Page가 뜨면 됨
- 세팅
  - 최근 IntelliJ 버전은 Gradle을 통해서 실행 하는 것이 기본 설정이다. 이렇게 하면 실행속도가 느리다. 다음과 같이 변경하면 자바로 바로 실행해서 실행속도가 더 빠르다
    - File -> Setting -> Build, Execution, Deployment -> Build Tools -> Gradle
      - Build and run using: Gradle -> IntelliJ IDEA
      - Run tests using: Gradle -> IntelliJ IDEA
  - 롬복 적용
    - File -> Setting -> plugin -> lombok 검색 실행 (재시작)
    - File -> Setting -> Annotation Processors 검색 -> Enable annotation processing 체크 (재시작)
- Postman 설치
  - https://www.postman.com/downloads

#### 타임리프 소개
- 타임리프 특징
  - 서버 사이트 HTML 렌더링 (SSR)
    - 타임리프는 백엔드 서버에서 HTML을 동적으로 렌더링 하는 용도로 사용된다.
  - 네츄럴 템플릿
    - 타임리프는 순수 HTML을 최대한 유지하는 특징이 있다.
    - 타임리프로 작성한 파일은 HTML을 유지하기 때문에 웹 브라우저에서 파일을 직접 열어도 내용을 확인할 수 있고, 서버를 통해 뷰 템플릿을 거치면 동적으로 변경된 결과를 확인할 수 있다.
    - JSP를 포함한 다른 뷰 템플릿들은 해당 파일을 열면, 예를 들어서 JSP 파일 자체를 그대로 웹 브라우저에서 열어보면 JSP 소스코드와 HTML이 뒤죽박죽 섞여서 웹 브라우저에서 정상적인 HTML 결과를 확인할 수 없다. 오직 서버를 통해서 HSP가 렌더링 되고 HTML 응답 결과를 받아야 화면을 확인할 수 있다. 반면에 타임리프로 작성된 파일은 해당 파일을 그대로 웹 브라우저에서 열어도 정상적인 HTML 결과를 확인할 수 있다. 물론 이 경우 동적으로 결과가 렌더링 되지는 않는다. 하지만 HTML 마크업 결과가 어떻게 되는지 파일만 열어도 바로 확인할 수 있다.
    - 이렇게 순수 HTML을 그대로 유지하면서 뷰 템플릿도 사용할 수 있는 타임리프의 특징을 네츄럴 템플릿(natural templates)이라 한다.
  - 스프링 통합 지원
    - 타임리프는 스프링과 자연스럽게 통합되고, 스프링의 다양한 기능을 편리하게 사용할 수 있게 지원한다.
- 타임리프 기본 기능
  - 타임이프 사용 선언
    - `<html xmlns:th="http://www.thymeleaf.org">`
  - 기본 표현식
    - 간단 표현식
    - 리터럴
    - 문자 연산
    - 산술 연산
    - 불린 연산
    - 비교와 동등
    - 조건 연산
    - 특별한 토큰

#### 텍스트 - text, utext
- 텍스트
  - text
  - utext
- Escape
- Unescape
- `th:inline="none"`: 타임리프는 `[[...]]`를 해석하기 때문에, 화면에 `[[...]]`글자를 보여줄 수 없다. 이 태그 안에서는 타임리프가 해석하지 말라는 옵션이다.
> 주의!  
> 실제 서비스를 개발하다 보면 escape를 사용하지 않아서 HTML이 정상 렌더링 되지 않는 수 많은 문제가 발생한다. escape를 기본으로 하고, 꼭 필요한 때만 unescape를 사용하자

#### 변수 - SpringEL
- 변수 표현식 : `${...}`
- SpringEL 다양한 표현식 사용
  - Object
    - `user.username`: user의 username을 프로퍼티 접근 -> `user.getUsername()`
    - `user['username']`: 위와 같음 -> `user.getUsername()`
    - `user.getUsername()`: user의 `getUsername()`을 직접 호출
  - List
    - `users[0].username`: List에서 첫 번째 회원을 찾고 username 프로퍼티 접근 -> `list.get(0).getUsername()`
    - `users[0]['username']`: 위와 같음
    - `users[0].getUsername()`: List에서 첫 번째 회원을 찾고 메서드 직접 호출
  - Map
    - `userMap['userA'].username`: Map에서 userA를 찾고, username 프로퍼티 접근 -> `map.get("userA").getUsername()`
    - `userMap['userA']['username']`: 위와 같음
    - `userMap['userA'].getUsername()`: Map에서 userA를 찾고 메서드 직접 호출
- 지역 변수 선언
  - `th:with`를 사용하면 지역 변수를 선언해서 사용할 수 있다. 지역 변수는 선언한 태그 안에서만 사용할 수 있다.