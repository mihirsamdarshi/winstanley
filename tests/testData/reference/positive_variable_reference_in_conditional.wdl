workflow variable_in_conditional {
    Boolean bar
    if (bar) {
        Int foo = 1
    }
    output {
        Int baz = <caret>foo
    }
}