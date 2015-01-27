use strict;
use warnings;
use utf8;

use Encode;

use Getopt::Long;

require __FILE__.'/../lib/mirrg.pl';

my %opts = (
	config => 'mirrg.config.pl',
);
Getopt::Long::GetOptions(\%opts, qw/config=s/) or die;

# args

my $argument_descs = "<entry_name> <value>";

my $entry_name = extract_argument("entry_name", $argument_descs);
my $value = extract_argument("value", $argument_descs);

validate_more_arguments($argument_descs);

# start

print "Config File: [$opts{config}]", "\n";
print "\n";

my $config = read_config($opts{config});

unless (defined $config->{$entry_name}) {
	print "Unknown entry_name: [$entry_name]", "\n";
	die;
}

my $entry = $config->{$entry_name};

map {
	print "BEFORE: ", ReplaceEntry->new($_->[0], $_->[1])->show, "\n";
	ReplaceEntry->new($_->[0], $_->[1])->set($value);
	print "AFTER:  ", ReplaceEntry->new($_->[0], $_->[1])->show, "\n";
} @$entry;

1;
