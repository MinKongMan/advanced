package hello.advanced.trace;

public class traceStatus {
    private traceId traceId;
    private Long startTimeMs;
    private String message;

    public hello.advanced.trace.traceId getTraceId() {
        return traceId;
    }

    public Long getStartTimeMs() {
        return startTimeMs;
    }

    public String getMessage() {
        return message;
    }

    public traceStatus(hello.advanced.trace.traceId traceId, Long startTimeMs, String message) {
        this.traceId = traceId;
        this.startTimeMs = startTimeMs;
        this.message = message;
    }
}
