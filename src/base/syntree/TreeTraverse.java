package base.syntree;

public class TreeTraverse {

    // traverses a syntax tree
    // make sure to take note of the fact that head "wrapper"
    // classes may be able to have a parent functionality...

    private Head current;

    public TreeTraverse(Head b) {
        current = b;
    }

    public Head toLeft() {
        try { current = current.getBranch().getLeftHead(); }
        catch (NullBranchException e) {
            throw new TreeBoundaryException("have reached the end of the current branch path");
        }
        return current;
    }

    public Head toRight() {
        if (current.getBranch().getRightHead() == null) {
            throw new TreeBoundaryException("have reached the end of the current branch path");
        }
        try { current = current.getBranch().getRightHead(); }
        catch (NullBranchException e) {
            throw new TreeBoundaryException("have reached the end of the current branch path");
        }
        return current;
    }

    public Head getCurrent() {
        return current;
    }

    // need a toParent()
    public Head toParent() {
        if (current.getParent() == null) { throw new IllegalArgumentException("current branch is the master head"); }
        current = current.getParent();
        return current;
    }
} 