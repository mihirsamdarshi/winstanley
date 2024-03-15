workflow hello {

  output {
    Int i = <error descr="No declaration found for 'l'">l</error>
  }
}
