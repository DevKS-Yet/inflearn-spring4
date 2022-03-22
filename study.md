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

#### 기본 객체들
- 기본 객체들
  - `${#request}`
  - `${#response}`
  - `${#session}`
  - `${#servletContext}`
  - `${#locale}`
- HTTP 요청 파라미터 접근: `param`
- HTTP 세션 접근: `session`
- 스프링 빈 접근: `@`

#### 유틸리티 객체와 날짜
- 타임리프 유틸리티 객체들
  - `#message`: 메시지, 국제화 처리
  - `#uris`: URI 이스케이프 지원
  - `#dates`: `java.util.Date` 서식 지원
  - `#calendars`: `java.util.Calendar` 서식 지원
  - `#temporals`: 자바8 날짜 서식 지원
  - `#numbers`: 숫자 서식 지원
  - `#strings`: 문자 관련 편의 기능
  - `#objects`: 객체 관련 기능 제공
  - `#bools`: boolean 관련 기능 제공
  - `#arrays`: 배열 고나련 기능 제공
  - `#lists`, `#sets`, `#maps`: 컬렉션 관련 기능 제공
  - `#ids`: 아이디 처리 관련 기능 제공
  - https://www.thymeleaf.org/doc/tutorials/3.0/usingthymeleaf.html#expression-utility-objects

#### URL 링크
- 단순한 URL
  - `@{/hello}` -> `/hello`
- 쿼리 파라미터
  - `@{/hello(param1=${param1}, param2=${param2})}` -> `/hello?param1=data1&param2=data2`
  - `()`에 있는 부분은 쿼리 파라미터로 처리된다.
- 경로 변수
  - `@{/hello/{param1}/{param2}(param1=${param1}, param2=${param2})}` -> `/hello/data1/data2`
  - URL 경로상에 변수가 있으면 `()` 부분은 경로 변수로 처리된다.
- 경로 변수 + 쿼리 파라미터
  - `@{/hello/{param1}(param1=${param1}, param2=${param2})}` -> `/hello/data1?param2=data2`
  - 경로 변수와 쿼리 파라미터를 함께 사용할 수 있다.
- 참고
  - https://www.thymeleaf.org/doc/tutorials/3.0/usingthymeleaf.html#link-urls

#### 리터럴
- 종류
  - 문자: `'hello'`
    - 타임리프에서 문자 리터럴은 항상 `'`(작은 따옴표)로 감싸야 한다.
    - 하지만 공백 없이 쭉 이어진다면 작음 따옴표를 생략 할 수 있다.
      - 룰: `A-Z`, `a-z`, `0-9`, `[]`, `.`, `-`, `_`
  - 숫자: `10`
  - 불린: `true`, `false`
  - null: `null`
- 리터럴 대체
  - `<span th:text="|hello ${data}|">`처럼 `|`로 감싸주는 것으로 사용하면 편리함

#### 연산
- 비교연산: HTML 엔티티를 사용해야 하는 부분을 주의하자
  - `>`(gt; greater than)
  - `<`(lt; less than)
  - `>=`(ge; greater than or equal)
  - `<=`(le; less than or equal)
  - `!`(not)
  - `==`(eq; equal)
  - `!=`(neq, ne; not equal)
- 조건식: 자바의 삼항연산자와 비슷
- Elvis 연산자: 조건식의 편의 버전
- No-Operation: `_`인 경우 마치 타임리프가 실행되지 않는 것처럼 동작한다. 이것을 잘 사용하면 HTML의 내용 그대로 활용할 수 있다. 마지막 예를 보면 `데이터가 없습니다.` 부분이 그대로 출력된다.

#### 속성 값 설정
- 속성 설정
  - `th:*`속성을 지정하면 타임리프는 기존 속성을 `th:*`로 지정한 속성으로 대체한다. 만약 기존 속성이 없다면 새로 만든다.
  - `<input type="text" name="mock" th:name="userA" />` -> 타임리프 렌더링 후 `<input type="text" name="userA" />`
- 속성 추가
  - `th:attrappend`: 속성 값의 뒤에 값을 추가한다.
  - `th:attrprepend`: 속성 값의 앞에 값을 추가한다.
  - `th:classappend`: class 속성에 자연스럽게 추가한다.
- checked 처리
  - HTML에서는 `<input type="checkbox" name="active" checked="false" />` -> 이 경우에도 checked 속성이 있기 때문에 checked 처리가 되어버린다.
  - HTML에서 `checked` 속성은 `checked` 속성의 값과 상관없이 `checked`라는 속성만 있어도 체크가 된다. 이런 부분은 `true`, `false` 값을 주로 사용하는 개발자 입장에서는 불편하다.
  - 타임리프의 `th:checked`는 값이 `false`인 경우 `checked`라는 속성 자체를 제거한다.
  - `<input type="checkbox" name="active" th:checked="false" />` -> `<input type="checkbox" name="active" />`

#### 반복
- 반복 기능
  - `<tr th:each="user : ${users}>`
  - `th:each`는 `List`뿐만 아니라 배열, `java.util.Iterable`, `java.util.Enumeration`을 구현한 모든 객체를 반복에 사용할 수 있습니다. `Map`도 사용할 수 있는데 이 경우 변수에 담기는 값은 `Map.Entry`입니다.
- 반복 상태 유지
  - `<tr th:each="user, userStat : ${users}">`
  - 반복 상태 유지 기능
    - `index`: 0부터 시작하는 값
    - `count`: 1부터 시작하는 값
    - `size`: 전체 사이즈
    - `even`, `odd`: 홀수, 짝수 여부(`boolean`)
    - `first`, `last`: 처음, 마지막 여부(`boolean`)
    - `current`: 현재 객체
  - 따로 `status variable`을 지정하지 않았다면 `iteration variable` 이름 뒤에 자동적으로 `Stat`이 붙어서 만들어준다.

## 2022-03-15

#### 조건부 평가
- if - 만약 조건이 `true`라면 `th:text`을 렌더링하고 `false`라면 렌더링하지 않는다
- unless - `if`의 반대이다. `false`라면 `th:text`을 렌더링하고 `true`라면 렌더링하지 않는다
- switch - `java`의 `switch`문과 동일하다. `*`는 만족하는 조건이 없을 때 사용하는 디폴트이다

#### 주석
- 표준 HTML 주석
  - 자바스크립트의 표준 HTML 주석은 타임리프가 렌더링 하지 않고, 그대로 남겨둔다
- 타임리프 파서 주석
  - 타임리프 파서 주석은 타임리프의 진짜 주석이다. 렌더링에서 주석 부분을 제거한다
- 타임리프 프로토타입 주석
  - HTML 주석에 약간의 구문을 더한다
  - HTML 파일을 웹 브라우저에서 그대로 열어보면 HTML 주석이기 때문에 이 부분이 웹 브라우저가 렌더링하지 않는다
  - 타임리프 렌더링을 거치면 이 부분이 정상 렌더링 됨

#### 블록
- 타임리프의 특성상 HTML 태그 안에 속성으로 기능을 정의해서 사용하는데, 위 예처럼 이렇게 사용하기 애매한 경우에 사용하면 된다. `<th:block>`은 렌더링시 제거된다.

## 2022-03-16

#### 자바스크립트 인라인
- 텍스트 렌더링
  - `var username = [[${user.username}]];`
    - 인라인 사용 전 -> `var username = userA;`
    - 인라인 사용 후 -> `var username = "userA";`
  - 인라인 사용 전을 보면 `userA`가 그대로 출력되기 때문에 원하였던 `String` 타입이 아니면서도 `javascript`가 오류로 처리를 해버린다. `age`같은 경우에는 숫자이기 때문에 정상 렌더링이 된다.
  - 인라인 사용 후를 보면 문자 타입인 경우에는 `""`를 포함해준다. 또한 추가로 자바스크립트에서 문제가 될 수 있는 문자가 포함되어 있다면 이스케이프 처리도 한다. 예) `"` -> `\"`
- 자바스크립트 내추럴 템플릿
  - `var username2 = /*[[${user.username}]]*/ "test username";`
    - 인라인 사용 전 -> `var username2 = /*userA*/ "test username";`
    - 인라인 사용 후 -> `var username2 = "userA";`
  - 인라인 사용 전은 순수하게 해석이 되었기 때문에 내추럴 템플릿 기능이 동작하지 않으면 해당 렌더링 내용이 주석처리 된다.
  - 인라인 사용 후는 주석 부분이 제거되고 정상적으로 `"userA"` 가 적용된다.
- 객체
  - `var user = [[${user}]];`
    - 인라인 사용 전 -> `var user = BasicController.User(username=userA, age=10);`
    - 인라인 사용 후 -> `var user = {"username":"userA","age":10};`
  - 인라인 사용 전은 객체의 `toString()`이 호출된 값이다.
  - 인라인 사용 후는 객체를 JSON으로 변환해준다.
- 자바스크립트 인라인 each
  - `[# th:each="..."]`과 `[/]`를 사용한다.
- 참고
  - https://www.javascript.com/learn/strings
  - https://www.thymeleaf.org/doc/tutorials/3.0/usingthymeleaf.html#javascript-inlining

## 2022-03-22

#### 템플릿 조각
- `th:fragment`를 통해 조각의 이름 및 매개변수를 설정한다.
- `th:(insert/replace)="~{경로/경로/html명 :: 조각이름}"`으로 해당 html에 있는 `th:fragment="조각이름"`을 찾아온다
- `th:insert`는 해당 태그 안에 삽입 시킨다
- `th:replace`는 해당 태그를 템플릿 조각으로 바꾼다
- 기본적으로 `~{...}`을 사용하는 것이 원칙이지만 템플릿 조각을 사용하는 코드가 단순하면 해당 `~{}`을 생략할 수 있다

#### 템플릿 레이아웃1
- 공통적인 `css`, `js` 적용하면서 특정 부분만 바꾸기

#### 템플릿 레이아웃2
- 전체적인 틀을 만들어두고 거기에 데이터를 집어넣기.