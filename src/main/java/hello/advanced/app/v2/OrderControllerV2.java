package hello.advanced.app.v2;

import hello.advanced.trace.helloTrace.helloTraceV1;
import hello.advanced.trace.helloTrace.helloTraceV2;
import hello.advanced.trace.traceStatus;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
/*
    @Controller + @Repository 합쳐진거
    @Controller 내부에 @Component가 있어 Component 스캔의 대상이 되어 자동으로 Spring Bean에 자동 등록 됨
 */
@RequiredArgsConstructor
public class OrderControllerV2 {
    private final OrderServiceV2 orderServiceV0;
    private final helloTraceV2 helloTrace;

    @GetMapping("/v2/request")
    public String request(String itemId){

        traceStatus status = null;
        try{
            status  =  helloTrace.begin("OrderController.request()"); // 이 부분은 수작업으로 어떤 부분이 실행되는지 알려줘야 함
            orderServiceV0.orderItem(status.getTraceId(), itemId);
            helloTrace.end(status);
            return "ok";
        }
        catch (Exception e){
            helloTrace.exception(status, e);

            throw e; // 예외를 던져줘야 함
        }
    }
}
