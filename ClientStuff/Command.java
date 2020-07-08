package ClientStuff;

import java.io.Serializable;

public class Command<T, P> implements Serializable {
    final static long serialVersionUID = 3L;
    private String commandName;
    private T argument;
    private P additional;
    private User user;

    public Command(String name, T argument){
        this.commandName = name;
        this.argument = argument;
    }

    public Command(){
        this.commandName = null;
        this.argument = null;
        this.additional = null;
    }

    public String getCommandName() {
        return commandName;
    }

    public void setCommandName(String commandName) {
        this.commandName = commandName;
    }

    public T getArgument() {
        return argument;
    }

    public void setArgument(T argument) {
        this.argument = argument;
    }

    public P getAdditional() {
        return additional;
    }

    public void setAdditional(P additional) {
        this.additional = (P) additional;
    }

    public void setEverything(String commandName, T argument){
        setCommandName(commandName);
        setArgument(argument);
    }

    public void setUser(User user) {
        this.user = user;
    }
}
