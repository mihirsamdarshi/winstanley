# winstanley: An IntelliJ Plug-in for WDL

This plug-in currently supports:
* Syntax highlighting

## Getting Started

### As a Plug-in User

The plugin is now part of the IntelliJ Plugin Repository. To install:
* Open the IntelliJ Preferences page
* Click on "Plugins"
* Click on "Browse Repositories "
* Search for and install "Winstanley WDL"

### Or, as a Developer
To build or test the plugin using IntelliJ:

* Get set up with the pre-requisites listed here: http://www.jetbrains.org/intellij/sdk/docs/tutorials/custom_language_support/prerequisites.html
* Create a new IntelliJ project using this repo as the existing source.
* Generate the necessary files (on Mac):
 * Navigate to wdl.bnf and generate sources using \[Command + G\]
 * Navigate to Wdl.flex and generate sources using \[Command + G\]
* At this point, you can run or test the project using IntelliJ's preset run modes. Fuller instructions for setting this up are in the IntelliJ links above.

## Errata

The plug-in was created based on the instructions here: http://www.jetbrains.org/intellij/sdk/docs/tutorials/custom_language_support_tutorial.html 
