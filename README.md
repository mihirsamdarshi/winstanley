# winstanley: An IntelliJ Plug-in for WDL

This plug-in currently supports:
* Syntax highlighting

## Getting Started
To build or test the plugin using IntelliJ:

* Get set up with the pre-requisites listed here: http://www.jetbrains.org/intellij/sdk/docs/tutorials/custom_language_support/prerequisites.html
* Create a new IntelliJ project using this repo as the existing source.
* Generate the necessary files:
 * Navigate to wdl.bnf and generate sources using COMMAND-G
 * Navigate to Wdl.flex and generate sources using COMMAND-G
* At this point, you can run or test the project using IntelliJ's preset run modes. Fuller instructions for setting this up are in the IntelliJ links above.

## Errata

Note that this is currently a source-only project and requires IntelliJ to build.
The plug-in was created based on the instructions here: http://www.jetbrains.org/intellij/sdk/docs/tutorials/custom_language_support_tutorial.html 
