package base.syntree;

public class TreeCreator extends TreeModifier {
    public TreeCreator(Head h) {
        super(h);
    }

    public TreeCreator() {
        super(new Head());
    }
    
    // parent functionality is maybe broken
    public void superLeft() {
        Head tmpparent = current.getParent();
        Head temp = current;
        Head newh = new Head(temp,new Head(),Op.NULL);
        newh.setParent(tmpparent);
        try {tmpparent.getBranch().setRightHead(newh);} catch (NullPointerException e) {}
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