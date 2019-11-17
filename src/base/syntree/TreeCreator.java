package base.syntree;

public class TreeCreator extends TreeModifier {
    public TreeCreator(Head h) {
        super(h);
    }

    public TreeCreator() {
        super(new Head());
    }
    
    // parent functionality is broken
    public void superLeft() {
        // Head tmp = current;
        // Head tmpparent = current.getParent();
        // current = new Head(tmp,new Head(),Op.NULL);
        // current.setParent(tmpparent);
        Head tmpparent = current.getParent();
        Head newh = new Head(current,new Head(),Op.NULL);
        newh.setParent(tmpparent);
        tmpparent.getBranch().setRightHead(newh);
        toParent();
    }

    public void subLeft() {
        Head tmp = current.getBranch().getRightHead();
        current.getBranch().setRightHead(new Head(tmp,new Head(),Op.NULL));
        current.getBranch().setRightHeadParent(current);
    }

    // hard sets the current head
    public void set(Head h) {
        current = h;
    }
}