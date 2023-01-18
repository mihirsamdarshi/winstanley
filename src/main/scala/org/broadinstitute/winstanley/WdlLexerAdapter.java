package org.broadinstitute.winstanley;

import com.intellij.lexer.FlexAdapter;
import org.broadinstitute.winstanley.lexer.WdlLexer;

public class WdlLexerAdapter extends FlexAdapter {
    public WdlLexerAdapter() {
        super(new WdlLexer(null));
    }
}
