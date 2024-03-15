workflow call_in_scatter {
    Array[Int] baz = [1, 2, 3, 4, 5]
    Int bam
    Boolean oof

    scatter (bar in baz) {
        if (oof) {
            call foo {
                input:
                    f = bam
            }
        }
    }
    Array[Int] foobar = <caret>foo.g
}

task foo {
    Int f
    output {
        Int g = f + 1
    }
}