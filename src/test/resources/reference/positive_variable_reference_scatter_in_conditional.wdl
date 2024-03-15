workflow in_scatter {
    Array[Int] baz = [1, 2, 3, 4, 5]
    Int bam
    Boolean oof

    if (oof) {
        scatter (bar in baz) {
            call foo {
                input:
                    f = bam
            }
            Int that = foo.g
        }
    }
    Array[Int] foobar = <caret>that
}

task foo {
    Int f
    output {
        Int g = f + 1
    }
}