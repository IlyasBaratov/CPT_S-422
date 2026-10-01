package Del1;

import com.puppycrawl.tools.checkstyle.api.AbstractCheck;
import com.puppycrawl.tools.checkstyle.api.DetailAST;
import com.puppycrawl.tools.checkstyle.api.TokenTypes;

/**
 * Counts the total number of comments in a Java source file.
 *
 * A // comment counts as one comment.
 * A block comment counts as one comment.
 * A Javadoc comment counts as one comment.
 */
public class CommentCountCheck extends AbstractCheck {

    /** Message key used for logging the result. */
    public static final String MSG_COMMENT_COUNT = "comment.count";

    /** Number of comments found in the current file. */
    private int commentCount;

    /**
     * Reset the counter when Checkstyle starts processing a new file.
     *
     * @param rootAST root of the syntax tree
     */
    @Override
    public void beginTree(DetailAST rootAST) {
        commentCount = 0;
    }

    /**
     * Tokens this check examines.
     *
     * SINGLE_LINE_COMMENT represents // comments.
     * BLOCK_COMMENT_BEGIN represents block and Javadoc comments.
     *
     * @return tokens used by this check
     */
    @Override
    public int[] getDefaultTokens() {
        return new int[] {
            TokenTypes.SINGLE_LINE_COMMENT,
            TokenTypes.BLOCK_COMMENT_BEGIN
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
     * No tokens are required beyond the configured tokens.
     *
     * @return empty token array
     */
    @Override
    public int[] getRequiredTokens() {
        return new int[0];
    }

    /**
     * Tell Checkstyle that this check needs comments in the AST.
     *
     * @return true because we are analyzing comments
     */
    @Override
    public boolean isCommentNodesRequired() {
        return true;
    }

    /**
     * Every visited token represents the beginning of one comment.
     *
     * @param ast comment AST node
     */
    @Override
    public void visitToken(DetailAST ast) {
        commentCount++;
    }

    /**
     * Output the final number of comments after processing the file.
     *
     * @param rootAST root of the syntax tree
     */
    @Override
    public void finishTree(DetailAST rootAST) {
        log(rootAST.getLineNo(), MSG_COMMENT_COUNT + " is " + commentCount + " IB");
    }
}