# Spring Boot Developer

## 📖 프로젝트 소개

Spring Boot를 활용한 백엔드 개발자 학습 및 실무 프로젝트입니다. 현대적인 웹 애플리케이션 개발에 필요한 핵심 기술들을 다룹니다.

## 🛠️ 기술 스택

### Backend
- **Java 17+** - 최신 Java 버전 활용
- **Spring Boot 3.x** - 프레임워크 코어
- **Spring Security** - 인증/인가 처리
- **Spring Data JPA** - 데이터베이스 접근 계층
- **Spring Web** - RESTful API 개발

### Database
- **H2 Database** - 개발/테스트 환경
- **MySQL/PostgreSQL** - 운영 환경

### Build & DevOps
- **Maven/Gradle** - 빌드 도구
- **Docker** - 컨테이너화
- **JUnit 5** - 단위 테스트

## 📋 주요 기능

- ✅ RESTful API 설계 및 구현
- ✅ JWT 기반 인증 시스템
- ✅ 데이터베이스 연동 및 CRUD 작업
- ✅ 예외 처리 및 로깅
- ✅ API 문서화 (Swagger/OpenAPI)
- ✅ 단위 테스트 및 통합 테스트
- ✅ 보안 설정 및 CORS 처리

## 🚀 시작하기

### 필수 요구사항

- Java 17 이상
- Maven 3.6+ 또는 Gradle 7.0+
- IDE (IntelliJ IDEA, Eclipse, VS Code)

### 설치 및 실행

1. **저장소 클론**
   ```bash
   git clone https://github.com/peryteil/springboot--developer.git
   cd springboot--developer
   ```

2. **의존성 설치**
   ```bash
   # Maven 사용 시
   mvn clean install
   
   # Gradle 사용 시
   ./gradlew build
   ```

3. **애플리케이션 실행**
   ```bash
   # Maven 사용 시
   mvn spring-boot:run
   
   # Gradle 사용 시
   ./gradlew bootRun
   ```

4. **접속 확인**
   - 애플리케이션: http://localhost:8080
   - API 문서: http://localhost:8080/swagger-ui.html

## 📁 프로젝트 구조

```
src/
├── main/
│   ├── java/
│   │   └── com/
│   │       └── example/
│   │           └── springboot/
│   │               ├── SpringBootApplication.java
│   │               ├── controller/       # REST 컨트롤러
│   │               ├── service/          # 비즈니스 로직
│   │               ├── repository/       # 데이터 접근 계층
│   │               ├── entity/           # JPA 엔티티
│   │               ├── dto/              # 데이터 전송 객체
│   │               ├── config/           # 설정 파일
│   │               └── security/         # 보안 관련
│   └── resources/
│       ├── application.yml               # 애플리케이션 설정
│       ├── static/                       # 정적 리소스
│       └── templates/                    # 뷰 템플릿
└── test/
    └── java/                             # 테스트 코드
```

## 🔧 주요 설정

### application.yml 예시

```yaml
server:
  port: 8080

spring:
  datasource:
    url: jdbc:h2:mem:testdb
    driver-class-name: org.h2.Driver
    username: sa
    password: password
  
  jpa:
    hibernate:
      ddl-auto: create-drop
    show-sql: true
    properties:
      hibernate:
        format_sql: true
  
  h2:
    console:
      enabled: true
      path: /h2-console

logging:
  level:
    com.example.springboot: DEBUG
    org.springframework.security: DEBUG
```

## 📚 학습 내용

### 1. Spring Boot 기초
- 자동 구성 (Auto Configuration)
- 스타터 의존성 (Starter Dependencies)
- 애플리케이션 설정 관리

### 2. 웹 개발
- RESTful API 설계
- HTTP 메소드 활용
- 요청/응답 처리
- 데이터 바인딩 및 검증

### 3. 데이터베이스 연동
- JPA/Hibernate 기본 개념
- 엔티티 매핑
- 레포지토리 패턴
- 쿼리 최적화

### 4. 보안
- Spring Security 설정
- JWT 토큰 인증
- 권한 기반 접근 제어
- 비밀번호 암호화

### 5. 테스트
- 단위 테스트 작성
- 통합 테스트
- 모킹(Mocking)
- 테스트 전략

## 🧪 테스트 실행

```bash
# 모든 테스트 실행
mvn test

# 특정 테스트 클래스 실행
mvn test -Dtest=UserServiceTest

# 테스트 커버리지 확인
mvn jacoco:report
```

## 📄 API 문서

애플리케이션 실행 후 다음 URL에서 API 문서를 확인할 수 있습니다:

- **Swagger UI**: http://localhost:8080/swagger-ui.html
- **OpenAPI JSON**: http://localhost:8080/v3/api-docs

## 🐳 Docker 실행

```bash
# 이미지 빌드
docker build -t springboot-developer .

# 컨테이너 실행
docker run -p 8080:8080 springboot-developer
```

## 📈 성능 및 모니터링

- **Actuator** 엔드포인트를 통한 애플리케이션 모니터링
- **Micrometer**를 활용한 메트릭 수집
- **로그 레벨 설정** 및 구조화된 로깅

## 🤝 기여하기

1. 이 저장소를 Fork 합니다
2. 기능 브랜치를 생성합니다 (`git checkout -b feature/amazing-feature`)
3. 변경사항을 커밋합니다 (`git commit -m 'Add some amazing feature'`)
4. 브랜치에 푸시합니다 (`git push origin feature/amazing-feature`)
5. Pull Request를 생성합니다

## 📝 라이센스

이 프로젝트는 MIT 라이센스를 따릅니다. 자세한 내용은 [LICENSE](LICENSE) 파일을 참고하세요.

## 📞 문의 및 지원

- 이슈: GitHub Issues 페이지를 이용해 주세요
- 이메일: developer@example.com
- 블로그: https://blog.example.com

## 🎯 향후 계획

- [ ] 마이크로서비스 아키텍처 적용
- [ ] 캐싱 전략 구현 (Redis)
- [ ] 메시지 큐 연동 (RabbitMQ)
- [ ] 클라우드 배포 (AWS/Azure)
- [ ] 모니터링 대시보드 구축

---

**Happy Coding! 🚀**
