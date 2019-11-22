#!/usr/bin/env perl

eval 'exec perl -wS $0 ${1+"$@"}'
    if 0;

use strict;
use warnings;
use POSIX qw(strftime mktime);
use Socket;     # This defines PF_INET and SOCK_STREAM

local $\ = "\n";

####

my $port = 4550;
my $server_ip_address = "127.0.0.1";
my $msg_hex = q{00B4F0F4F0F0C2F2F3F8F0F0F0F0F0F8F4F0F0F0F0F0F0F0F0F0F0F0F4F0F0F0F0F0F0F0F1F1F0F0F3F0F7F3F0F0F0F0F0F0F5F1F1F1F0F0F0F5F0F2F1F4F1F3F2F0F2F3F3F1F7F0F1F1F1F3F1F8F0F5F0F2F1F0F8F34040404040404040F5F0F0F7F9F5F5F740404040404040F0F1F0F0F2F3F3F1F6F9F0F5F0F2F1F1F1F3F1F8F0F0F0F0F0F0F0F0F0F0F0F0F0F0F0F0F0F0F0F0F0F0F0F2F6F0F0F0F0F0F0F0F0F0F2F3F4F5F7F2F9F8F5F2F7F0F0F0F0F0F0F0F1};
my $msg_bin = pack(q{H*}, $msg_hex);

####

socket(SOCKET, PF_INET, SOCK_STREAM, (getprotobyname('tcp'))[2]);

connect( SOCKET, pack_sockaddr_in($port, inet_aton($server_ip_address)))
   or die "Can't connect to port $port! \n";

{
    local $\ = undef;
    print SOCKET ($msg_bin);
}

my $response;

print("aaa");

#$response = <SOCKET>;
recv(SOCKET, $response, 2, 0);

print("bbb");

print("got ", length($response)," bytes");
