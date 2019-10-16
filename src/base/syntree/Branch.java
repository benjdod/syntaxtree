package base.syntree;

import java.lang.Math;

public class Branch {

    /*
    represents the most basic tree structure 
    of two child operands joined by an operator 
    */


    // there's something weird with _thischild that's 
    // throwing off parent child functionality.
    // consider encapsulating branch and double under child.
    private Head _thischild = new Head();
    private Head _leftchild = new Head();
    private Head _rightchild = new Head();
    private Op _operator = Op.NULL;
    // private boolean _isMaster = false;

    public Branch(double lchild, double rchild, Op operator) {
        _thischild.set(this);
        _leftchild.set(lchild);
        _rightchild.set(rchild);
        _operator = operator;
    }

    public Branch(Branch lchild, double rchild, Op operator) {
        _thischild.set(this);
        _leftchild.set(lchild);
        _leftchild.setParent(_thischild);
        _rightchild.set(rchild);
        _operator = operator;
    }

    public Branch(double lchild, Branch rchild, Op operator) {
        _thischild.set(this);
        _leftchild.set(lchild);
        _rightchild.set(rchild);
        _rightchild.setParent(_thischild);
        _operator = operator;
    }

    public Branch(Branch lchild, Branch rchild, Op operator) {
        _thischild.set(this);
        _leftchild.set(lchild);
        _leftchild.setParent(_thischild);
        _rightchild.set(rchild);
        _rightchild.setParent(_thischild);
        _operator = operator;
    }

    public Head getLeftHead() {
        return _leftchild;
    }

    public Head getRightHead() {
        return _rightchild;
    }

    public Head getThisHead() {
        return _thischild;
    }

    public Op getOp() {
        return _operator;
    }

    public void setLeft(double n) {
        _leftchild.set(n);
    }

    public void setLeft(Branch p) {
        _leftchild.set(p);
    }

    public void setRight(double n) {
        _rightchild.set(n);
    }

    public void setRight(Branch p) {
        _rightchild.set(p);
    }

    public void setLeftHead(Head c) {
        _leftchild = c;
    }

    public void setRightHead(Head c) {
        _rightchild = c;
    }

    public void setThisHead(Head c) {
        _thischild = c;
    }

    public void setOperator(Op op) {
        _operator = op;
    }

    public String toString() {
        String ret = "";
        switch (_operator) {
            case PLUS:
                ret =  _leftchild.toString() + " + " + _rightchild.toString();
                break;
            case MINUS:
                ret = _leftchild.toString() + " - " + _rightchild.toString();
                break;
            case MULT:
                ret = _leftchild.toString() + " * " + _rightchild.toString();
                break;
            case DIV:
                ret = _leftchild.toString() + " / " + _rightchild.toString();
                break;
            case EXP:
                ret = _leftchild.toString() + " ^ " + _rightchild.toString();
                break;
            default:
                ret = "Branch.toString() returned a null value for some reason. This isn't right.";
        }
        return ret;
    }

    boolean bothInstantiated() {
        if (_leftchild.isInstantiated() && _rightchild.isInstantiated()) {
            return true;
        } else {
            return false;
        }
    }

    boolean isFull() {
        return (bothInstantiated() && _operator != Op.NULL);
    }

    boolean isFlat() {
        return (_leftchild.isFlat() && _rightchild.isFlat());
    }

    public double calculate() {
        
        double calc_l = 0;
        double calc_r = 0;

        if (!isFull()) {
            throw new RuntimeException("values for one or more items in the tree have not been provided");
        }

        // four calculation methods based on which children are branches or numbers
        if (_rightchild.isFlat() && _leftchild.isFlat()) {
            calc_r = _rightchild.toNum();
            calc_l = _leftchild.toNum();
        }

        if (!_leftchild.isFlat() && _rightchild.isFlat()) {
            calc_l = _leftchild.getBranch().calculate();
            calc_r = _rightchild.toNum();
        }

        if (!_rightchild.isFlat() && _leftchild.isFlat()) {
            calc_r = _rightchild.getBranch().calculate();
            calc_l = _leftchild.toNum();
        }

        if (!_rightchild.isFlat() && !_leftchild.isFlat()) {
            calc_r = _rightchild.getBranch().calculate();
            calc_l = _leftchild.getBranch().calculate();
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
