package base.syntree;

public class TreeTraverse {

    // traverses a syntax tree
    // make sure to take note of the fact that head "wrapper"
    // classes may be able to have a parent functionality...

    // TODO: rework to deal with null parent cases presented by simple number heads
    // make simple number heads inaccessible to users?

    protected Head current;

    public TreeTraverse(Head b) {
        current = b;
    }

    public Head toLeft() {
        // try current.getBranch().getLeftHead().getBranch() to test if the child head is a branch
        try { current.getBranch().getLeftHead().getBranch(); current = current.getBranch().getLeftHead(); }
        catch (NullBranchException e) {
            throw new TreeBoundaryException(this + " has reached the end of the current branch path");
        }
        return current;
    }

    public Head toRight() {
        try { current.getBranch().getRightHead().getBranch(); current = current.getBranch().getRightHead(); }
        catch (NullBranchException e) {
            throw new TreeBoundaryException(this + " has reached the end of the current branch path");
        }
        return current;
    }

    public Head getCurrent() {
        return current;
    }

    public Head toParent() {
        if (current.getParent() == null) { throw new TreeBoundaryException("current branch is the master branch"); }
        current = current.getParent();
        return current;
    }

    public Head toMaster() {
        while (current.hasParent()) {
            current = current.getParent();
        }
        return current;
    }
} 