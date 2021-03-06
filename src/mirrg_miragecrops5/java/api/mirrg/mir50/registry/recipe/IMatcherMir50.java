package api.mirrg.mir50.registry.recipe;

public interface IMatcherMir50<I, O>
{

	/**
	 * このメソッドは入力されたインスタンスを破壊します。
	 * このメソッドを連続して呼び出すことはできません。
	 * @return このメソッドによって減少した分の入力。
	 */
	public I consume();

	/**
	 * @return 破壊してはならないインスタンスを返却します。
	 */
	public O getOutput();

}
