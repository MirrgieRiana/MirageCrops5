use strict;
use warnings;

=pod
sub getVersionFileEntries($)
{
	my $prefix = shift;
	
	-f $VersionFileEntry::defaultFileName or die;
	
	my $entries = do $VersionFileEntry::defaultFileName;
	
	die $@ if $@;
	
	if (ref $entries eq "ARRAY") {
		$entries = {
			version => $entries,
		};
	}
	
	die "entries is not hash" unless ref $entries eq "HASH";
	
	my $hash = {};
	
	for my $key(keys %$entries) {
		$hash->{$key} = [map {
			VersionFileEntry->new($prefix.$_->[0], $_->[1]),
		} @{$entries->{$key}}];
	}
	
	return $hash;
}
=cut

sub read_file( $ )
{
	my $filename = shift;
	
	open(my $fp, "<", $filename);
	binmode $fp;
	my $buf = join "", <$fp>;
	close $fp;
	
	return $buf;
}

sub write_file( $$ )
{
	my $filename = shift;
	my $buf = shift;
	
	open my $fp, ">", $filename or die;
	binmode $fp;
	print $fp $buf;
	close $fp;
}

sub read_config( $ )
{
	my $filename = shift;
	
	no warnings;
	my $config = do($filename);
	use warnings;
	
	return $config;
}

sub validate_more_arguments($)
{
	my $argument_descs = shift;
	
	unless ($#ARGV == -1) {
		print "Unknown arguments: [".join(" ", @ARGV)."]", "\n";
		print "ARGUMENTS: $argument_descs", "\n";
		die;
	}
	
}

sub extract_argument($)
{
	my $argument_name = shift;
	my $argument_descs = shift;
	
	my $argument = shift @ARGV;
	unless (defined $argument) {
		print "$argument_name is undefined!", "\n";
		print "ARGUMENTS: $argument_descs", "\n";
		die;
	}
	
	return $argument;
}

package ReplaceEntry;

=pod
$VersionFileEntry::defaultFileName = "./mirrg.config.pl";
$VersionFileEntry::regexps{"build.gradle"} = qr/(version = ")([^"]+)(")/;
$VersionFileEntry::regexps{"build.gradle archivesBaseName"} = qr/(archivesBaseName = ")([^"]+)(")/;
$VersionFileEntry::regexps{"mcmod.info"} = qr/("version": ")([^"]+)(")/;
$VersionFileEntry::regexps{"mcmod.info modid"} = qr/("modid": ")([^"]+)(")/;
$VersionFileEntry::regexps{"mcmod.info name"} = qr/("name": ")([^"]+)(")/;
$VersionFileEntry::regexps{"mcmod.info description"} = qr/("description": ")([^"]+)(")/;
$VersionFileEntry::regexps{"java VERSION"} = qr/(VERSION = ")([^"]+)(")/;
$VersionFileEntry::regexps{"java MODID"} = qr/(MODID = ")([^"]+)(")/;
$VersionFileEntry::regexps{"java NAME"} = qr/(NAME = ")([^"]+)(")/;
=cut

sub new( $$$ )
{
	my $class = shift;
	my $filename = shift;
	my $regexp = shift;
	
	return bless([$filename, $regexp], $class);
}

sub get_filename()
{
	my $self = shift;
	
	return $self->[0];
}

sub get_regexp()
{
	my $self = shift;
	
	return $self->[1];
}

sub match($)
{
	my $self = shift;
	
	-e $self->get_filename or die "no such file: ".$self->get_filename;
	my $buf = main::read_file($self->get_filename);
	$buf =~ $self->get_regexp or die "do not match regexp in ".$self->get_filename;
	
	return $`, $1, $2, $3, $';
}

sub set($$)
{
	my $self = shift;
	my $value = shift;
	
	my ($left, $pre, $main, $suf, $right) = $self->match();
	my $buf = $left.$pre.$value.$suf.$right;
	
	main::write_file($self->get_filename, $buf);
}

sub get($)
{
	my $self = shift;
	
	my ($left, $pre, $main, $suf, $right) = $self->match();
	
	return $main;
}

sub show($)
{
	my $self = shift;
	
	my ($left, $pre, $main, $suf, $right) = $self->match();
	
	return $pre."[[".$main."]]".$suf;
}

1;
