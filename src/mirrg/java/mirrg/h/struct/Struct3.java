package mirrg.h.struct;

public class Struct3<X, Y, Z> extends Struct2<X, Y>
{

	private static final long serialVersionUID = 1958692588510365196L;

	public Z z;

	public Struct3(X x, Y y, Z z)
	{
		super(x, y);
		setZ(z);
	}

	public final Z getZ()
	{
		return z;
	}

	public final Struct3<X, Y, Z> setZ(Z z)
	{
		this.z = z;
		return this;
	}

	@Override
	public int hashCode()
	{
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((z == null) ? 0 : z.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj)
	{
		if (this == obj) {
			return true;
		}
		if (!super.equals(obj)) {
			return false;
		}
		if (!(obj instanceof Struct3)) {
			return false;
		}
		Struct3<?, ?, ?> other = (Struct3<?, ?, ?>) obj;
		if (z == null) {
			if (other.z != null) {
				return false;
			}
		} else if (!z.equals(other.z)) {
			return false;
		}
		return true;
	}

	@Override
	public String toString()
	{
		StringBuilder builder = new StringBuilder();
		builder.append("Struct3 [x=");
		builder.append(getX());
		builder.append(", y=");
		builder.append(getY());
		builder.append(", z=");
		builder.append(getZ());
		builder.append("]");
		return builder.toString();
	}

}
