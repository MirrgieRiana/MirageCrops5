package mirrg.p.virtualclass.test;

import mirrg.p.virtualclass.IVirtualImplementationAccessor;
import mirrg.p.virtualclass.VirtualClass;
import mirrg.p.virtualclass.VirtualClassCastException;

import org.junit.Assert;
import org.junit.Test;

public class TestVirtualClass
{

	@Test
	public void test()
	{
		{
			A a = new A();

			Assert.assertEquals("iron", a.getName());
		}

		////////////////////////////////////////////////////

		{
			B b = new B();

			Assert.assertEquals("iron", b.getName());

			b.virtualClass.override(new IGetName() {
				@Override
				public String getName()
				{
					return "gold";
				}
			});

			Assert.assertEquals("gold", b.getName());

		}

		////////////////////////////////////////////////////

		{
			B b = new B();

			{
				Assert.assertEquals(false, b.virtualClass.instanceOf(C.class));
				try {
					b.virtualClass.cast(C.class);
					Assert.fail();
				} catch (Exception e) {
					Assert.assertEquals(VirtualClassCastException.class, e.getClass());
					Assert.assertTrue(e.getMessage().matches("[^ ]+ is not interface [^ ]+"));
				}
			}

			b.virtualClass.register(C.class, new C() {
				@Override
				public int getStrength()
				{
					return 400;
				}
			});

			Assert.assertEquals(true, b.virtualClass.instanceOf(C.class));

			IVirtualImplementationAccessor<C> accessorC = b.virtualClass.cast(C.class);
			Assert.assertNotEquals(null, accessorC);

			{
				C c = accessorC.get();
				Assert.assertNotEquals(null, c);

				Assert.assertEquals(400, c.getStrength());
			}

			{
				Assert.assertEquals(false, b.virtualClass.instanceOf(D.class));
				try {
					b.virtualClass.cast(D.class);
					Assert.fail();
				} catch (Exception e) {
					Assert.assertEquals(VirtualClassCastException.class, e.getClass());
					Assert.assertTrue(e.getMessage().matches("[^ ]+ is not interface [^ ]+"));
				}
			}

			b.virtualClass.register(D.class, new D() {
				private final C superC = b.virtualClass.getCurrentImplementation(C.class);

				@Override
				public int getStrength()
				{
					return superC.getStrength();
				}

				@Override
				public int getDurability()
				{
					return getStrength() * 2;
				}
			});

			Assert.assertEquals(400, accessorC.get().getStrength());
			Assert.assertEquals(800, b.virtualClass.getCurrentImplementation(D.class).getDurability());

			b.virtualClass.override(new C() {
				@Override
				public int getStrength()
				{
					return 789;
				}
			});

			Assert.assertEquals(789, accessorC.get().getStrength());
			Assert.assertEquals(800, b.virtualClass.getCurrentImplementation(D.class).getDurability());

			b.virtualClass.override(new D() {
				private final D superD = b.virtualClass.getCurrentImplementation(D.class);

				@Override
				public int getStrength()
				{
					return superD.getDurability() * 2;
				}

				@Override
				public int getDurability()
				{
					return b.virtualClass.getCurrentImplementation(D.class).getStrength() * 2;
				}
			});

			Assert.assertEquals(1600, accessorC.get().getStrength());
			Assert.assertEquals(3200, b.virtualClass.getCurrentImplementation(D.class).getDurability());

			b.virtualClass.override(new IGetName() {
				@Override
				public String getName()
				{
					return "" + b.virtualClass.getCurrentImplementation(D.class).getDurability();
				}
			});

			Assert.assertEquals("3200", b.getName());

		}

	}

	////////////////////////////////////////////////////

	public static class A
	{

		public String getName()
		{
			return "iron";
		}

	}

	////////////////////////////////////////////////////

	public static class B implements IGetName
	{

		public final VirtualClass virtualClass = new VirtualClass(this);

		public B()
		{
			virtualClass.register(IGetName.class, new GetName(this));
			accessor_IGetName = virtualClass.cast(IGetName.class);
		}

		public VirtualClass getVirtualClass()
		{
			return virtualClass;
		}

		//

		public final IVirtualImplementationAccessor<IGetName> accessor_IGetName;

		@Override
		public String getName()
		{
			return accessor_IGetName.get().getName();
		}

	}

	public static interface IGetName
	{

		public String getName();

	}

	public static class GetName implements IGetName
	{

		protected final B b;

		public GetName(B b)
		{
			this.b = b;
		}

		@Override
		public String getName()
		{
			return "iron";
		}

	}

	////////////////////////////////////////////////////

	public static interface C
	{

		public int getStrength();

	}

	public static interface D extends C
	{

		public int getDurability();

	}

}
