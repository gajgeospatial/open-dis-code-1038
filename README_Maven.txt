= Instructions for using Maven build system

== Compile and build the jars

TIP: The "mvn install" step will copy the primary project artifact into your
local ~/.m2/repository/ so that it can be referenced as a dependency by related
projects)

$ cd utilities/sedris
$ mvn compile
$ mvn install

$ cd ../Enumerations
$ mvn compile
$ mvn install

$ cd ../../languages/java/trunk
$ mvn compile
$ mvn install

This will have created the following artifacts:

- utilities/sedris/target/srm-4.4.0.jar
- utilities/Enumerations/target/dis-enums-1.1.jar
- languages/java/trunk/target/open-dis-4.0.8.jar

== Deploy release artifacts to Maven Central

Suppose some changes are made to open-dis and you wish to deploy a 4.0.x release. We follow this guide:

https://docs.sonatype.org/display/Repository/Sonatype+OSS+Maven+Repository+Usage+Guide

In a nutshell the person performing the release will need:
 * A Sonatype JIRA account
 * Your JIRA credentials populated in your ~/.m2/settings.xml
 * Your GPG key published

Once that's done, each release is fairly simple.

$ cd languages/java/trunk

$ mvn release:clean
$ mvn release:prepare
$ mvn release:perform
