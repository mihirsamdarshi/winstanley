workflow hello_wf {
  call me as my_alias

  output {
    Int out = <caret>my_alias.j
  }
}

task me {
  Int i
  command {
    echo ${i}
  }

  output {
    Int j = read_int(stdout())
  }
}
