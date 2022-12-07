package hello.advanced.trace.logTrace;

import hello.advanced.trace.traceStatus;

public interface logTrace {
    // 로그 추적기를 위한 최소한의 기능인 begin, end, exception을 정의
    traceStatus begin(String message);
    void end(traceStatus traceStatus);
    void exception(traceStatus traceStatus, Exception e);

}
