package Observers;

import Enums.Channel;
import Subjects.UISubject;
import Subjects.UIUpdate;

public interface UIObserver 
{
    public void update(UIUpdate update); // UIUpdate is payload container
    public void onNotified(UISubject subj); // UISUbjectRef answers question "What exactly changed?" USR is based on channel
}
