package hello.advanced.app.v0;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
/*
    @Controller + @Repository 합쳐진거
    @Controller 내부에 @Component가 있어 Component 스캔의 대상이 되어 자동으로 Spring Bean에 자동 등록 됨
 */
@RequiredArgsConstructor
public class OrderControllerV0 {
    private final OrderServiceV0 orderServiceV0;

    @GetMapping("/v0/request")
    public String request(String itemId){
        orderServiceV0.orderItem(itemId);
        return "OK";
    }
}
