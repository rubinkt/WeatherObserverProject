public interface UISubject {
    public void register(UIObserver o, Channel c);
    public void unregister(UIObserver o, Channel c);
    public void notify(Channel c);
    public void setMode(boolean isPush);
}