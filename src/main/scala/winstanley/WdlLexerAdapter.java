package winstanley;

import com.intellij.lexer.FlexAdapter;

public class WdlLexerAdapter extends FlexAdapter {
    public WdlLexerAdapter() {
        super(new WdlLexer(null));
    }
}
