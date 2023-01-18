workflow in_conditional {
    Boolean baz
    Int bam
    if (baz) {
        call foo {
            input:
                f = bam
        }
    }
    Int foobar = <caret>foo.f
}

task foo {
    Int f
    output {
        Int g = f + 1
    }
}