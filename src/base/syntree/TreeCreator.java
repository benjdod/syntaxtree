package base.syntree;

public class TreeCreator extends TreeModifier {
    public TreeCreator(Head h) {
        super(h);
    }

    public TreeCreator() {
        super(new Head());
    }
    
    public void superLeft() {
        Head tmp = current;
        current = new Head(tmp,new Head(),Op.NULL);
    }

    public void subLeft() {
        Head tmp = current.getBranch().getRightHead();
        // FIXME: not here, but maybe consider moving parent functionality to the 
        // Branch class since you can't descend into single digits anymore...
        current.getBranch().setRightHead(new Head(tmp,new Head(),Op.NULL));
        current.getBranch().setRightHeadParent(current);
    }
}