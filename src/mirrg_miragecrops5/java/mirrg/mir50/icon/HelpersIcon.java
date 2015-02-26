package mirrg.mir50.icon;

import java.net.URL;

public class HelpersIcon
{

	public static final String TYPE_ITEMS = "textures/items";
	public static final String TYPE_BLOCKS = "textures/blocks";

	/**
	 * リソースが存在する場合のみURLを返す。存在しない場合はnullを返す
	 */
	public static URL getURLFromTextureName(String domain, String typeString, String textureNameWithoutDomain)
	{
		StringBuffer path = new StringBuffer();
		path.append("assets/");
		path.append(domain);
		path.append("/");
		path.append(typeString);
		path.append("/");
		path.append(textureNameWithoutDomain);
		path.append(".png");

		return HelpersIcon.class.getClassLoader().getResource(path.toString());
	}

	/**
	 * テクスチャが存在する場合のみURLを返す。存在しない場合はnullを返す
	 */
	public static URL getURLFromTextureName(String textureName, String typeString)
	{
		int domainLength = textureName.indexOf(':');

		if (domainLength == -1) {
			return getURLFromTextureName("minecraft", typeString, textureName);
		}

		return getURLFromTextureName(
			textureName.substring(0, domainLength),
			typeString,
			textureName.substring(domainLength + 1));
	}

}
