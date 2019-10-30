package base.syntree;

import java.lang.Math;

public class Branch {

    /*
    represents the most basic tree structure 
    of two head operands joined by an operator 
    */

    private Head _thishead = new Head();
    private Head _lefthead = new Head();
    private Head _righthead = new Head();
    private Op _operator = Op.NULL;
    // private boolean _isMaster = false;

    public Branch(double lhead, double rhead, Op operator) {
        _thishead.set(this);
        _lefthead.set(lhead);
        _righthead.set(rhead);
        _operator = operator;
    }

    public Branch(Branch lhead, double rhead, Op operator) {
        _thishead.set(this);
        _lefthead.set(lhead);
        _lefthead.setParent(_thishead);
        _righthead.set(rhead);
        _operator = operator;
    }

    public Branch(double lhead, Branch rhead, Op operator) {
        _thishead.set(this);
        _lefthead.set(lhead);
        _righthead.set(rhead);
        _righthead.setParent(_thishead);
        _operator = operator;
    }

    public Branch(Branch lhead, Branch rhead, Op operator) {
        _thishead.set(this);
        _lefthead.set(lhead);
        _lefthead.setParent(_thishead);
        _righthead.set(rhead);
        _righthead.setParent(_thishead);
        _operator = operator;
    }

    public Head getLeftHead() {
        return _lefthead;
    }

    public Head getRightHead() {
        return _righthead;
    }

    public Head getThisHead() {
        return _thishead;
    }

    public Op getOp() {
        return _operator;
    }

    public void setLeft(double n) {
        _lefthead.set(n);
    }

    public void setLeft(Branch p) {
        _lefthead.set(p);
    }

    public void setRight(double n) {
        _righthead.set(n);
    }

    public void setRight(Branch p) {
        _righthead.set(p);
    }

    public void setLeftHead(Head c) {
        _lefthead = c;
    }

    public void setRightHead(Head c) {
        _righthead = c;
    }

    public void setThisHead(Head c) {
        _thishead = c;
    }

    public void setLeftHeadParent(Head c) {
        _lefthead.setParent(c);
    }

    public void setRightHeadParent(Head c) {
        _righthead.setParent(c);
    }

    public void setOperator(Op op) {
        _operator = op;
    }

    public String toString() {
        String ret = "";
        switch (_operator) {
            case PLUS:
                ret =  _lefthead.toString() + " + " + _righthead.toString();
                break;
            case MINUS:
                ret = _lefthead.toString() + " - " + _righthead.toString();
                break;
            case MULT:
                ret = _lefthead.toString() + " * " + _righthead.toString();
                break;
            case DIV:
                ret = _lefthead.toString() + " / " + _righthead.toString();
                break;
            case EXP:
                ret = _lefthead.toString() + " ^ " + _righthead.toString();
                break;
            default:
                ret = "Branch.toString() returned a null value for some reason. This isn't right.";
        }
        return ret;
    }

    boolean bothInstantiated() {
        if (_lefthead.isInstantiated() && _righthead.isInstantiated()) {
            return true;
        } else {
            return false;
        }
    }
    boolean isFull() {
        return (bothInstantiated() && _operator != Op.NULL);
    }
    boolean isFlat() {
        return (_lefthead.isFlat() && _righthead.isFlat());
    }

    public double calculate() {
        
        double calc_l = 0;
        double calc_r = 0;

        if (!isFull()) {
            throw new RuntimeException("values for one or more items in the tree have not been provided");
        }

        // four calculation methods based on which headren are branches or numbers
        if (_righthead.isFlat() && _lefthead.isFlat()) {
            calc_r = _righthead.getNum();
            calc_l = _lefthead.getNum();
        }

        if (!_lefthead.isFlat() && _righthead.isFlat()) {
            calc_l = _lefthead.getBranch().calculate();
            calc_r = _righthead.getNum();
        }

        if (!_righthead.isFlat() && _lefthead.isFlat()) {
            calc_r = _righthead.getBranch().calculate();
            calc_l = _lefthead.getNum();
        }

        if (!_righthead.isFlat() && !_lefthead.isFlat()) {
            calc_r = _righthead.getBranch().calculate();
            calc_l = _lefthead.getBranch().calculate();
        }

        if (_operator == Op.PLUS) {
            return (calc_l + calc_r);
        } else if (_operator == Op.MINUS) {
            return (calc_l - calc_r);
        } else if (_operator == Op.MULT) {
            return (calc_l * calc_r);
        } else if (_operator == Op.DIV) {
            return (calc_l / calc_r);
        } else if (_operator == Op.EXP) {
            return (Math.pow(calc_l,calc_r));
        } else {
            throw new RuntimeException("something is wack, it gave the wrong enum type..");
        }
    }
}
