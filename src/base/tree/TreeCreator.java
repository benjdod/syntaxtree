package base.tree;

import base.tree.struct.*;
import base.tree.exception.NullBranchException;


public class TreeCreator extends TreeTraverse {
    public TreeCreator(Head h) {
        super(h);
    }

    public TreeCreator() {
        super(new Head());
    }

    public void setLeft(Head b) {
        try {
        current.getBranch().setLeft(b);
        current.getBranch().setLeftHeadParent(current);
        } catch (NullBranchException e) {
            Head currentparent = current.getParent();
            current = new Head(b,new Head(),Op.NULL);
            current.setParent(currentparent);
        }
    }

    public void setLeft(double n) {
        setLeft(new Head(n));
    }

    public void setRight(Head b) {
        try {
        current.getBranch().setRight(b);
        current.getBranch().setRightHeadParent(current);
        } catch (NullBranchException e) {
            Head currentparent = current.getParent();
            current = new Head(b,new Head(),Op.NULL);
            current.setParent(currentparent);
        }
    }

    public void setRight(double n) {
        setRight(new Head(n));
    }

    public void setOperator(Op o) {
        try {
            current.getBranch().setOperator(o);
        } catch (NullBranchException e) {
            current = new Head(new Head(), new Head(), o);
        }
    }
    
    // parent functionality is maybe broken
    public void superLeft() {
        Head tmpparent = current.getParent();
        Head temp = current;
        Head newh = new Head(temp,new Head(),Op.NULL);
        newh.setParent(tmpparent);
        try {tmpparent.getBranch().setRight(newh);} catch (NullPointerException e) {}
        toParent();
    }

    public void subLeft() {
        Head tmp = current.getBranch().getRightHead();
        current.getBranch().setRight(new Head(tmp,new Head(),Op.NULL));
        current.getBranch().setRightHeadParent(current);
    }

    // hard sets the current head
    public void set(Head h) {
        current = h;
    }
}