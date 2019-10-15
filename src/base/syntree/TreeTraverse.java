package base.syntree;

public class TreeTraverse {

    // traverses a syntax tree
    // make sure to take note of the fact that child "wrapper"
    // classes may be able to have a parent functionality...

    private Child current;

    public TreeTraverse(Child b) {
        current = b;
    }

    public Child toLeft() {
        try { current = current.getBranch().getLeftChild(); }
        catch (NullBranchException e) {
            throw new NullBranchException("cannot descend to the left branch because it is not a branch");
        }
        return current;
    }

    public Child toRight() {
        try { current = current.getBranch().getRightChild(); }
        catch (NullBranchException e) {
            throw new NullBranchException("cannot descend to the right branch because it is not a branch");
        }
        return current;
    }

    public Child getCurrent() {
        return current;
    }

    // need a toParent()
    // this will always be a branch, never a double...
    // in fact, these all will be branches, never doubles, because descending to
    // a double is illegal.
    public Child toParent() {
        if (current.getParent() == null) { throw new IllegalArgumentException("current branch is the master child"); }
        current = current.getParent();
        return current;
    }
} 