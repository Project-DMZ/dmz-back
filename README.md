## 개발환경

* Language : java17
* Framework : spring boot 3.1.3
* DB : TBD
* License
    * TBD
    * TBD

## 패키지 구조

### api

* api는 핵심 비즈니스 로직을 관리합니다.
* 도메인 단위로 패키지를 구분합니다.
* 아래의 형태로 패지키는 구성됩니다.
    * controller : 도메인별 컨트롤러 클래스들을 관리합니다.
    * service : 도메인별 핵심 비즈니스 로직들을 관리합니다.
    * dto : Controller <-> Service 레이어간의 dto 객체들을 관리합니다.
    * enums : 열거형 타입의 클래스들을 관리합니다.
    * exception : 도메인 별 발생하는 예외들을 관리합니다.
    * repository : querydsl 등 DB 접근 코드들을 관리합니다.
    * domain : 테이블과 매칭되는 엔티티들을 관리합니다.
* 각 패키지의 기본 구성은 위의 형태이며 사용되지 않은 패키지의 경우 제외 하였습니다.
* mapper : dto <-> entity 변환을 위해 MapStruct 라이브러리를 사용 하였습니다.

### global

* global은 핵심 비즈니스 로직에 종속적이지 않고, 공통으로 사용되는 로직들을 관리합니다.
* advice는 글로벌 예외처리를 관리합니다.

* paging은 Page로 반환 되어야 할 객체들의 응답 파라미터를 바인딩하여 커스텀 하였습니다.
* RequestDto 객체에 utils 패키지의 Page를 상속받습니다.
* Page 객체의 order는 내림차 / 오름차 순을 의미하며, sort는 기준으로 할 컬럼을 의미합니다.

```java
// dto
@Getter
@Setter
public class TempSearch extends Page {
	private String parameter;
}

```

* Response는 응답 객체들을 관리합니다.
  * list() 메소드를 사용하면 Paging.of() 메소드를 호출하여 페이징 처리를 하도록 구현했습니다.

```java
    // service
    public Response<?> TempList(TempSearch search){
    
        PageRequest pageable=search.getPageable(search);
    
        return Response.list(tempRepository.getAdminList(pageable,search));
  }	
``` 
