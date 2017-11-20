
//theworldisquiethere

public class CircularDoubleLinkedList<T> {

	private Node<T> Head;
	private size;

	public void add(T Container) {

		if (Container == null) return;

		if (size == 0) {

			Head = new Node<T>(Container, Head, Head);

			size++;

			return;

		}

		Node<T> Temp = Head.Last;

		Head.Last = new Node<T>(Container, Head, Temp);
		Temp.next = Head.Last;

		size++;

	}

	public size() { return size; }

}

