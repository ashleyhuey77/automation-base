package com.warnermedia.utils.observers.app;

import com.warnermedia.config.TestException;
import com.warnermedia.utils.observers.Observable;

public class ApplicationState extends Observable<ApplicationState, Application, IssueType> {
    private IssueType currentState;

    public ApplicationState() {

    }

    /**
     * @throws TestException
     *
     */
    public void checkState() throws TestException {
        currentState = new AppObserverHelper(currentState).issueType;
        notifyObservers(currentState);
    }
}
