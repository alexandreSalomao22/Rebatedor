#!/usr/bin/env perl

# set MAVEN_CMD=%HOMEDRIVE%%HOMEPATH%\opt\apache-maven-3.3.9\bin\mvn.cmd
# %MAVEN_CMD% -Dmaven.wagon.http.ssl.allowall=true -Dmdep.outputFile=mdep.classpath dependency:build-classpath clean package
# perl run-classpath.pl

use strict;
use warnings;
die 'run "maven -Dmdep.outputFile=mdep.classpath dependency:build-classpath clean package" first' unless -f 'mdep.classpath';
$ENV{'CLASSPATH'} = join(';', 'target\\classes', `cat mdep.classpath`);
system('java', '-ea', 'main.Y_SW_RebatedorAdquirencia', @ARGV);
