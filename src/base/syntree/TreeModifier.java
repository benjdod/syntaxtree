package base.syntree;

public class TreeModifier extends TreeTraverse{
    public TreeModifier(Head h) {
        super(h);
    }

    // pull out to a seperate method which checks if it's an active branch?
    public void setLeft(Head b) {
        try {
        current.getBranch().setLeftHead(b);
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
        current.getBranch().setRightHead(b);
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
    
}