package com.warnermedia.utils.observers.app;

import com.warnermedia.config.TestException;
import com.warnermedia.utils.observers.Observable;

public class ApplicationState extends Observable<ApplicationState, Application, IssueType> {
    private static ThreadLocal<IssueType> currentState = new ThreadLocal<>();

    public ApplicationState() {

    }

    /**
     * @throws TestException
     *
     */
    public void checkState() throws TestException {
        try {
            if (currentState.get() == null) {
                currentState.set(IssueType.NONE);
            }
            IssueType result = new AppObserverHelper(currentState.get()).issueType.get();
            currentState.set(result);
            notifyObservers(currentState.get());
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}