package mirrg_miragecrops5.fairytype;

public abstract class FairySkillAbstract implements IFairySkill
{

	protected String name;
	protected double[] values;

	public FairySkillAbstract(String name, double ph, double in, double em, double lo, double ma, double tr)
	{
		this.name = name;
		values = new double[] {
			ph, in, em, lo, ma, tr,
		};
	}

	@Override
	public String getName()
	{
		return name;
	}

	@Override
	public boolean isPositive()
	{
		return false;
	}

}