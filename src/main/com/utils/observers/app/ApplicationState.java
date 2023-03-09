package com.utils.observers.app;

import com.config.TestException;
import com.utils.observers.Observable;
import lombok.extern.slf4j.Slf4j;

@Slf4j
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
            log.error(e.getMessage(), e);
        }
    }
}
