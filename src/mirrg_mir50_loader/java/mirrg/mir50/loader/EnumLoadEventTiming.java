package mirrg.mir50.loader;

public enum EnumLoadEventTiming
{
	Created,

	ClientBeforePreInit,
	ServerBeforePreInit,
	PreInit,
	ClientAfterPreInit,
	ServerAfterPreInit,

	ClientBeforeInit,
	ServerBeforeInit,
	Init,
	ClientAfterInit,
	ServerAfterInit,

	ClientBeforePostInit,
	ServerBeforePostInit,
	PostInit,
	ClientAfterPostInit,
	ServerAfterPostInit,

	Compile, ;

	public static final int count = values().length;
}
