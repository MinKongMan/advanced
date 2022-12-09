package hello.advanced.app.v3;

import hello.advanced.trace.helloTrace.helloTraceV2;
import hello.advanced.trace.logTrace.logTrace;
import hello.advanced.trace.traceId;
import hello.advanced.trace.traceStatus;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
/*
    얘도 Component 어노테이션이 있어서 Component 스캔의 대상이 된다.

 */
@RequiredArgsConstructor
public class OrderServiceV3 {

    private final OrderRepositoryV3 orderRepositoryV3;
    private final logTrace trace;

    public void orderItem(String item){

        traceStatus status = null;
        try{
            status  =  trace.begin("OrderServiceV1.orderItem()"); // 이 부분은 수작업으로 어떤 부분이 실행되는지 알려줘야 함
            orderRepositoryV3.save(item);
            trace.end(status);
        }
        catch (Exception e){
            trace.exception(status, e);
            throw e; // 예외를 던져줘야 함
        }
    }
}
