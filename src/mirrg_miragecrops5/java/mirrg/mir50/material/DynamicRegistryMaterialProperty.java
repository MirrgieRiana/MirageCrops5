package mirrg.mir50.material;

import java.util.HashSet;
import java.util.Hashtable;

public class DynamicRegistryMaterialProperty
{

	private HashSet<String> names = new HashSet<>();
	private Hashtable<String, Integer> hashColor = new Hashtable<>();
	private Hashtable<String, String> hashChemicalFormula = new Hashtable<>();

	public HashSet<String> getNames()
	{
		return names;
	}

	public void set(String name, int color, String chemicalFormula)
	{
		setColor(name, color);
		setChemicalFormula(name, chemicalFormula);
	}

	//

	public void setColor(String name, int color)
	{
		names.add(name);
		hashColor.put(name, color);
	}

	public boolean hasColor(String name)
	{
		return hashColor.containsKey(name);
	}

	public int getColor(String name)
	{
		if (hashColor.containsKey(name)) return hashColor.get(name);
		return 0xFFFFFF;
	}

	//

	public void setChemicalFormula(String name, String chemicalFormula)
	{
		names.add(name);
		hashChemicalFormula.put(name, chemicalFormula);
	}

	public boolean hasChemicalFormula(String name)
	{
		return hashChemicalFormula.containsKey(name);
	}

	public String getChemicalFormula(String name)
	{
		if (hashChemicalFormula.containsKey(name)) return hashChemicalFormula.get(name);
		return "";
	}

}
