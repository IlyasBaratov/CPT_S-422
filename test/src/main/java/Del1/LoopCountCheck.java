package Del1;

import com.puppycrawl.tools.checkstyle.api.AbstractCheck;
import com.puppycrawl.tools.checkstyle.api.DetailAST;
import com.puppycrawl.tools.checkstyle.api.TokenTypes;

/**
 * Counts looping statements in a Java source file.
 *
 * The following loops are counted:
 * - for loops
 * - enhanced for loops
 * - while loops
 * - do-while loops
 */
public class LoopCountCheck extends AbstractCheck {

    /** Message key used for logging the result. */
    public static final String MSG_LOOP_COUNT = "loop.count";

    /** Number of loops found in the current file. */
    private int loopCount;

    /**
     * Reset the loop counter when processing a new file.
     *
     * @param rootAST root of the syntax tree
     */
    @Override
    public void beginTree(DetailAST rootAST) {
        loopCount = 0;
    }

    /**
     * Tokens representing Java looping statements.
     *
     * @return loop tokens
     */
    @Override
    public int[] getDefaultTokens() {
        return new int[] {
            TokenTypes.LITERAL_FOR,
            TokenTypes.LITERAL_WHILE,
            TokenTypes.LITERAL_DO
        };
    }

    /**
     * Tokens allowed for this check.
     *
     * @return acceptable tokens
     */
    @Override
    public int[] getAcceptableTokens() {
        return getDefaultTokens();
    }

    /**
     * No additional tokens are required.
     *
     * @return empty token array
     */
    @Override
    public int[] getRequiredTokens() {
        return new int[0];
    }

    /**
     * Every token visited represents one loop.
     *
     * @param ast loop AST node
     */
    @Override
    public void visitToken(DetailAST ast) {
        loopCount++;
    }

    /**
     * Output the final loop count.
     *
     * @param rootAST root of the syntax tree
     */
    @Override
    public void finishTree(DetailAST rootAST) {
        log(rootAST.getLineNo(), MSG_LOOP_COUNT + " is " + loopCount + " IB");
    }
}