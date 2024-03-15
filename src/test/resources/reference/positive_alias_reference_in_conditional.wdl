workflow look_in_scatter {
    Boolean baz
    Int bam
    if (baz) {
        call foo as oof {
            input:
                f = bam
        }
    }
    Int foobar = <caret>oof.f
}

task foo {
    Int f
    output {
        Int g = f + 1
    }
}