package mirrg.h.struct;

import java.io.Serializable;

public class Tuple1<X> implements Serializable
{

	private static final long serialVersionUID = -593273184797856L;

	private final X x;

	public Tuple1(X x)
	{
		this.x = x;
	}

	public final X getX()
	{
		return x;
	}

	public Tuple1<X> modifyX(X x)
	{
		return new Tuple1<X>(x);
	}

	@Override
	public int hashCode()
	{
		final int prime = 31;
		int result = 1;
		result = prime * result + ((x == null) ? 0 : x.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj)
	{
		if (this == obj) return true;
		if (obj == null) return false;
		if (getClass() != obj.getClass()) return false;
		Tuple1<?> other = (Tuple1<?>) obj;
		if (x == null) {
			if (other.x != null) return false;
		} else if (!x.equals(other.x)) return false;
		return true;
	}

	@Override
	public String toString()
	{
		StringBuilder builder = new StringBuilder();
		builder.append(this.getClass().getSimpleName());
		builder.append(" [x=");
		builder.append(getX());
		builder.append("]");
		return builder.toString();
	}

}
