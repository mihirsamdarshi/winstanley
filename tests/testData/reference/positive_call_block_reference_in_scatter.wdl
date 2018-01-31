workflow call_in_scatter {
    Array[Int] baz = [1, 2, 3, 4, 5]
    Int bam
    scatter (bar in baz) {
        call foo {
            input:
                f = bam
        }
    }
    Array[Int] foobar = <caret>foo.f
}

task foo {
    Int f
    output {
        Int g = f + 1
    }
}