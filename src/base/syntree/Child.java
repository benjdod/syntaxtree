package base.syntree;

public class Child {

    // represents a container class for an operand entity
    // Holds either a simple number or a branch.

    private double _num = 0;
    private boolean _isnum = false;
    private Branch _ref = null; 
    private Child _parent = null;

    public Child() {
    }

    public Child(double n) {
        _num = n;
        _isnum = true;
    }

    public Child(Branch p) {
        _ref = p;
    }

    public Branch getBranch() {
        if (_ref == null) {
            throw new NullBranchException("child is not a branch");
        }
        return _ref;
    }

    public String toString() {
        if (_isnum) {
            return Double.toString(_num);
        } else {
            return "(" +  _ref.toString() + ")";
        }
    }

    public void set(double n) {
        _num = n;
        _isnum = true;
        _ref = null;
    }

    public void set(Branch p) {
        _num = 0;
        _isnum = false;
        _ref = p;
    }

    public Child getParent() {
        return _parent;
    }

    public void setParent(Child p) {
        _parent = p;
    }

    public boolean hasParent() {
        if (_parent != null) {
            return true;
        } else {
            return false;
        }
    }

    public boolean isInstantiated() {
        if (_isnum || _ref != null) {
            return true;
        } else {
            return false;
        }
    }

    public boolean isFlat() {
        if (_isnum && (_ref == null)) {
            return true;
        } else {
            return false;
        }
    }

    // unused method?
    public double toNum() {
        if (_isnum) {
            return _num;
        } else {
            throw new RuntimeException("child is not a simple number");
        }
    }
}