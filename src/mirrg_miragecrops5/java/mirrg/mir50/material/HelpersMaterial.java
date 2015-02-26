package mirrg.mir50.material;

public class HelpersMaterial
{

	public static void initDefaultMaterials(DynamicRegistryMaterialProperty containerMaterialProperty)
	{

		// 金属酸化物

		containerMaterialProperty.set("calcite", 0xDBE0BE, "CaCO3");
		containerMaterialProperty.set("magnesite", 0xDAE1F0, "MgCO3");
		containerMaterialProperty.set("siderite", 0x91A42C, "FeCO3");
		containerMaterialProperty.set("rhodochrosite", 0xE594BB, "MnCO3");
		containerMaterialProperty.set("smithsonite", 0x97DDBF, "ZnCO3");
		containerMaterialProperty.set("sphaerocobaltite", 0xC0278B, "CoCO3");
		containerMaterialProperty.set("gaspeite", 0x406F2F, "NiCO3");
		containerMaterialProperty.set("otavite", 0x95ABE8, "CdCO3");

		containerMaterialProperty.set("talc", 0xD3E2D7, "Mg3Si4O10(OH)2");
		containerMaterialProperty.set("gypsum", 0xEAEAEA, "CaSO4");
		containerMaterialProperty.set("calcite", 0xDBE0BE, "CaCO3");
		containerMaterialProperty.set("fluorite", 0x1BE86A, "CaF2");
		containerMaterialProperty.set("apatite", 0x5BB5FF, "Ca5(PO4)3OH");
		containerMaterialProperty.set("orthoclase", 0xFFE2B7, "KAlSi3O8");
		containerMaterialProperty.set("quartz", 0xC6D0FF, "SiO2");
		containerMaterialProperty.set("topaz", 0xFFC46D, "Al2SiO4OH2");
		containerMaterialProperty.set("ruby", 0xE40000, "(Al2O3)49AlCrO3");
		containerMaterialProperty.set("diamond", 0x33EBCB, "C");

		containerMaterialProperty.set("glass", 0xEEEEEE, "SiO2");

		// 単体金属

		containerMaterialProperty.set("calcium", 0xE0E0D5, "Ca");
		containerMaterialProperty.set("magnesium", 0xD8A9A9, "Mg");
		containerMaterialProperty.set("iron", 0xA5A5A5, "Fe");
		containerMaterialProperty.set("manganese", 0xC8C0C0, "Mn");
		containerMaterialProperty.set("zinc", 0xE5DBF2, "Zn");
		containerMaterialProperty.set("cobalt", 0x4242CF, "Co");
		containerMaterialProperty.set("nickel", 0x96A4FF, "Ni");
		containerMaterialProperty.set("cadmium", 0x2F2F38, "Cd");

		containerMaterialProperty.set("copper", 0xEF7351, "Cu");
		containerMaterialProperty.set("tin", 0xCEDBEA, "Sn");
		containerMaterialProperty.set("gold", 0xFFFF0B, "Au");
		containerMaterialProperty.set("silver", 0xD8F4FF, "Ag");
		containerMaterialProperty.set("lead", 0x5929AD, "Pb");
		containerMaterialProperty.set("mercury", 0xD5B7B7, "Hg");
		containerMaterialProperty.set("titanium", 0xD197FF, "Ti");
		containerMaterialProperty.set("chrome", 0xFFAAD5, "Ch");
		containerMaterialProperty.set("iridium", 0xE0FFE0, "Ir");
		containerMaterialProperty.set("osmium", 0x4B00CE, "Os");
		containerMaterialProperty.set("tungsten", 0x333346, "W");
		containerMaterialProperty.set("platinum", 0xFFF6DD, "Pt");
		containerMaterialProperty.set("aluminium", 0xC0D3F7, "Al");
		containerMaterialProperty.set("bismuth", 0x597ABA, "Bi");

		// 合金

		containerMaterialProperty.set("bronze", 0xFF6A00, "Cu3Sn");
		containerMaterialProperty.set("brass", 0xEABE2E, "Cu3Zn");
		containerMaterialProperty.set("electrum", 0xFFFF8C, "AgAu");
		containerMaterialProperty.set("steel", 0x74749E, "Fe49C");
		containerMaterialProperty.set("stainlessSteel", 0x9EEDBB, "Fe6NiCrMn");
		containerMaterialProperty.set("tungstenSteel", 0x3636A0, "Fe49W50C");

		// 非金属

		containerMaterialProperty.set("sulfur", 0xFFE727, "S");

	}

}
