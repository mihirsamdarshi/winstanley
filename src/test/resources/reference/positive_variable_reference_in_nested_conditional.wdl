workflow variable_in_nested_conditional {
    Boolean bar
    Boolean nest

    if (bar) {
        if (nest) {
            Int foo = 1
        }
    }
    output {
        Int baz = <caret>foo
    }
}