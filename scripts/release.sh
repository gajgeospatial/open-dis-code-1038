#!/bin/bash
#
# Puts together various file releases for sourceforge.
#
# DMcG

# First, languages. This retrieves all the language implementations
# and puts them all in a zip.

mkdir open-dis 

# Checkouts

# java
svn export https://open-dis.svn.sourceforge.net/svnroot/open-dis/languages/java/trunk open-dis/java

# CPP
svn export https://open-dis.svn.sourceforge.net/svnroot/open-dis/languages/cpp/trunk open-dis/cpp

# c-sharp
svn export https://open-dis.svn.sourceforge.net/svnroot/open-dis/languages/csharp/trunk open-dis/csharp

# Objective-c
svn export https://open-dis.svn.sourceforge.net/svnroot/open-dis/languages/objective-c/trunk open-dis/objective-c

# Zip this up

zip -r open-dis.zip open-dis/

# XPlane DIS plugin
svn export https://open-dis.svn.sourceforge.net/svnroot/open-dis/applications/DisPlugin xplaneDisPlugin

zip -r xplaneDisPlugin.zip XPlaneDisPlugin

# Websockets
svn export https://open-dis.svn.sourceforge.net/svnroot/open-dis/applications/DisWebSockets DisWebSockets

zip -r DisWebSockets.zip DisWebSockets

