package com.example.btp_app;

public class TANode {
    TANode next;
    String Action;
    String guard;
    int clock;
    int currentid;
    int nextid;
    boolean isAccepting;

    public TANode() {
    }

    public boolean isAccepting() {
        return isAccepting;
    }

    public void setAccepting(boolean isAccepting) {
        this.isAccepting = isAccepting;
    }

    public TANode(int currentid) {
        this.currentid = currentid;
    }

    public TANode(TANode next, String action, String guard, int clock, int currentid, int nextid) {
        this.next = next;
        Action = action;
        this.guard = guard;
        this.clock = clock;
        this.currentid = currentid;
        this.nextid = nextid;
    }

    public TANode getNext() {
        return next;
    }

    public void setNext(TANode next) {
        this.next = next;
    }

    public String getAction() {
        return Action;
    }

    public void setAction(String action) {
        Action = action;
    }

    public String getGuard() {
        return guard;
    }

    public void setGuard(String guard) {
        this.guard = guard;
    }

    public int getClock() {
        return clock;
    }

    public void setClock(int clock) {
        this.clock = clock;
    }

    public int getCurrentid() {
        return currentid;
    }

    public void setCurrentid(int currentid) {
        this.currentid = currentid;
    }

    public int getNextid() {
        return nextid;
    }

    public void setNextid(int nextid) {
        this.nextid = nextid;
    }

    public void printNode(TANode node){
        String s="";
        s+="Node id: "+node.getCurrentid()+"\n";
        s+="isAcceptingState : "+node.isAccepting()+"\n";
        s+="Action :"+node.getAction()+"\n";
        s+="Guard :"+node.getGuard()+"\n";
        s+="Clock :"+node.getClock()+"\n";
        s+="NextState Id : "+node.getNextid()+"\n";
        System.out.println(s);
    }
}

