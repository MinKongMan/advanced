# 로그 추적기
```
애플리케이션 개발 후 시간이 지나면 모니터링과 운영이 중요해지는 단계가 옴(여기서 이 단계를 수행할 예정)
최근에 병목현상 발생 -> 장애가 생김
어떤 부분에서 병목이, 에러가 생기는지 로그를 통해 문제를 해결하려고 함
```

# 요구사항



# V0
  - 기본적인 클래스 생성 및 기능 구현

# V1
  - Controller, Service, Repository에 구현한 기능 적용
  - 하나의 작업에 대해 아직 traceId가 일치하지 않음

# V2
  - V1에서 traceId가 일치하지 않는 오류 해결
  >> Controller에서 traceId를 생성하고 인자를 넘겨주는 방식으로 구현
  >> 만약 Service로 바로 실행되는 작업에 대해 오류가 발생함
