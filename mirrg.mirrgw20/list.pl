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

my $argument_descs = "";

validate_more_arguments($argument_descs);

# start

print "Config File: [$opts{config}]", "\n";
print "\n";

my $config = read_config($opts{config});

print join("\n", keys $config), "\n";

1;
