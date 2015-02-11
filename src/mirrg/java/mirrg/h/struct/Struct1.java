package mirrg.h.struct;

import java.io.Serializable;

public class Struct1<X> implements Serializable
{

	private static final long serialVersionUID = -6151338189401255007L;

	public X x;

	public Struct1(X x)
	{
		setX(x);
	}

	public final X getX()
	{
		return x;
	}

	public final Struct1<X> setX(X x)
	{
		this.x = x;
		return this;
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
		Struct1<?> other = (Struct1<?>) obj;
		if (x == null) {
			if (other.x != null) return false;
		} else if (!x.equals(other.x)) return false;
		return true;
	}

	@Override
	public String toString()
	{
		StringBuilder builder = new StringBuilder();
		builder.append("Struct1 [x=");
		builder.append(getX());
		builder.append("]");
		return builder.toString();
	}

}
