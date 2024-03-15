workflow hello_wf {
  call foo as bar
  call foo
  Int out = <caret>bar.j
}

task foo {
  Int i
  command {
    echo ${i}
  }

  output {
    Int j = read_int(stdout())
  }
}
