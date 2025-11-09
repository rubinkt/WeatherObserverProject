package Subjects;

import Enums.Channel;
import Observers.UIObserver;

public interface UISubject {
    public void register(UIObserver o, Channel c);
    public void unregister(UIObserver o, Channel c);
    public void notifyObservers();
    public void setMode(boolean isPush);
}