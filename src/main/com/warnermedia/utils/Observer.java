package com.warnermedia.utils;

import com.warnermedia.config.TestException;

public interface Observer<S extends Observable<S, O, A>, O extends Observer<S, O, A>, A> {

	void update(S subject, A argument) throws TestException;
}
