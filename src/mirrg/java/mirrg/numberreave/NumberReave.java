package mirrg.numberreave;

import static org.junit.Assert.*;

import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import mirrg.h.struct.Tuple;

import org.junit.Test;

public class NumberReave
{

	public static ArrayList<Tuple<Integer, boolean[]>> allReave(Storage storage)
	{
		ArrayList<Tuple<Integer, boolean[]>> list = new ArrayList<>();

		for (int i = 0; i < storage.getLength(); i++) {
			while (!storage.isEmpty(i)) {
				int value = storage.first(i);

				list.add(new Tuple<>(value, storage.reaveAll(value)));

			}
		}

		return list;
	}

	public static ArrayList<Tuple<Integer, boolean[]>> allReaveShort(Storage storage)
	{
		ArrayList<Tuple<Integer, boolean[]>> list = new ArrayList<>();

		for (int i = 0; i < storage.getLength(); i++) {
			while (!storage.isEmpty(i)) {
				int value = storage.first(i);

				ArrayList<Integer> schedule = new ArrayList<>();
				tryInsertSchedule(value, schedule, storage);

				for (int j = 0; j < schedule.size(); j++) {
					list.add(new Tuple<>(schedule.get(j), storage.reaveAll(schedule.get(j))));
				}

			}
		}

		return list;
	}

	private static void tryInsertSchedule(int value, ArrayList<Integer> schedule, Storage storage)
	{

		for (int i = 0; i < storage.getLength(); i++) { // 全ての文字列の中で

			int index = storage.indexOf(i, value); // 追加したい文字が
			if (index >= 1) { // 先頭以外の場所に現れた場合、

				for (int j = 0; j < index; j++) { // それより前の全てのindexの
					int value2 = storage.at(i, j); // 文字は
					// スケジュールに割り込む可能性がある。

					if (schedule.indexOf(value2) == -1) { // 既に予約されている文字でなければ、
						schedule.add(value2); // 予約に入れる。
					}
				}

			}

		}

		schedule.add(value); // 当初から予約したかったやつを予約する。

	}

	//

	private static String[] createRandomStrings(int length, int stringMaxLength, int variation)
	{
		String[] strings = new String[length];

		for (int i = 0; i < strings.length; i++) {
			strings[i] = createRandomString(((int) Math.random() * stringMaxLength), variation);
		}

		return strings;
	}

	private static String createRandomString(int stringLength, int variation)
	{
		StringBuffer sb = new StringBuffer();

		for (int i = 0; i < stringLength; i++) {
			sb.append('a' + ((int) Math.random() * variation));
		}

		return sb.toString();
	}

	private static String buildFromIntegers(Stream<Integer> integers)
	{
		return integers
			.map(a -> String.valueOf((char) (int) a))
			.collect(Collectors.joining());
	}

	private static String buildFromBooleans(boolean[] booleans)
	{
		StringBuffer sb = new StringBuffer();

		for (boolean b : booleans) {
			sb.append(b ? "T" : "F");
		}

		return sb.toString();
	}

	private final static String[] TABLE = {
		"12",
		"111",
		"22",
		"1421",
		"32121",
		"424",
	};

	@Test
	public void test()
	{
		{
			Storage storage = Storage.valueOf(TABLE);
			ArrayList<Tuple<Integer, boolean[]>> res = allReave(storage);
			assertEquals("12112421321214", buildFromIntegers(res.stream().map(Tuple::getX)));
			assertTrue(storage.isEmpty());
		}

		{
			Storage storage = Storage.valueOf(TABLE);
			ArrayList<Tuple<Integer, boolean[]>> res = allReaveShort(storage);
			assertEquals("32142114", buildFromIntegers(res.stream().map(Tuple::getX)));
			assertEquals("FFFFTF", buildFromBooleans(res.get(0).getY()));
			assertEquals("FFTFTF", buildFromBooleans(res.get(1).getY()));
			assertEquals("TTFTTF", buildFromBooleans(res.get(2).getY()));
			assertEquals("FFFTFT", buildFromBooleans(res.get(3).getY()));
			assertEquals("TFTTTT", buildFromBooleans(res.get(4).getY()));
			assertEquals("FTFTTF", buildFromBooleans(res.get(5).getY()));
			assertEquals("FTFFFF", buildFromBooleans(res.get(6).getY()));
			assertEquals("FFFFFT", buildFromBooleans(res.get(7).getY()));
			assertTrue(storage.isEmpty());
		}

		long start = System.currentTimeMillis();

		int limit = 2000000;
		for (int t = 0; t < limit; t++) {
			Storage storage = Storage.valueOf(createRandomStrings(10, 10, Integer.MAX_VALUE));

			allReaveShort(storage);
			assertTrue(storage.isEmpty());

			if ((t + 1) % (limit / 10) == 0) System.out.println(t + 1);
		}

		long end = System.currentTimeMillis();

		System.out.println(MessageFormat.format("Time: {0} s ({1} Hz)",
			0.001 * (end - start),
			1000.0 * limit / (end - start)));
	}

}
