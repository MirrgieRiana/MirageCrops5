package mirrg.h.struct;

public class Tuple3<X, Y, Z> extends Tuple<X, Y>
{

	private static final long serialVersionUID = -5932739191184797856L;

	private final Z z;

	public Tuple3(X x, Y y, Z z)
	{
		super(x, y);

		this.z = z;
	}

	public final Z getZ()
	{
		return z;
	}

	@Override
	public Tuple3<X, Y, Z> modifyX(X x)
	{
		return new Tuple3<X, Y, Z>(x, getY(), getZ());
	}

	@Override
	public Tuple3<X, Y, Z> modifyY(Y y)
	{
		return new Tuple3<X, Y, Z>(getX(), y, getZ());
	}

	public Tuple3<X, Y, Z> modifyZ(Z z)
	{
		return new Tuple3<X, Y, Z>(getX(), getY(), z);
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
		if (!(obj instanceof Tuple3)) {
			return false;
		}
		Tuple3<?, ?, ?> other = (Tuple3<?, ?, ?>) obj;
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
		builder.append(this.getClass().getSimpleName());
		builder.append(" [x=");
		builder.append(getX());
		builder.append(", y=");
		builder.append(getY());
		builder.append(", z=");
		builder.append(getZ());
		builder.append("]");
		return builder.toString();
	}

}
