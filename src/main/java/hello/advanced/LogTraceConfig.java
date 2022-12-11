package hello.advanced;

import hello.advanced.trace.logTrace.FieldLogTrace;
import hello.advanced.trace.logTrace.ThreadLocalLogTrace;
import hello.advanced.trace.logTrace.logTrace;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

// 스프링 빈 수동으로 등록하는 방법
@Configuration
// @Configuration 내부에 @Component가 있어 Component 스캔 대상이 된다.
public class LogTraceConfig {
    @Bean
    public logTrace logTrace(){
        return new ThreadLocalLogTrace();
    }
}
