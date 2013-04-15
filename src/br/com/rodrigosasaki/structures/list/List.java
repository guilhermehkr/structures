package br.com.rodrigosasaki.structures.list;

import java.util.Arrays;
import java.util.Iterator;

import br.com.rodrigosasaki.structures.iterator.ArrayIterator;
import br.com.rodrigosasaki.structures.util.ArrayUtil;

/**
 * @author Rodrigo Sasaki
 */
public class List<E> implements Iterable<E>{
	
	private static final int INITIAL_CAPACITY = 10;
	
	private E[] elements;
	private int index;

	public List(){
		this(INITIAL_CAPACITY);
	}

	@SuppressWarnings("unchecked")
	public List(int size){
		elements = (E[]) new Object[size];
		index = 0;
	}

	public void add(E e){
		controlLength();
		elements[index++] = e;
	}
	
	public E get(int i){
		return elements[i];
	}
	
	public E remove(int i){
		index--;
		E removedElement = elements[i];
		ArrayUtil.moveBackwards(elements, i + 1, 1);
		controlLength();
		return removedElement;
	}

	private void controlLength(){
		if (index >= INITIAL_CAPACITY){
			if (index >= elements.length){
				elements = Arrays.copyOf(elements, index * 2);
			} else if (index <= elements.length / 4){
				elements = Arrays.copyOf(elements, elements.length / 2);
			}
		}
	}

	public int size(){
		return index;
	}

	public boolean isEmpty(){
		return index == 0;
	}

	@Override
	public Iterator<E> iterator(){
		return new ArrayIterator<E>(elements);
	}
}