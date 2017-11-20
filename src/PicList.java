
//theworldisquiethere

public class PicList<Pic> extends CircularDoubleLinkedList {

	Node<Pic> Current;

	public Pic start() {

		if (size == 0) return null;

		Current = Head;

		return Current.Data();

	}

	public Pic end() {

		if (size == 0) return null;

		Current = Head.last;

		return Current.Data;

	}

	public Pic next() {

		if (size == 0) return null;

		Current = Current.next;

		return Current.Data;

	}

	public Pic last() {

		if (size == 0) return null;

		Current = Current.last;

		return Current.Data;

	}

}

