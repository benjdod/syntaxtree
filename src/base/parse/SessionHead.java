package base.parse;

import base.tree.struct.Branch;
import base.tree.struct.Head;

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

    public SessionHead getSessionParent() {
        return _parent;
    }

    public void setSessionParent(SessionHead parent) {
        _parent = parent;
    }

    public void setState(SessionState state) {
        _state = state;
    }

    public SessionState getState() {
        return _state;
    }
}