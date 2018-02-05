workflow variable_in_scatter {
    Array[Int] baz = [1, 2, 3, 4, 5]
    scatter (bar in baz) {
        Int foo = bar + 1
    }
    Array[Int] foobar = <caret>foo
}