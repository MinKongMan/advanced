package hello.advanced.app.v2;

import hello.advanced.trace.helloTrace.helloTraceV1;
import hello.advanced.trace.helloTrace.helloTraceV2;
import hello.advanced.trace.traceId;
import hello.advanced.trace.traceStatus;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository // Component 스캔의 대상
@RequiredArgsConstructor
public class OrderRepositoryV2 {
    private final helloTraceV2 trace;
    public void save(traceId traceId, String itemId){

        traceStatus status = null;
        try{
            status  =  trace.beginSync(traceId, "OrderRepositoryV1.save()"); // 이 부분은 수작업으로 어떤 부분이 실행되는지 알려줘야 함

            // 저장 로직
            if(itemId.equals("ex")){
                throw new IllegalStateException("예외 발생");
            }

            sleep(1000);

            trace.end(status);
        }
        catch (Exception e){
            trace.exception(status, e);
            throw e; // 예외를 던져줘야 함
        }

    }

    private void sleep(int millis) {
        try {
            Thread.sleep(millis);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
