package mirrg.h.struct;

public class Struct2<X, Y> extends Struct1<X>
{

	private static final long serialVersionUID = -6230066286262431814L;

	public Y y;

	public Struct2(X x, Y y)
	{
		super(x);
		setY(y);
	}

	public final Y getY()
	{
		return y;
	}

	public final Struct2<X, Y> setY(Y y)
	{
		this.y = y;
		return this;
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
		if (this == obj) {
			return true;
		}
		if (!super.equals(obj)) {
			return false;
		}
		if (!(obj instanceof Struct2)) {
			return false;
		}
		Struct2<?, ?> other = (Struct2<?, ?>) obj;
		if (y == null) {
			if (other.y != null) {
				return false;
			}
		} else if (!y.equals(other.y)) {
			return false;
		}
		return true;
	}

	@Override
	public String toString()
	{
		StringBuilder builder = new StringBuilder();
		builder.append("Struct2 [x=");
		builder.append(getX());
		builder.append(", y=");
		builder.append(getY());
		builder.append("]");
		return builder.toString();
	}

}
