workflow in_scatter {
    Array[Int] baz = [1, 2, 3, 4, 5]
    Array[Int] zab = [1, 2, 3, 4, 5]
    Int bam
    Boolean frack

    if (frack) {
        scatter (bar in baz) {
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