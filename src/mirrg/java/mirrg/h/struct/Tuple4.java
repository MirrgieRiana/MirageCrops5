package mirrg.h.struct;

public class Tuple4<X, Y, Z, W> extends Tuple3<X, Y, Z>
{

	private static final long serialVersionUID = -551537179060965001L;

	private final W w;

	public Tuple4(X x, Y y, Z z, W w)
	{
		super(x, y, z);

		this.w = w;
	}

	public final W getW()
	{
		return w;
	}

	@Override
	public Tuple4<X, Y, Z, W> modifyX(X x)
	{
		return new Tuple4<X, Y, Z, W>(x, getY(), getZ(), getW());
	}

	@Override
	public Tuple4<X, Y, Z, W> modifyY(Y y)
	{
		return new Tuple4<X, Y, Z, W>(getX(), y, getZ(), getW());
	}

	@Override
	public Tuple4<X, Y, Z, W> modifyZ(Z z)
	{
		return new Tuple4<X, Y, Z, W>(getX(), getY(), z, getW());
	}

	public Tuple4<X, Y, Z, W> modifyW(W w)
	{
		return new Tuple4<X, Y, Z, W>(getX(), getY(), getZ(), w);
	}

	@Override
	public int hashCode()
	{
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((w == null) ? 0 : w.hashCode());
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
		if (!(obj instanceof Tuple4)) {
			return false;
		}
		Tuple4<?, ?, ?, ?> other = (Tuple4<?, ?, ?, ?>) obj;
		if (w == null) {
			if (other.w != null) {
				return false;
			}
		} else if (!w.equals(other.w)) {
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
		builder.append(", w=");
		builder.append(getW());
		builder.append("]");
		return builder.toString();
	}

}
