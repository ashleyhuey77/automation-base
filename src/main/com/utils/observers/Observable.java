package com.utils.observers;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

import com.config.TestException;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public abstract class Observable<S extends Observable<S, O, A>, O extends Observer<S, O, A>, A> {

	protected List<O> observers;

	public Observable() {
		this.observers = new CopyOnWriteArrayList<>();
	}

	public void addObserver(O observer) {
		this.observers.add(observer);
	}

	public void removeObserver(O observer) {
		this.observers.remove(observer);
	}

	/**
	 * Notify observers.
	 * @throws TestException
	 */
	@SuppressWarnings("unchecked")
	public void notifyObservers(A argument) throws TestException {
		try {
			for (O observer : observers) {
				observer.update((S) this, argument);
			}
		} catch (Exception e) {
			log.error(e.getMessage(), e);
		}
	}

}
