package base.syntree;

public class TreeTraverse {

    // traverses a syntax tree
    // make sure to take note of the fact that child "wrapper"
    // classes may be able to have a parent functionality...

    private Head current;

    public TreeTraverse(Head b) {
        current = b;
    }

    public Head toLeft() {
        try { current = current.getBranch().getLeftHead(); }
        catch (NullBranchException e) {
            throw new NullBranchException("cannot descend to the left branch because it is not a branch");
        }
        return current;
    }

    public Head toRight() {
        try { current = current.getBranch().getRightHead(); }
        catch (NullBranchException e) {
            throw new NullBranchException("cannot descend to the right branch because it is not a branch");
        }
        return current;
    }

    public Head getCurrent() {
        return current;
    }

    // need a toParent()
    public Head toParent() {
        if (current.getParent() == null) { throw new IllegalArgumentException("current branch is the master child"); }
        current = current.getParent();
        return current;
    }
} 