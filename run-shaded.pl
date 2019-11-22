#!/usr/bin/env perl
use strict;
use warnings;
my $jar = 'target/rebatedor-0.1-SNAPSHOT.jar';
die 'run "maven clean package" first' unless -f $jar;
system('java', '-ea', '-jar', $jar, @ARGV);
