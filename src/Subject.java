public interface Subject {
    void attach(HangmanView v);
    void detach(HangmanView v);
    void notifyViews();
}