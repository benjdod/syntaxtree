package base.parse;

import base.syntree.Branch;
import base.syntree.Head;

public class SessionHead extends Head {

    public enum SessionState {LEFT,OP,RIGHT,EVALOP,EVALDIGIT};

    private SessionHead _parent;
    private SessionState _state;

    public SessionHead(SessionHead parent) {
        super(new Branch());
        _parent = parent;
        _state = SessionState.LEFT;
    }

    public SessionHead() {
        this(null);
    }

    public SessionHead getParent() {
        return _parent;
    }

    public void setState(SessionState state) {
        _state = state;
    }

    public SessionState getState() {
        return _state;
    }
}