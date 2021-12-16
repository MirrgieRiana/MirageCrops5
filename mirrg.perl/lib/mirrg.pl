use strict;

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

sub readFile( $ )
{
	my $filename = shift;
	
	open(my $fp, "<", $filename);
	binmode $fp;
	my $buf = join "", <$fp>;
	close $fp;
	
	return $buf;
}

sub writeFile( $$ )
{
	my $filename = shift;
	my $buf = shift;
	
	open(my $fp, ">", $filename);
	binmode $fp;
	print $fp $buf;
	close $fp;
}

package VersionFileEntry;

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

sub new( $$$ )
{
	my $class = shift;
	my $filename = shift;
	my $regexp = shift;
	
	return bless([$filename, $regexp], $class);
}

sub set($)
{
	my $self = shift;
	my $version = shift;
	
	-e $self->getFilename or die;
	my $buf = main::readFile($self->getFilename);
	$buf =~ $self->getRegexp or die;
	$buf = $`.$1.$version.$3.$';
	
	main::writeFile($self->getFilename, $buf);
}

sub get()
{
	my $self = shift;
	
	-e $self->getFilename or die;
	my $buf = main::readFile($self->getFilename);
	$buf =~ $self->getRegexp or die;
	
	return $2;
}

sub getFilename()
{
	my $self = shift;
	
	return $self->[0];
}

sub getRegexp()
{
	my $self = shift;
	
	return $self->[1];
}

1;
