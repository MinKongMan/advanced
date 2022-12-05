package hello.advanced.trace.helloTrace;

import hello.advanced.trace.traceId;
import hello.advanced.trace.traceStatus;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
// 싱글톤으로 사용하기 위해 스프링 빈으로 등록
public class helloTraceV1 {
    // 실제 로그를 출력 및 시간 출력

    private static final String START_PREFIX = "-->";
    private static final String COMPLETE_PREFIX = "-->";
    private static final String EX_PREFIX = "<X-";

    public traceStatus begin(String message){ // 시작 로그 출력
        traceId traceId = new traceId();
        Long startTimeMs = System.currentTimeMillis();

        log.info("[{}] {}{}", traceId.getId(), addSpace(START_PREFIX, traceId.getLevel()), message);

        // 로그 출력
        return new traceStatus(traceId, startTimeMs, message);
    }



    public void end(traceStatus status){ // 로그 종료 출력
        complete(status, null);
    }

    public void exception(traceStatus status, Exception e){
        // 로그를 에러 상황에서 호출
        // 화살표 <X- ~~ 출력
        // 실행시간 뿐만 아니라 예외 정보도 출력
        complete(status, e);
    }

    private void complete(traceStatus status, Exception e){
        Long stopTimeMs = System.currentTimeMillis();
        long resultTimeMs = stopTimeMs - status.getStartTimeMs();
        traceId traceId = status.getTraceId();

        if(e==null){
            log.info("[{}] {}{} time={}ms", traceId.getId(), addSpace(COMPLETE_PREFIX, traceId.getLevel()), status.getMessage(), resultTimeMs);
        }
        else{
            log.info("[{}] {}{} time={}ms, ex={}", traceId.getId(), addSpace(EX_PREFIX, traceId.getLevel()), status.getMessage(), resultTimeMs,e.toString());
        }
    }


    //level = 0
    //level = 1 |-->
    //level = 2 |   |-->

    //level = 2 ex |   |<X-
    //level = 1 |<X-
    private static String addSpace(String prefix, int level) {
        StringBuilder sb = new StringBuilder();
        for(int i = 0; i<level; i++){
            sb.append((i==level-1)?"|"+prefix:"|   ");
        }
        return sb.toString();
    }
}
