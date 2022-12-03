package hello.advanced.trace;

import java.util.UUID;

public class traceId {
    
    private String id;
    // 796bccd9와 같은 id
    private int level;
    // 얼마나 파고들어갔는지 나타내는 레벨


    public traceId() { // 그냥 생성하면 기본으로 생성되도록
        this.id = createId();
        this.level = 0;
    }

    private traceId(String id, int level){
        this.id = id;
        this.level = level;
    }

    public String createId(){
        return UUID.randomUUID().toString().substring(0,8);
    }

    public traceId createNextId(){
        return new traceId(id, level+1);
    }

    public traceId createPrevId(){
        return new traceId(id, level-1);
    }

    public boolean isFirstLevel(){
        return level==0;
    }

    public String getId() {
        return id;
    }

    public int getLevel() {
        return level;
    }
}
