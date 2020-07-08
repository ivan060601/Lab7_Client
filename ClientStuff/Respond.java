package ClientStuff;

import java.io.Serializable;

public class Respond implements Serializable {
    private String msg;

    public Respond(String msg) {
        this.msg = msg;
    }

    public Respond(){

    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
