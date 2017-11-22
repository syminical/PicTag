
//theworldisquiethere

public class PicList extends CircularDoubleLinkedList<Pic> {

	Node<Pic> Current;

	public PicList() { super(); }

	public Pic start() {

		if (size() == 0) return null;

		Current = Head();

		return Current.Data();

	}

	public Pic end() {

		if (size() == 0) return null;

		Current = Head().Last();

		return Current.Data();

	}

	public Pic next() {

		if (size() == 0) return null;

		Current = Current.Next();

		return Current.Data();

	}

	public Pic last() {

		if (size() == 0) return null;

		Current = Current.Last();

		return Current.Data();

	}

}

