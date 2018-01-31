workflow in_conditional {
    Boolean baz
    Boolean nest
    Int bam
    if (baz) {
        if (nest) {
            call foo {
                input:
                    f = bam
            }
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