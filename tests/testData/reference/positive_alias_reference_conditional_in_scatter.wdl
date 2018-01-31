workflow call_in_scatter {
    Array[Int] baz = [1, 2, 3, 4, 5]
    Int bam
    Boolean frack

    scatter (bar in baz) {
        if (frack) {
            call foo as oof {
                input:
                    f = bam
            }
        }
    }
    Array[Int] foobar = <caret>oof.g
}

task foo {
    Int f
    output {
        Int g = f + 1
    }
}