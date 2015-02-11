package mirrg.h.struct;

import java.util.Iterator;

public class TupleList2<X, Y>
	extends Tuple<Iterable<X>, Iterable<Y>>
	implements Iterable<Tuple<X, Y>>
{

	/**
	 * 
	 */
	private static final long serialVersionUID = 4984146283438849578L;

	public TupleList2(Iterable<X> x, Iterable<Y> y)
	{
		super(x, y);
	}

	@Override
	public java.util.Iterator<Tuple<X, Y>> iterator()
	{
		return new IteratorImpl(getX(), getY());
	}

	private class IteratorImpl
		extends Tuple<Iterator<X>, Iterator<Y>>
		implements Iterator<Tuple<X, Y>>
	{
		/**
		 * 
		 */
		private static final long serialVersionUID = -199832514292839113L;

		public IteratorImpl(Iterable<X> x, Iterable<Y> y)
		{
			super(x.iterator(), y.iterator());
		}

		@Override
		public boolean hasNext()
		{
			if (!getX().hasNext()) return false;
			if (!getY().hasNext()) return false;
			return true;
		}

		@Override
		public Tuple<X, Y> next()
		{
			return new Tuple<X, Y>(getX().next(), getY().next());
		}

		@Override
		public void remove()
		{
			getX().remove();
			getY().remove();
		}
	}

}
