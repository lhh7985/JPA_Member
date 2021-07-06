# JPA_Member
JPA Member CRUD

1. ORM 이란?
정의
ORM( Object Relational Mapping, 객체 관계 매핑)
- 객체와 관계형 DB의 데이터를 자동으로 매핑해주는 것

- 장점
1. **객체 지향적인 코드로 인해 직관적이고, 비지니스 로직에 더 집중할 수 있다.**
    - SQL 쿼리가 아닌 메소드로 데이터를 조작할 수 있어 객체 모델 프로그래밍에 집중할 수 있다.
    - SQL 쿼리를 직접 작성하지 않아 중복되는 부수적인 작업이 줄어든다.

2. **재사용 및 유지보수의 편리성 증가**
    - ORM은 독립적으로 작성되어 있고, 해당 객체들을 재활용 가능

3. **DBMS에 대한 종속성이 줄어든다.**
    - DBMS를 교체하는 작업인 경우 비교적 적은 리스크와 적은 시간이 소요
    - 객체간의 관계를 바탕으로 SQL을 자동으로 생성하기 때문에 RDBMS의 데이터 구조와 Java의 객체지향 모델 사이의 간격을 좁힐 수 있다.


- 단점
1. 완벽한 ORM으로만 서비스를 구현하기 어렵다.
    - 프로젝트의 복잡성이 커지면 난이도도 올라간다.
    - 잘못 구현된 경우 속도 저하 및 일관성이 깨질 수 있다.
2. 프로시저가 많은 시스템에선 ORM의 객체 지향적인 장점을 활용하기 어렵다.





## H2 데이터베이스 In Memory Database로 사용하기

1. 의존성 추가
 ```    
        <dependency>
            <groupId>com.h2database</groupId>
            <artifactId>h2</artifactId>
            <scope>runtime</scope>
        </dependency>
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-data-jpa</artifactId>
        </dependency>
 ```
 
 2. application properties 설정
 ```
 spring.h2.console.enabled=true
spring.datasource.url=jdbc:h2:mem:demo
#JPA 설정
spring.jpa.hibernate.ddl-auto=create
spring.jpa.properties.hibernate.show_sql = true
#spring.jpa.properties.hibernate.format_sql = true
#spring.jpa.properties.hibernate.use_sql_comments = true
```

   url을 지정하지 않으면 console창에 자동으로 생성되는 주소를 사용하여 접속하면 됩니다.
 
 3. Entity 객체를 만들어 localhost:8080/h2-console에 접속하여 확인
 ```
 @Data
 @Entity
 public class MemberVo {

    @Id
    private long memberNo;

    private String id;
    private String name;
 }
 
 ```
 Entity 설정 후 @Id가 Primary key로 설정된다. 
 In Memory Database에 MemberVo라는 테이블이 생성된다. 
 
 
 ===============================
 # PostgreSQL
 
 1. 의존성 
 ```
        <dependency>
			<groupId>org.postgresql</groupId>
			<artifactId>postgresql</artifactId>
		</dependency>
 ```
 
 2. docker를 활용한 PostgreSQL 다운
 ```
    //Docker로 PostgreSQL DB생성
    docker run -p 5432:5432 -e POSTGRES_PASSWORD=<원하는 비밀번호> -e POSTGRES_USER=<원하는 USERNAME> -e POSTGRES_DB=<원하는 DB명> --name postgres_boot -d postgres
    
    //postgres서버 실행
    docker start <DB명>
    
    //bash 명령어로 바꾸기
    docker exec -i -t postgres_boot bash
    
 ```
 
 3. Runner class 생성 후 Test
 ```
 @Component
public class PgSQLRunner implements ApplicationRunner {

    @Autowired
    DataSource datasource;

    @Autowired
    JdbcTemplate jdbcTemplate;
    
    @Override
    public void run(ApplicationArguments args) throws Exception {
        try(Connection connection = datasource.getConnection()) {
            System.out.println(datasource.getClass());
            System.out.println(connection.getMetaData().getURL());
            System.out.println(connection.getMetaData().getUserName());

            //create user table
            Statement statement = connection.createStatement();
            String sql = "create table users(ID INTEGER NOT NULL, name VARCHAR(255), PRIMARY KEY(id))";
            statement.executeUpdate(sql);
        }
        jdbcTemplate.execute("insert into users values(1, 'lhh')");
    }
}
```
결과
<img src="https://user-images.githubusercontent.com/59523147/124559068-3fa1af00-de76-11eb-9a47-2a52f1813dad.png">

 
 
