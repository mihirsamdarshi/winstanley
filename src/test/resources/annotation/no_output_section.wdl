version 1.0

task <weak_warning descr="Suspicious lack of task outputs (is this task really idempotent and portable?)">no_runtime_section</weak_warning> {
  command { }
  runtime { docker: "ubuntu:latest" }
}
