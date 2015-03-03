package mirrg_miragecrops5.fairytype;

public class FairySkillNegative extends FairySkillAbstract
{

	public FairySkillNegative(String name, double ph, double in, double em, double lo, double ma, double tr)
	{
		super(name, ph, in, em, lo, ma, tr);
	}

	@Override
	public boolean isPositive()
	{
		return false;
	}

	@Override
	public void increase(int[] values, int tier, double level)
	{
		double rate = level * 10;
		rate *= Math.pow(4, tier - 1);
		rate *= Math.pow(0.95, level - 1);
		rate *= 0.9;
		for (int i = 0; i < 6; i++) {
			values[i] += (int) Math.ceil(this.values[i] * rate);
		}
	}

}
