package Quiz2;

import com.puppycrawl.tools.checkstyle.api.AbstractCheck;
import com.puppycrawl.tools.checkstyle.api.DetailAST;
import com.puppycrawl.tools.checkstyle.api.TokenTypes;

public class SemiColonCheck extends AbstractCheck {

    private int semiColonCount;

    @Override
    public void beginTree(DetailAST rootAST) {
        semiColonCount = 0;
    }

    @Override
    public int[] getDefaultTokens() {
        return new int[] { TokenTypes.SEMI };
    }

    @Override
    public int[] getAcceptableTokens() {
        return getDefaultTokens();
    }

    @Override
    public int[] getRequiredTokens() {
        return getDefaultTokens();
    }

    @Override
    public void visitToken(DetailAST ast) {
        semiColonCount++;
    }

    @Override
    public void finishTree(DetailAST rootAST) {
        System.out.println("Number of semicolons: " + semiColonCount);
    }
}