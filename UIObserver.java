public interface UIObserver {
    public void update(UIUpdate update); // UIUpdate is payload container
    public void onNotified(UISubjectRef ref, Channel c); // UISUbjectRef answers question "What exactly changed?" USR is based on channel
}
