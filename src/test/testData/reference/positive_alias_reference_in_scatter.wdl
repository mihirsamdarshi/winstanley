workflow look_in_scatter {
    Array[Int] baz = [1, 2, 3, 4, 5]
    Int bam
    scatter (bar in baz) {
        call foo as oof {
            input:
                f = bam
        }
    }
    Array[Int] foobar = <caret>oof.f
}

task foo {
    Int f
    output {
        Int g = f + 1
    }
}