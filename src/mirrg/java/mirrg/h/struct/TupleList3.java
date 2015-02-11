package mirrg.h.struct;

import java.util.Iterator;

public class TupleList3<X, Y, Z>
	extends Tuple3<Iterable<X>, Iterable<Y>, Iterable<Z>>
	implements Iterable<Tuple3<X, Y, Z>>
{

	/**
	 * 
	 */
	private static final long serialVersionUID = 9157005248959338211L;

	public TupleList3(Iterable<X> x, Iterable<Y> y, Iterable<Z> z)
	{
		super(x, y, z);
	}

	@Override
	public java.util.Iterator<Tuple3<X, Y, Z>> iterator()
	{
		return new IteratorImpl(getX(), getY(), getZ());
	}

	private class IteratorImpl
		extends Tuple3<Iterator<X>, Iterator<Y>, Iterator<Z>>
		implements Iterator<Tuple3<X, Y, Z>>
	{
		/**
		 * 
		 */
		private static final long serialVersionUID = 2246151315298294955L;

		public IteratorImpl(Iterable<X> x, Iterable<Y> y, Iterable<Z> z)
		{
			super(x.iterator(), y.iterator(), z.iterator());
		}

		@Override
		public boolean hasNext()
		{
			if (!getX().hasNext()) return false;
			if (!getY().hasNext()) return false;
			if (!getZ().hasNext()) return false;
			return true;
		}

		@Override
		public Tuple3<X, Y, Z> next()
		{
			return new Tuple3<X, Y, Z>(getX().next(), getY().next(), getZ().next());
		}

		@Override
		public void remove()
		{
			getX().remove();
			getY().remove();
			getZ().remove();
		}
	}

}
