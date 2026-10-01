package Quiz2; // update this to your package name

import com.puppycrawl.tools.checkstyle.api.*;
import java.util.regex.Pattern;

public class AntiHungarianCheck extends AbstractCheck {

    // NEW: count Hungarian notation variables
    private int antiHungarianCount = 0;

    private final HungarianNotationMemberDetector detector =
            new HungarianNotationMemberDetector();

    @Override
    public int[] getDefaultTokens() {
        return new int[] {TokenTypes.VARIABLE_DEF};
    }

    // NEW: reset count for every Java file
    @Override
    public void beginTree(DetailAST rootAST) {
        antiHungarianCount = 0;
    }

    @Override
    public void visitToken(DetailAST aAST) {
        String variableName = findVariableName(aAST);

        if (itsAFieldVariable(aAST)
                && detector.detectsNotation(variableName)) {

            // CHANGED: count instead of reporting each variable
            antiHungarianCount++;
        }
    }

    private String findVariableName(DetailAST aAST) {
        DetailAST identifier =
                aAST.findFirstToken(TokenTypes.IDENT);

        return identifier.getText();
    }

    private boolean itsAFieldVariable(DetailAST aAST) {
        return aAST.getParent().getType()
                == TokenTypes.OBJBLOCK;
    }

    // NEW: print only the final count
    @Override
    public void finishTree(DetailAST rootAST) {
        log(rootAST.getLineNo(),
                "AntiHungarian count is " + antiHungarianCount + " IB");
    }

    @Override
    public int[] getAcceptableTokens() {
        return new int[] {TokenTypes.VARIABLE_DEF};
    }

    @Override
    public int[] getRequiredTokens() {
        return new int[0];
    }
}


class HungarianNotationMemberDetector {

    private Pattern pattern =
            Pattern.compile("m[A-Z0-9].*");

    public boolean detectsNotation(String variableName) {
        return pattern.matcher(variableName).matches();
    }
}