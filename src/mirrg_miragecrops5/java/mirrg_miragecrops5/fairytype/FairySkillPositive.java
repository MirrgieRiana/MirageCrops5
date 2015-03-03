package mirrg_miragecrops5.fairytype;

public class FairySkillPositive extends FairySkillAbstract
{

	public FairySkillPositive(String name, double ph, double in, double em, double lo, double ma, double tr)
	{
		super(name, ph, in, em, lo, ma, tr);
	}

	@Override
	public boolean isPositive()
	{
		return true;
	}

	@Override
	public void increase(int[] values, int tier, double level)
	{
		double rate = level * 10;
		rate *= Math.pow(4, tier - 1);
		rate *= Math.pow(1.05, level - 1);
		for (int i = 0; i < 6; i++) {
			values[i] += (int) Math.ceil(this.values[i] * rate);
		}
	}

}