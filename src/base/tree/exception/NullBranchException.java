package base.tree.exception;

public class NullBranchException extends IllegalArgumentException {

    private static final long serialVersionUID = 1L;

    public NullBranchException(String str) {
        super(str);
    }
}