package mirrg.h.struct;

public class Tuple<X, Y> extends Tuple1<X>
{

	private static final long serialVersionUID = -6151338189401255007L;

	private final Y y;

	public Tuple(X x, Y y)
	{
		super(x);

		this.y = y;
	}

	public final Y getY()
	{
		return y;
	}

	@Override
	public Tuple<X, Y> modifyX(X x)
	{
		return new Tuple<X, Y>(x, getY());
	}

	public Tuple<X, Y> modifyY(Y y)
	{
		return new Tuple<X, Y>(getX(), y);
	}

	@Override
	public int hashCode()
	{
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((y == null) ? 0 : y.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj)
	{
		if (this == obj) return true;
		if (!super.equals(obj)) return false;
		if (getClass() != obj.getClass()) return false;
		Tuple<?, ?> other = (Tuple<?, ?>) obj;
		if (y == null) {
			if (other.y != null) return false;
		} else if (!y.equals(other.y)) return false;
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
		builder.append("]");
		return builder.toString();
	}

}
