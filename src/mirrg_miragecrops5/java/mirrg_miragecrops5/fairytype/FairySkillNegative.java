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
	public void increase(int[] values, double level)
	{
		level *= Math.pow(0.95, level - 1);
		level *= 0.5;
		for (int i = 0; i < 6; i++) {
			values[i] += (int) Math.ceil(this.values[i] * level * 10);
		}
	}

}