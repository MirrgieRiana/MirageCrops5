{
	version => [
		['build.gradle', qr/(version\s*=\s*")([^"]*)(")/],
		['src/main/resources/mcmod.info', qr/("version"\s*:\s*")([^"]*)(")/],
#		['src/main/java/mirrg/miragecrops4/ModMirageCrops4.java', qr/(VERSION\s*=\s*")([^"]*)(")/],
	],
	mcversion => [
		['src/main/resources/mcmod.info', qr/("mcversion"\s*:\s*")([^"]*)(")/],
	],
	modid => [
		['build.gradle', qr/(archivesBaseName\s*=\s*")([^"]*)(")/],
		['src/main/resources/mcmod.info', qr/("modid"\s*:\s*")([^"]*)(")/],
	],
	group => [
		['build.gradle', qr/(group\s*=\s*")([^"]*)(")/],
	],
	name => [
		['src/main/resources/mcmod.info', qr/("name"\s*:\s*")([^"]*)(")/],
	],
	description => [
		['src/main/resources/mcmod.info', qr/("description"\s*:\s*")([^"]*)(")/],
	],
}