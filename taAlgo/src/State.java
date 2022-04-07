import java.util.ArrayList;

public class State{
    String location;
    Boolean isAccepting;
    State Parent;
    
    ArrayList<Transition> transitions;

    public State() {
    }
    
    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public Boolean getIsAccepting() {
        return isAccepting;
    }

    public void setIsAccepting(Boolean isAccepting) {
        this.isAccepting = isAccepting;
    }

    public State getParent() {
        return Parent;
    }

    public void setParent(State parent) {
        Parent = parent;
    }

    public ArrayList<Transition> getTransitions() {
        return transitions;
    }

    public void setTransitions(ArrayList<Transition> transitions) {
        this.transitions = transitions;
    }

    
    
}