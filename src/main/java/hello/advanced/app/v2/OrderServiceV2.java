package hello.advanced.app.v2;

import hello.advanced.trace.helloTrace.helloTraceV1;
import hello.advanced.trace.helloTrace.helloTraceV2;
import hello.advanced.trace.traceId;
import hello.advanced.trace.traceStatus;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
/*
    얘도 Component 어노테이션이 있어서 Component 스캔의 대상이 된다.

 */
@RequiredArgsConstructor
public class OrderServiceV2 {

    private final OrderRepositoryV2 orderRepositoryV1;
    private final helloTraceV2 trace;

    public void orderItem(traceId traceId, String item){

        traceStatus status = null;
        try{
            status  =  trace.beginSync(traceId, "OrderServiceV1.orderItem()"); // 이 부분은 수작업으로 어떤 부분이 실행되는지 알려줘야 함
            orderRepositoryV1.save(status.getTraceId(), item);
            trace.end(status);
        }
        catch (Exception e){
            trace.exception(status, e);
            throw e; // 예외를 던져줘야 함
        }
    }
}
