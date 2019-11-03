package base.syntree;

public class Head {

    // represents a container class for an operand entity
    // Holds either a simple number or a branch.

    private double _num = 0;
    private boolean _isnum = false;
    private Branch _ref = null; 
    private Head _parent = null;

    public Head() {
    }

    public Head(double n) {
        _num = n;
        _isnum = true;
    }

    // new wrapper type constructors
    public Head(double left, double right, Op operator) {
        _ref = new Branch(left,right,operator);
    }

    public Head(Head left, double right, Op operator) {
        _ref = new Branch(0,right,operator);
        _ref.setLeftHead(left);
        _ref.setLeftHeadParent(this);
        _ref.setThisHead(this);
    }

    public Head(double left, Head right, Op operator) {
        _ref = new Branch(left,0,operator);
        _ref.setRightHead(right);
        _ref.setRightHeadParent(this);
        _ref.setThisHead(this);
    }

    public Head(Head left, Head right, Op operator) {
        _ref = new Branch(0,0,operator);
        _ref.setLeftHead(left);
        _ref.setRightHead(right);
        _ref.setLeftHeadParent(this);
        _ref.setRightHeadParent(this);
        _ref.setThisHead(this);
    }

    // do users ever need to use these?
    public Branch getBranch() {
        if (_ref == null) {
            throw new NullBranchException("The current head is not a branch");
        }
        return _ref;
    }

    public double getNum() {
        if (_isnum) {
            return _num;
        } else {
            throw new RuntimeException("The current head is not a simple number");
        }
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

    public Head getParent() {
        return _parent;
    }

    public void setParent(Head p) {
        _parent = p;
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

    public double calculate() {
        if (_ref != null) {
            return _ref.calculate();
        } else {
            return _num;
        }
    }
}