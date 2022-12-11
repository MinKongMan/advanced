package hello.advanced.trace.logTrace;

import hello.advanced.trace.traceId;
import hello.advanced.trace.traceStatus;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ThreadLocalLogTrace implements logTrace{
    private static final String START_PREFIX = "-->";
    private static final String COMPLETE_PREFIX = "-->";
    private static final String EX_PREFIX = "<X-";

    private ThreadLocal<traceId> traceIdHolder = new ThreadLocal<>(); //기존 파라미터를 넘겼다면 이제는 여기에 보관, (동시성 이슈 발생)
    // traceId를 어딘가에서 들고 있어야 함

    @Override
    public traceStatus begin(String message) {
        syncTraceId();
        traceId traceId= traceIdHolder.get();
        Long startTimeMs = System.currentTimeMillis();

        log.info("[{}] {}{}", traceId.getId(), addSpace(START_PREFIX, traceId.getLevel()), message);

        // 로그 출력
        return new traceStatus(traceId, startTimeMs, message);
    }
    
    private void syncTraceId(){
        traceId traceId = traceIdHolder.get();
        if(traceId == null){
            traceIdHolder.set(new traceId());
        }
        else{
            traceIdHolder.set(traceId.createNextId());
        }
    }

    @Override
    public void end(traceStatus traceStatus) {
        complete(traceStatus, null);
    }

    @Override
    public void exception(traceStatus traceStatus, Exception e) {
        complete(traceStatus, e);
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

        releaseTraceId();
    }

    private void releaseTraceId() {
        traceId traceId = traceIdHolder.get();
        if(traceId.isFirstLevel()){
            traceIdHolder.remove(); //destroy
            // 해당 스레드 로컬에서 보관한 데이터만 다 지워줌
        }
        else{
            traceIdHolder.set(traceId.createPrevId());
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
