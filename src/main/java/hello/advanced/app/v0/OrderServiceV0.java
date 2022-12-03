package hello.advanced.app.v0;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
/*
    얘도 Component 어노테이션이 있어서 Component 스캔의 대상이 된다.

 */
@RequiredArgsConstructor
public class OrderServiceV0 {

    private final OrderRepositoryV0 orderRepositoryV0;

    public void orderItem(String item){
        orderRepositoryV0.save(item);
    }
}
