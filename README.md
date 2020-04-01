# SNS Proejct
Radis를 통한 cache 사용으로 RDBMS를 사용함에도 빠른 검색이 가능한 SNS 프로젝트입니다.


## 파일구조
```
├─src
│  ├─main
│  │  ├─java
│  │  │  └─com
│  │  │      └─sns
│  │  │          └─prj
│  │  │              │  CodePressoSbApplication.java
│  │  │              │
│  │  │              ├─config
│  │  │              │      RedisConfiguration.java
│  │  │              │
│  │  │              ├─controller
│  │  │              │  │  HomeController.java
│  │  │              │  │  PostController.java
│  │  │              │  │  PostControllerApi.java
│  │  │              │  │  UserController.java
│  │  │              │  │  UserControllerApi.java
│  │  │              │  │
│  │  │              │  ├─aop
│  │  │              │  │      AdviceByController.java
│  │  │              │  │
│  │  │              │  └─dto
│  │  │              │          ResultDto.java
│  │  │              │
│  │  │              ├─domain
│  │  │              │  │  FeedVO.java
│  │  │              │  │  FollowVO.java
│  │  │              │  │  PostVO.java
│  │  │              │  │  TokenVO.java
│  │  │              │  │  UserVO.java
│  │  │              │  │
│  │  │              │  └─PK
│  │  │              │          FeedPK.java
│  │  │              │          FollowPK.java
│  │  │              │
│  │  │              ├─repository
│  │  │              │  │  FeedDAO.java
│  │  │              │  │  FollowDAO.java
│  │  │              │  │  PostDAO.java
│  │  │              │  │  TokenDAO.java
│  │  │              │  │  UserDAO.java
│  │  │              │  │
│  │  │              │  └─redis
│  │  │              │          PostRedis.java
│  │  │              │
│  │  │              ├─service
│  │  │              │      FeedService.java
│  │  │              │      FeedServiceImpl.java
│  │  │              │      FollowService.java
│  │  │              │      FollowServiceImpl.java
│  │  │              │      PostService.java
│  │  │              │      PostServiceImpl.java
│  │  │              │      TokenService.java
│  │  │              │      TokenServiceImpl.java
│  │  │              │      UserService.java
│  │  │              │      UserServiceImpl.java
│  │  │              │
│  │  │              └─util
│  │  │                      TokenUtil.java
│  │  │                      UserUtil.java
│  │  │
│  │  └─resources
│  │      │  application.properties
│  │      │
│  │      ├─static
│  │      │  └─js
│  │      │          detail.js
│  │      │          index.js
│  │      │          login.js
│  │      │          signup.js
│  │      │
│  │      └─templates
│  │              detail.ftl
│  │              footer.ftl
│  │              header.ftl
│  │              index.ftl
│  │              login.ftl
│  │              signup.ftl
```

## 개발환경
- Spring Boot 2.2.2.RELEASE
- JPA
- Redis 4.0
- MySQL 5.7
- Freemarker

## 프로젝트 구성
- Spring Boot를 이용한 web server입니다.
- 전체적으로 Rest API 형식으로 구성되어 있습니다.
- ORM인 JPA를 통한 CURD가 진행됩니다.
- RDBMS인 MySQL을 main DB로 사용했습니다.
- Redis를 통해 cache 되는 정보는 빠른 조회가 가능합니다.
- Applicaion.properties에 MySQL user와 Redis user 정보가 저장되어 있어야 합니다.
