workflow hello_wf {
  call me

  output {
    Int out = <caret>me.j
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
