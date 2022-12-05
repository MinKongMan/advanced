package hello.advanced.app.v1;

import hello.advanced.trace.helloTrace.helloTraceV1;
import hello.advanced.trace.traceStatus;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
/*
    얘도 Component 어노테이션이 있어서 Component 스캔의 대상이 된다.

 */
@RequiredArgsConstructor
public class OrderServiceV1 {

    private final OrderRepositoryV1 orderRepositoryV1;
    private final helloTraceV1 trace;

    public void orderItem(String item){

        traceStatus status = null;
        try{
            status  =  trace.begin("OrderServiceV1.orderItem()"); // 이 부분은 수작업으로 어떤 부분이 실행되는지 알려줘야 함
            orderRepositoryV1.save(item);
            trace.end(status);
        }
        catch (Exception e){
            trace.exception(status, e);
            throw e; // 예외를 던져줘야 함
        }
    }
}
