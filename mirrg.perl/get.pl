use strict;
require __FILE__.'/../lib/mirrg.pl';

my $property = shift @ARGV;
unless (defined $property) {
	printDie();
	exit;
}

{
	my $versionFileEntries = getVersionFileEntries("./");
	
	for my $versionFileEntry(@{$versionFileEntries->{$property}}) {
		print $versionFileEntry->get, "\n";
	}
}

sub printDie
{
	print "USAGE: {property} {value:string}", "\n";
	print "property", "\n";
	print "        version", "\n";
	print "        modid", "\n";
	print "        name", "\n";
	print "        description", "\n";
}
