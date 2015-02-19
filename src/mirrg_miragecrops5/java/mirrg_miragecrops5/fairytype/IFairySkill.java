package mirrg_miragecrops5.fairytype;

public interface IFairySkill
{

	public String getName();

	public boolean isPositive();

	public void apply(FairyType fairyType, double level);

}
